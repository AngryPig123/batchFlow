package com.batchflow.bootstrap.dto.insert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : OlistOrderItemsSeedRow
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
public class OlistOrderItemsSeedRow {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_item_id")
    private Integer orderItemId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("shipping_limit_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shippingLimitDate;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("freight_value")
    private Double freightValue;

}
