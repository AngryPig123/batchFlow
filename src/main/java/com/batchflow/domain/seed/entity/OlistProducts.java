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
 * fileName       : OlistProducts
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
@Table(name = "OLIST_PRODUCTS", comment = "상품 도메인 테이블")
public class OlistProducts {

    @Id
    @Column(name = "PRODUCT_ID", length = 32, nullable = false, comment = "상품 ID (PK)")
    private String productId;

    @Column(name = "PRODUCT_CATEGORY_NAME", length = 60, comment = "상품 카테고리 코드")
    private String productCategoryName;

    @Column(name = "PRODUCT_NAME_LENGHT", comment = "상품명 길이")
    private Integer productNameLenght;

    @Column(name = "PRODUCT_DESCRIPTION_LENGHT", comment = "상품 설명 길이")
    private Integer productDescriptionLenght;

    @Column(name = "PRODUCT_PHOTOS_QTY", comment = "상품 이미지 개수")
    private Integer productPhotosQty;

    @Column(name = "PRODUCT_WEIGHT_G", comment = "상품 무게(g)")
    private Double productWeightG;

    @Column(name = "PRODUCT_LENGTH_CM", comment = "상품 길이(cm)")
    private Double productLengthCm;

    @Column(name = "PRODUCT_HEIGHT_CM", comment = "상품 높이(cm)")
    private Double productHeightCm;

    @Column(name = "PRODUCT_WIDTH_CM", comment = "상품 너비(cm)")
    private Double productWidthCm;

    @Column(name = "INGESTED_AT", nullable = false, comment = "데이터 적재 시각")
    private LocalDateTime ingestedAt;

    @Column(name = "CREATED_AT", nullable = false, comment = "레코드 생성 시각")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", comment = "레코드 수정 시각")
    private LocalDateTime updatedAt;
}