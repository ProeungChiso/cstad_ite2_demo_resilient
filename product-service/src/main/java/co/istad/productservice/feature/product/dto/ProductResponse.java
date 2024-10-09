package co.istad.productservice.feature.product.dto;

public record ProductResponse(
        String uuid,
        String productName,
        String productDescription,
        String productCategory,
        Double productPrice,
        Integer productQuantity
) {
}
