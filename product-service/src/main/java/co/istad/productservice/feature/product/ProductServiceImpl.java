package co.istad.productservice.feature.product;

import co.istad.productservice.domain.Product;
import co.istad.productservice.feature.product.dto.CreateProductRequest;
import co.istad.productservice.feature.product.dto.ProductResponse;
import co.istad.productservice.feature.product.dto.UpdateProductRequest;
import co.istad.productservice.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    ProductResponse returnProductFunction(
            String uuid,
            String productName,
            String productDescription,
            String productCategory,
            Double productPrice,
            Integer productQuantity
    ){
        if(productRepository.findProductByUuid(uuid).isPresent()){
            Product product = productRepository.findProductByUuid(uuid).get();

            product.setProductName(productName);
            product.setProductDescription(productDescription);
            product.setProductCategory(productCategory);
            product.setProductPrice(productPrice);
            product.setProductQuantity(productQuantity);
            productRepository.save(product);
            return productMapper.productToProductResponse(product);
        }else{
            Product product = new Product();

            product.setUuid(uuid);
            product.setProductName(productName);
            product.setProductDescription(productDescription);
            product.setProductCategory(productCategory);
            product.setProductPrice(productPrice);
            product.setProductQuantity(productQuantity);
            productRepository.save(product);
            return productMapper.productToProductResponse(product);
        }

    }

    @Override
    public Page<?> findAllProducts(int page, int size) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sortById);

        Page<Product> products = productRepository.findAll(pageable);

        if(products.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product is Empty!"
            );
        }
        return products.map(productMapper::productToProductResponse);
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        if(productRepository.existsByProductName(request.productName())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product name already exists!"
            );
        }

        String generateUuid = UUID.randomUUID().toString();
        return returnProductFunction(
                generateUuid,
                request.productName(),
                request.productDescription(),
                request.productCategory(),
                request.productPrice(),
                request.productQuantity()
        );
    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {

        Product product = productRepository.findProductByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Product with uuid %s not found", uuid)
                        )
                );
        return productMapper.productToProductResponse(product);
    }

    @Override
    public ProductResponse updateProductByUuid(String uuid, UpdateProductRequest request) {

        if(productRepository.findProductByUuid(uuid).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product Not Found!"
            );
        }

        return returnProductFunction(
                uuid,
                request.productName(),
                request.productDescription(),
                request.productCategory(),
                request.productPrice(),
                request.productQuantity()
        );
    }

    @Override
    public ResponseEntity<?> deleteProductByUuid(String uuid) {
        Product product = productRepository.findProductByUuid(uuid)
                .orElseThrow(
                        ()-> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Product Not Found!"
                        )
                );

        productRepository.delete(product);
        return ResponseEntity.ok("Product deleted successfully!");
    }
}
