package com.batchflow.batch.fact.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * packageName    : com.batchflow.batch.fact.entity
 * fileName       : FactOrderItemEntity
 * author         : AngryPig123
 * date           : 26. 1. 4.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 4.        AngryPig123       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FactOrderItemEntity {

    private LocalDateTime aggregateAt;
    private String orderId;
    private Integer orderItemId;
    private String sellerId;
    private String customerId;
    private String productId;
    private LocalDateTime purchaseDateKey;
    private LocalDateTime approveDateKey;
    private LocalDateTime deliveredDateKey;
    private BigDecimal gmv;
    private BigDecimal freight;
    private BigDecimal revenue;
    private String orderStatus;
    private String isDeliveredFlag;
}
