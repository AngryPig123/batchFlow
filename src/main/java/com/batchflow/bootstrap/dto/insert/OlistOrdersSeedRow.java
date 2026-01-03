package com.batchflow.bootstrap.dto.insert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : OlistOrdersSeedRow
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class OlistOrdersSeedRow {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("customer_id")
    private String customerId;

    @JsonProperty("order_status")
    private String orderStatus;

    @JsonProperty("order_purchase_timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderPurchaseTimestamp;

    @JsonProperty("order_approved_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderApprovedAt;

    @JsonProperty("order_delivered_carrier_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDeliveredCarrierDate;

    @JsonProperty("order_delivered_customer_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDeliveredCustomerDate;

    @JsonProperty("order_estimated_delivery_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderEstimatedDeliveryDate;

}
