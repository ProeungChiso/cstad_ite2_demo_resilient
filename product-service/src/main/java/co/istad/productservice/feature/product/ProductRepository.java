package co.istad.productservice.feature.product;

import co.istad.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByUuid(String uuid);
    Boolean existsByProductName(String productName);
}
