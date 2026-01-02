package com.batchflow.batch.seed.dto.insert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : OlistCustomersSeedRow
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
public class OlistCustomersSeedRow {

    @JsonProperty("customer_id")
    private String customerId;

    @JsonProperty("customer_unique_id")
    private String customerUniqueId;

    @JsonProperty("customer_zip_code_prefix")
    private Integer customerZipCodePrefix;

    @JsonProperty("customer_city")
    private String customerCity;

    @JsonProperty("customer_state")
    private String customerState;

}