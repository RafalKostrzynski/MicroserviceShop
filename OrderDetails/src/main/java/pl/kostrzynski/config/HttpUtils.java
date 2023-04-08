package pl.kostrzynski.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpUtils {
    private static final String API = "/v1/api/";

    public static final String BEARER = "Bearer ";
    public static final String HTTPS = "https";
    public static final String SHIPMENT_SERVICE = "shipment-service";
    public static final String PRODUCT_SERVICE = "product-service";
    public static final String PRODUCT_PRICE_PATH = API + "product/price";
    public static final String SHIPMENT_COURIER_PATH = API + "shipment/courier";
    public static final String SHIPMENT_REGION_PATH = API + "shipment/region";

}
