package dev.perfectbogus.productservice.service;

import dev.perfectbogus.productservice.dto.ProductRequestDTO;
import dev.perfectbogus.productservice.dto.ProductResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(UUID id);
    ProductResponseDTO createProduct(ProductRequestDTO dto);
    ProductResponseDTO updateProduct(UUID id, ProductRequestDTO dto);
    void deleteProduct(UUID id);
    List<ProductResponseDTO> getByCategory(String category);
}
