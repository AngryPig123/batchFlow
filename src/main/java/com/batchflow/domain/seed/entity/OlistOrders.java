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
 * fileName       : OlistOrders
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
@Table(name = "OLIST_ORDERS", comment = "주문 도메인 테이블 (주문 단위)")
public class OlistOrders {

    @Id
    @Column(name = "ORDER_ID", length = 32, nullable = false, comment = "주문 ID (PK)")
    private String orderId;

    @Column(name = "CUSTOMER_ID", length = 32, nullable = false, comment = "주문 고객 ID")
    private String customerId;

    @Column(name = "ORDER_STATUS", length = 20, nullable = false, comment = "주문 상태")
    private String orderStatus;

    @Column(name = "ORDER_PURCHASE_TIMESTAMP", comment = "주문 생성 시각")
    private LocalDateTime orderPurchaseTimestamp;

    @Column(name = "ORDER_APPROVED_AT", comment = "결제 승인 시각")
    private LocalDateTime orderApprovedAt;

    @Column(name = "ORDER_DELIVERED_CARRIER_DATE", comment = "배송사 전달 시각")
    private LocalDateTime orderDeliveredCarrierDate;

    @Column(name = "ORDER_DELIVERED_CUSTOMER_DATE", comment = "고객 배송 완료 시각")
    private LocalDateTime orderDeliveredCustomerDate;

    @Column(name = "ORDER_ESTIMATED_DELIVERY_DATE", comment = "배송 예정 시각")
    private LocalDateTime orderEstimatedDeliveryDate;

    @Column(name = "INGESTED_AT", nullable = false, comment = "데이터 적재 시각")
    private LocalDateTime ingestedAt;

    @Column(name = "CREATED_AT", nullable = false, comment = "레코드 생성 시각")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", comment = "레코드 수정 시각")
    private LocalDateTime updatedAt;
}