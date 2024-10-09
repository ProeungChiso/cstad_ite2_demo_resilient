package co.istad.productservice.feature.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductRequest(
        @NotBlank
        String productName,

        @Size(max = 100)
        String productDescription,

        String productCategory,

        @Positive
        Double productPrice,

        @Min(0)
        Integer productQuantity
) {
}
