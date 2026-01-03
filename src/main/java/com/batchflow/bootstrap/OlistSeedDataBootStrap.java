package com.batchflow.bootstrap;

import com.batchflow.bootstrap.dto.SeedLoadResult;
import com.batchflow.bootstrap.dto.insert.*;
import com.batchflow.bootstrap.mapper.OlistSeedMapper;
import com.batchflow.bootstrap.service.OlistSeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.batchflow.batch.seed.bootstrap
 * fileName       : OlistSeedDataBootStrap
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "seed.load.enabled",
        havingValue = "true"
)
public class OlistSeedDataBootStrap implements CommandLineRunner {

    private final OlistSeedService olistSeedService;

    @Override
    public void run(String... args) {

        log.info("run olist seed bootstrap......");

        SeedLoadResult customerLoad = olistSeedService.load(OlistCustomersSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistCustomer;
                }
        );
        log.info("customerLoad = {}", customerLoad);

        SeedLoadResult geolocationLoad = olistSeedService.load(OlistGeolocationsSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistGeolocation;
                }
        );
        log.info("geolocationLoad = {}", geolocationLoad);

        SeedLoadResult orderItemLoad = olistSeedService.load(OlistOrderItemsSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistOrderItem;
                }
        );
        log.info("orderItemLoad = {}", orderItemLoad);

        SeedLoadResult orderPaymentLoad = olistSeedService.load(OlistOrderPaymentsSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistOrderPayment;
                }
        );
        log.info("orderPaymentLoad = {}", orderPaymentLoad);

        SeedLoadResult orderReviewLoad = olistSeedService.load(OlistOrderReviewsSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistOrderReview;
                }
        );
        log.info("orderReviewLoad = {}", orderReviewLoad);

        SeedLoadResult orderLoad = olistSeedService.load(OlistOrdersSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistOrder;
                }
        );
        log.info("orderLoad = {}", orderLoad);

        SeedLoadResult productCatTransLoad = olistSeedService.load(OlistProductCatTransSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistProductCatTran;
                }
        );
        log.info("productCatTransLoad = {}", productCatTransLoad);

        SeedLoadResult productLoad = olistSeedService.load(OlistProductsSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistProduct;
                }
        );
        log.info("productLoad = {}", productLoad);

        SeedLoadResult sellerLoad = olistSeedService.load(OlistSellersSeedRow.class, session -> {
                    OlistSeedMapper mapper = session.getMapper(OlistSeedMapper.class);
                    return mapper::insertOlistSeller;
                }
        );
        log.info("sellerLoad = {}", sellerLoad);

        log.info("olist seed bootstrap success");

    }

}
