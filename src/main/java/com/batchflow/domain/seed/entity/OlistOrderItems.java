package com.batchflow.domain.seed.entity;

import com.batchflow.domain.seed.entity.id.OlistOrderItemsPk;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.batchflow.domain.seed.entity.id
 * fileName       : OlistOrderItems
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
@Table(name = "OLIST_ORDER_ITEMS", comment = "주문 상품 라인 테이블 (주문별 상품 구성)")
public class OlistOrderItems {

    @EmbeddedId
    private OlistOrderItemsPk id;

    @Column(name = "PRODUCT_ID", length = 32, nullable = false, comment = "상품 ID")
    private String productId;

    @Column(name = "SELLER_ID", length = 32, nullable = false, comment = "판매자 ID")
    private String sellerId;

    @Column(name = "SHIPPING_LIMIT_DATE", comment = "출고 마감 기한")
    private LocalDateTime shippingLimitDate;

    @Column(name = "PRICE", comment = "상품 가격")
    private Double price;

    @Column(name = "FREIGHT_VALUE", comment = "배송비")
    private Double freightValue;

    @Column(name = "INGESTED_AT", nullable = false, comment = "데이터 적재 시각")
    private LocalDateTime ingestedAt;

    @Column(name = "CREATED_AT", nullable = false, comment = "레코드 생성 시각")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", comment = "레코드 수정 시각")
    private LocalDateTime updatedAt;
}