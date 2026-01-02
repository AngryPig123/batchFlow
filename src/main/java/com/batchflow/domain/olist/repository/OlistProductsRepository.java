package com.batchflow.domain.olist.repository;

import com.batchflow.domain.olist.entity.OlistProducts;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.batchflow.domain.seed.repository
 * fileName       : OlistProductsRepository
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
public interface OlistProductsRepository extends JpaRepository<OlistProducts, String> {
}
