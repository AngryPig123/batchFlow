package com.batchflow.bootstrap.dto.insert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : OlistGeolocationSeedRow
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
public class OlistGeolocationsSeedRow {

    @JsonProperty("geolocation_zip_code_prefix")
    private String zipCodePrefix;

    @JsonProperty("geolocation_lat")
    private Double lat;

    @JsonProperty("geolocation_lng")
    private Double lng;

    @JsonProperty("geolocation_city")
    private String city;

    @JsonProperty("geolocation_state")
    private String state;

}
