package co.istad.productservice.feature.product;

import co.istad.productservice.feature.product.dto.CreateProductRequest;
import co.istad.productservice.feature.product.dto.ProductResponse;
import co.istad.productservice.feature.product.dto.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<?> findAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.findAllProducts(page, size);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    ProductResponse findProductByUuid(@PathVariable String uuid) {
        return productService.findProductByUuid(uuid);
    }

    @PutMapping("/update/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ProductResponse updateProductByUuid(
            @PathVariable String uuid,
            @RequestBody UpdateProductRequest request
    ){
        return productService.updateProductByUuid(uuid, request);
    }

    @DeleteMapping("/delete/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> deleteProductByUuid(@PathVariable String uuid) {
        return productService.deleteProductByUuid(uuid);
    }

    @PostMapping("/create")
    ProductResponse createProduct(@RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }
}
