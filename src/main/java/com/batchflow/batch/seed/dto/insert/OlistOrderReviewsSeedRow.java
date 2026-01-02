package com.batchflow.batch.seed.dto.insert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : OlistOrderReviewsSeedRow
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
public class OlistOrderReviewsSeedRow {

    @JsonProperty("review_id")
    private String reviewId;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("review_score")
    private Integer reviewScore;

    @JsonProperty("review_comment_title")
    private String reviewCommentTitle;

    @JsonProperty("review_comment_message")
    private String reviewCommentMessage;

    @JsonProperty("review_creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reviewCreationDate;

    @JsonProperty("review_answer_timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reviewAnswerTimestamp;

}
