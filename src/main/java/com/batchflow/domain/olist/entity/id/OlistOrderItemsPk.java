package com.batchflow.domain.olist.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * packageName    : com.batchflow.domain.seed.entity.id
 * fileName       : OlistOrderItemsPk
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class OlistOrderItemsPk implements Serializable {

    @Column(name = "ORDER_ID", length = 32, nullable = false)
    private String orderId;

    @Column(name = "ORDER_ITEM_ID", nullable = false)
    private Integer orderItemId;

}
