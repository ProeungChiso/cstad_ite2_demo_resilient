package co.istad.productservice.feature.product.dto;

import jakarta.validation.constraints.*;

public record CreateProductRequest(
        @NotBlank
        String productName,

        @Size(max = 100)
        String productDescription,

        @NotBlank
        String productCategory,

        @Positive
        Double productPrice,

        @Min(0)
        Integer productQuantity
) {
}
