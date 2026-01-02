package com.batchflow.domain.seed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.batchflow.domain.seed.entity.id
 * fileName       : OlistOrderReviews
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "OLIST_ORDER_REVIEWS", comment = "주문 리뷰 테이블")
public class OlistOrderReviews {

    @Id
    @Column(name = "REVIEW_ID", length = 32, nullable = false, comment = "리뷰 ID (PK)")
    private String reviewId;

    @Column(name = "ORDER_ID", length = 32, nullable = false, comment = "주문 ID")
    private String orderId;

    @Column(name = "REVIEW_SCORE", comment = "리뷰 평점 (1~5)")
    private Integer reviewScore;

    @Column(name = "REVIEW_COMMENT_TITLE", length = 100, comment = "리뷰 제목")
    private String reviewCommentTitle;

    @Column(name = "REVIEW_COMMENT_MESSAGE", length = 4000, comment = "리뷰 내용")
    private String reviewCommentMessage;

    @Column(name = "REVIEW_CREATION_DATE", comment = "리뷰 작성 시각")
    private LocalDateTime reviewCreationDate;

    @Column(name = "REVIEW_ANSWER_TIMESTAMP", comment = "리뷰 답변 시각")
    private LocalDateTime reviewAnswerTimestamp;

    @Column(name = "INGESTED_AT", nullable = false, comment = "데이터 적재 시각")
    private LocalDateTime ingestedAt;

    @Column(name = "CREATED_AT", nullable = false, comment = "레코드 생성 시각")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", comment = "레코드 수정 시각")
    private LocalDateTime updatedAt;
}