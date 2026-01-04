package com.batchflow.batch.fact.mapper;

import com.batchflow.batch.fact.entity.FactOrderItemEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.batchflow.batch.fact.mapper
 * fileName       : FactOlistMapper
 * author         : AngryPig123
 * date           : 26. 1. 4.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 4.        AngryPig123       최초 생성
 */
@Mapper
public interface FactOlistMapper {

    List<FactOrderItemEntity> selectFactOrderItemsByDeliveredDate();

    int insertFactOrderItem(FactOrderItemEntity entity);

}
