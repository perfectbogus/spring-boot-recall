package dev.perfectbogus.productservice.repository;

import dev.perfectbogus.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByCategory(String category);

    List<Product> findByPriceLessThanEqual(BigDecimal maxPrice);

    boolean existsByName(String name);

    @Query("select p from Product p where p.stock > 0 and p.category = :cat")
    List<Product> findAvailableByCategory(@Param("cat") String category);

    Page<Product> findByCategory(String category, Pageable pageable);
}
