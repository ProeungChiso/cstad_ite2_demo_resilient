package co.istad.productservice.feature.product;

import co.istad.productservice.feature.product.dto.CreateProductRequest;
import co.istad.productservice.feature.product.dto.ProductResponse;
import co.istad.productservice.feature.product.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    Page<?> findAllProducts(int page, int size);
    ProductResponse createProduct(CreateProductRequest createProductRequest);
    ProductResponse findProductByUuid(String uuid);
    ProductResponse updateProductByUuid(String uuid, UpdateProductRequest request);
    ResponseEntity<?> deleteProductByUuid(String uuid);

}
