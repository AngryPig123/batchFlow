package com.batchflow.domain.olist.entity;

import com.batchflow.domain.olist.entity.id.OlistOrderPaymentsPk;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.batchflow.domain.seed.entity.id
 * fileName       : OlistOrderPayments
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
@Table(name = "OLIST_ORDER_PAYMENTS") // , comment = "주문 결제 정보 테이블 (주문당 N건 가능)")
public class OlistOrderPayments {

    @EmbeddedId
    private OlistOrderPaymentsPk id;

    @Column(name = "PAYMENT_TYPE", length = 20) // , comment = "결제 수단")
    private String paymentType;

    @Column(name = "PAYMENT_INSTALLMENTS") // , comment = "할부 개월 수")
    private Integer paymentInstallments;

    @Column(name = "PAYMENT_VALUE") // , comment = "결제 금액")
    private Double paymentValue;

    @Column(name = "INGESTED_AT", nullable = false) // , comment = "데이터 적재 시각")
    private LocalDateTime ingestedAt;

    @Column(name = "CREATED_AT", nullable = false) // , comment = "레코드 생성 시각")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT") // , comment = "레코드 수정 시각")
    private LocalDateTime updatedAt;
}