package pl.kostrzynski.order_details;


import pl.kostrzynski.datatype.courier.Courier;
import pl.kostrzynski.datatype.region.Region;

import java.util.List;

record OrderDetailsRequest(
        Courier courier,
        Region region,
        List<String> products
) {
}
