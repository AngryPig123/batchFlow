package com.batchflow.bootstrap.dto.insert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : OlistProductCatTransSeedRow
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
public class OlistProductCatTransSeedRow {

    @JsonProperty("product_category_name")
    private String productCategoryName;

    @JsonProperty("product_category_name_english")
    private String productCategoryNameEnglish;

}
