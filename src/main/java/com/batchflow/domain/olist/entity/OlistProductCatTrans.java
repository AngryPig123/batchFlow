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
 * fileName       : OlistProductCatTrans
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
@Table(name = "OLIST_PRODUCT_CAT_TRANS") // , comment = "상품 카테고리 번역 마스터 테이블")
public class OlistProductCatTrans {

    @Id
    @Column(name = "PRODUCT_CATEGORY_NAME", length = 60, nullable = false) // , comment = "상품 카테고리 코드(원본)")
    private String productCategoryName;

    @Column(name = "PRODUCT_CATEGORY_NAME_ENGLISH", length = 60) // , comment = "상품 카테고리 영문명")
    private String productCategoryNameEnglish;

    @Column(name = "INGESTED_AT", nullable = false) // , comment = "데이터 적재 시각")
    private LocalDateTime ingestedAt;

    @Column(name = "CREATED_AT", nullable = false) // , comment = "레코드 생성 시각")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT") // , comment = "레코드 수정 시각")
    private LocalDateTime updatedAt;

}