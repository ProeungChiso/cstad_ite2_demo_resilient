package co.istad.productservice.mapper;

import co.istad.productservice.domain.Product;
import co.istad.productservice.feature.product.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "uuid", target = "uuid"),
            @Mapping(source = "productName", target = "productName"),
            @Mapping(source = "productDescription", target = "productDescription"),
            @Mapping(source = "productCategory", target = "productCategory"),
            @Mapping(source = "productPrice", target = "productPrice"),
            @Mapping(source = "productQuantity", target = "productQuantity")
    })
    ProductResponse productToProductResponse(Product product);
}
