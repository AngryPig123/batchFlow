package com.batchflow.batch.seed.dto.insert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : OlistSellersSeedRow
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
public class OlistSellersSeedRow {

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("seller_zip_code_prefix")
    private Integer sellerZipCodePrefix;

    @JsonProperty("seller_city")
    private String sellerCity;

    @JsonProperty("seller_state")
    private String sellerState;
}
