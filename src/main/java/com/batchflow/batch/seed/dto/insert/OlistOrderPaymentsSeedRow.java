package com.batchflow.batch.seed.dto.insert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : OlistOrderPaymentsSeedRow
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
public class OlistOrderPaymentsSeedRow {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("payment_sequential")
    private Integer paymentSequential;

    @JsonProperty("payment_type")
    private String paymentType;

    @JsonProperty("payment_installments")
    private Integer paymentInstallments;

    @JsonProperty("payment_value")
    private Double paymentValue;

}
