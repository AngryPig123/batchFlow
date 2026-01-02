package com.batchflow.batch.seed.service;

import com.batchflow.batch.seed.dto.SeedLoadResult;
import com.batchflow.batch.seed.loader.Inserter;
import org.apache.ibatis.session.SqlSession;

import java.util.function.Function;

/**
 * packageName    : com.batchflow.batch.seed.service
 * fileName       : SeedService
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
public interface OlistSeedService {
    <T> SeedLoadResult load(Class<T> type, Function<SqlSession, Inserter<T>> inserterProvider);
}
