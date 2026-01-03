package com.batchflow.batch.seed.service;

import com.batchflow.bootstrap.dto.insert.OlistCustomersSeedRow;
import com.batchflow.bootstrap.dto.SeedLoadResult;
import com.batchflow.bootstrap.mapper.OlistSeedMapper;
import com.batchflow.bootstrap.service.OlistSeedService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * packageName    : com.batchflow.batch.seed.service
 * fileName       : OlistSeedServiceImplTest
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Slf4j
@ActiveProfiles("h2")
@SpringBootTest
class OlistSeedServiceImplTest {

    @Autowired
    private OlistSeedService olistSeedService;

    @Test
    void testClass() {
        SeedLoadResult load = olistSeedService.load(OlistCustomersSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistCustomer;
                }
        );
        log.info("seed load result = {}", load);
    }

}