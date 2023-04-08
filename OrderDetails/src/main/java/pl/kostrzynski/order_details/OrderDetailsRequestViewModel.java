package pl.kostrzynski.order_details;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

record OrderDetailsRequestViewModel(
        @NotNull String courier,
        @NotNull String region,
        @NotEmpty List<String> products
) {
}
