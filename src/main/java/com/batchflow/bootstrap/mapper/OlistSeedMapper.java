package com.batchflow.bootstrap.mapper;

import com.batchflow.bootstrap.dto.insert.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName    : com.batchflow.batch.seed.mapper
 * fileName       : OlistSeedMapper
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Mapper
public interface OlistSeedMapper {

    void insertOlistCustomer(OlistCustomersSeedRow customer);

    void insertOlistGeolocation(OlistGeolocationsSeedRow geolocation);

    void insertOlistOrderItem(OlistOrderItemsSeedRow orderItem);

    void insertOlistOrderPayment(OlistOrderPaymentsSeedRow orderPayment);

    void insertOlistOrderReview(OlistOrderReviewsSeedRow orderReview);

    void insertOlistOrder(OlistOrdersSeedRow order);

    void insertOlistProductCatTran(OlistProductCatTransSeedRow productCatTran);

    void insertOlistProduct(OlistProductsSeedRow product);

    void insertOlistSeller(OlistSellersSeedRow seller);

}
