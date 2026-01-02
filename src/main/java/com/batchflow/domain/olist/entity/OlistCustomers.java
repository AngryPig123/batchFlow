package com.batchflow.domain.olist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.batchflow.domain.seed.entity.id
 * fileName       : OlistCustomers
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "OLIST_CUSTOMERS", comment = "고객 도메인 테이블")
public class OlistCustomers {

    @Id
    @Column(name = "CUSTOMER_ID", length = 32, nullable = false) // , comment = "고객 ID (PK)")
    private String customerId;

    @Column(name = "CUSTOMER_UNIQUE_ID", length = 32) // , comment = "고객 고유 식별자 (SCD TYPE 2, 중복 가능)")
    private String customerUniqueId;

    @Column(name = "CUSTOMER_ZIP_CODE_PREFIX") // , comment = "고객 우편번호 Prefix")
    private Integer customerZipCodePrefix;

    @Column(name = "CUSTOMER_CITY", length = 64) // , comment = "고객 도시명")
    private String customerCity;

    @Column(name = "CUSTOMER_STATE", length = 2) // , comment = "고객 주/지역 코드")
    private String customerState;

    @Column(name = "INGESTED_AT", nullable = false) // , comment = "데이터 적재 시각")
    private LocalDateTime ingestedAt;

    @Column(name = "CREATED_AT", nullable = false) // , comment = "레코드 생성 시각")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT") // , comment = "레코드 수정 시각")
    private LocalDateTime updatedAt;
}