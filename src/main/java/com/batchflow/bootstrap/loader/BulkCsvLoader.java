package com.batchflow.bootstrap.loader;

import com.batchflow.bootstrap.dto.SeedLoadResult;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * packageName    : com.batchflow.batch.seed
 * fileName       : BulkCsvLoader
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Slf4j
@Component
public class BulkCsvLoader {

    public <T> SeedLoadResult load(
            Class<T> type,
            java.util.function.Function<SqlSession, Inserter<T>> inserterProvider,
            InputStream is,
            int chunkSize,
            SqlSessionFactory sqlSessionFactory
    ) {

        CsvMapper csvMapper = CsvMapper.builder()
                .addModule(new JavaTimeModule())
                .build();

        csvMapper.enable(CsvParser.Feature.TRIM_SPACES);
        csvMapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES);

        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        long total = 0, success = 0, failed = 0;

        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            Inserter<T> inserter = inserterProvider.apply(session);

            MappingIterator<T> it = csvMapper.readerFor(type).with(schema).readValues(is);

            int buffered = 0;

            while (it.hasNext()) {
                total++;
                try {
                    T row = it.next();
                    inserter.insert(row);
                    success++;
                    buffered++;

                    if (buffered == chunkSize) {
                        session.flushStatements();
                        session.commit();
                        session.clearCache();
                        buffered = 0;
                    }
                } catch (Exception e) {
                    failed++;
                    log.error("failed = ", e);
                }
            }

            if (buffered > 0) {
                session.flushStatements();
                session.commit();
                session.clearCache();
            }

        } catch (Exception e) {

            throw new IllegalStateException("CSV bulk load failed. total = {}" + total, e);
        }
        return new SeedLoadResult(total, success, failed);
    }

}
