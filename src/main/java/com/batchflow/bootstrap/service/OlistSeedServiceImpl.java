package com.batchflow.bootstrap.service;

import com.batchflow.bootstrap.dto.SeedLoadResult;
import com.batchflow.bootstrap.loader.BulkCsvLoader;
import com.batchflow.bootstrap.loader.Inserter;
import com.batchflow.bootstrap.type.SeedCsv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * packageName    : com.batchflow.batch.seed.service
 * fileName       : OlistSeedServiceImpl
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OlistSeedServiceImpl implements OlistSeedService {

    private final BulkCsvLoader bulkCsvLoader;
    private final ResourceLoader resourceLoader;
    private final SqlSessionFactory sqlSessionFactory;

    private final int chunkSize = 1_000;

    @Override
    public <T> SeedLoadResult load(
            Class<T> type,
            Function<SqlSession, Inserter<T>> inserterProvider
    ) {

        SeedLoadResult result = null;
        SeedCsv seedType = SeedCsv.lookup(type);
        try (InputStream inputStream = this.fromClasspath(seedType.getPath())) {
            return bulkCsvLoader.load(
                    type,
                    inserterProvider,
                    inputStream,
                    chunkSize,
                    sqlSessionFactory
            );
        } catch (IOException ioe) {
            log.error("존재하지 않는 경로,", ioe);
        }
        return result;
    }

    public InputStream fromClasspath(String path) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + path);
        return resource.getInputStream();
    }

}










