package com.batchflow.batch.seed.dto.insert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : OlistProductsSeedRow
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
public class OlistProductsSeedRow {

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("product_category_name")
    private String productCategoryName;

    @JsonProperty("product_name_lenght")
    private Integer productNameLenght;

    @JsonProperty("product_description_lenght")
    private Integer productDescriptionLenght;

    @JsonProperty("product_photos_qty")
    private Integer productPhotosQty;

    @JsonProperty("product_weight_g")
    private Double productWeightG;

    @JsonProperty("product_length_cm")
    private Double productLengthCm;

    @JsonProperty("product_height_cm")
    private Double productHeightCm;

    @JsonProperty("product_width_cm")
    private Double productWidthCm;

}
