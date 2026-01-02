package com.batchflow.domain.seed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.batchflow.domain.seed.entity.id
 * fileName       : OlistSellers
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
@Table(name = "OLIST_SELLERS", comment = "판매자 도메인 테이블")
public class OlistSellers {

    @Id
    @Column(name = "SELLER_ID", length = 32, nullable = false, comment = "판매자 ID (PK)")
    private String sellerId;

    @Column(name = "SELLER_ZIP_CODE_PREFIX", comment = "판매자 우편번호 Prefix")
    private Integer sellerZipCodePrefix;

    @Column(name = "SELLER_CITY", length = 64, comment = "판매자 도시명")
    private String sellerCity;

    @Column(name = "SELLER_STATE", length = 2, comment = "판매자 주/지역 코드")
    private String sellerState;

    @Column(name = "INGESTED_AT", nullable = false, comment = "데이터 적재 시각")
    private LocalDateTime ingestedAt;

    @Column(name = "CREATED_AT", nullable = false, comment = "레코드 생성 시각")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", comment = "레코드 수정 시각")
    private LocalDateTime updatedAt;
}