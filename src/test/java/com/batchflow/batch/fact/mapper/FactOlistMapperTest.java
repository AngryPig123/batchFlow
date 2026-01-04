package com.batchflow.batch.fact.mapper;

import com.batchflow.batch.fact.entity.FactOrderItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.batchflow.batch.fact.mapper
 * fileName       : FactOlistMapperTest
 * author         : AngryPig123
 * date           : 26. 1. 4.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 4.        AngryPig123       최초 생성
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("h2")
class FactOlistMapperTest {

    @Autowired
    private FactOlistMapper factOlistMapper;

    @Test
    void fact_olist_mapper_성공() {

        Map<String, Object> map = new HashMap<>();
        List<FactOrderItemEntity> factOrderItemEntities = factOlistMapper.selectFactOrderItemsByDeliveredDate();
        for (int i = 0; i < factOrderItemEntities.size(); i++) {
            int count = factOlistMapper.insertFactOrderItem(factOrderItemEntities.get(i));
            log.info("count = {}", count);
        }

    }


}