package com.batchflow.domain.olist.repository;

import com.batchflow.domain.olist.entity.OlistCustomers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.batchflow.domain.seed.repository
 * fileName       : OlistCustomersRepository
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Repository
public interface OlistCustomersRepository extends JpaRepository<OlistCustomers, String> {
}
