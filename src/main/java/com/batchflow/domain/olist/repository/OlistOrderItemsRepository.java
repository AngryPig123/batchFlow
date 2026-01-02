package com.batchflow.domain.olist.repository;

import com.batchflow.domain.olist.entity.OlistOrderItems;
import com.batchflow.domain.olist.entity.id.OlistOrderItemsPk;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.batchflow.domain.seed.entity
 * fileName       : OlistOrderItemsRepository
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
public interface OlistOrderItemsRepository extends JpaRepository<OlistOrderItems, OlistOrderItemsPk> {
}
