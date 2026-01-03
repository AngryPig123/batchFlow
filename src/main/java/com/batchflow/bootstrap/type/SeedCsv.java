package com.batchflow.bootstrap.type;

import com.batchflow.bootstrap.dto.insert.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * packageName    : com.batchflow.batch.seed.type
 * fileName       : SeedClassToCsvPath
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@Getter
@AllArgsConstructor
public enum SeedCsv {

    OLIST_GEOLOCATION(OlistGeolocationsSeedRow.class, "/csv/olist/olist_geolocation_dataset.csv"),
    OLIST_PRODUCT_CAT_TRANS(OlistProductCatTransSeedRow.class, "/csv/olist/product_category_name_translation.csv"),
    OLIST_CUSTOMERS(OlistCustomersSeedRow.class, "/csv/olist/olist_customers_dataset.csv"),
    OLIST_SELLERS(OlistSellersSeedRow.class, "/csv/olist/olist_sellers_dataset.csv"),
    OLIST_PRODUCTS(OlistProductsSeedRow.class, "/csv/olist/olist_products_dataset.csv"),
    OLIST_ORDERS(OlistOrdersSeedRow.class, "/csv/olist/olist_orders_dataset.csv"),
    OLIST_ORDER_ITEMS(OlistOrderItemsSeedRow.class, "/csv/olist/olist_order_items_dataset.csv"),
    OLIST_ORDER_PAYMENTS(OlistOrderPaymentsSeedRow.class, "/csv/olist/olist_order_payments_dataset.csv"),
    OLIST_ORDER_REVIEWS(OlistOrderReviewsSeedRow.class, "/csv/olist/olist_order_reviews_dataset.csv");
    private final Class<?> type;
    private final String path;

    private static final Map<Class<?>, SeedCsv> LOOKUP;

    static {
        LOOKUP = Arrays.stream(SeedCsv.values())
                .collect(Collectors.toMap(
                        SeedCsv::getType,
                        Function.identity()
                ));
    }

    public static SeedCsv lookup(Class<?> type) {
        SeedCsv seed = LOOKUP.get(type);
        if (seed == null) {
            throw new IllegalArgumentException("존재하지 않는 seed 타입 : " + type.getName());
        }
        return seed;
    }

}
