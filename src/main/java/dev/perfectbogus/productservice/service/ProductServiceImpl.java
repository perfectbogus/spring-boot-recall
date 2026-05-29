package dev.perfectbogus.productservice.service;

import dev.perfectbogus.productservice.dto.ProductRequestDTO;
import dev.perfectbogus.productservice.dto.ProductResponseDTO;
import dev.perfectbogus.productservice.exception.ResourceNotFoundException;
import dev.perfectbogus.productservice.mapper.ProductMapper;
import dev.perfectbogus.productservice.model.Product;
import dev.perfectbogus.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        log.info("Fetching all products");
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDTO getProductById(UUID id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapper.toDTO(product);
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        if (repository.existsByName(dto.getName()))
            throw new IllegalArgumentException("Product already exists: " + dto.getName());
        Product saved = repository.save(mapper.toEntity(dto));
        log.info("Created product with id: {}", saved.getId());
        return mapper.toDTO(saved);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO dto) {
        Product existing = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not found with id: " + id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setStock(dto.getStock());
        existing.setCategory(dto.getCategory());

        Product updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Product not found");

        repository.deleteById(id);
        log.info("Deleted product with id: {}", id);
    }

    @Override
    public List<ProductResponseDTO> getByCategory(String category) {
        return repository.findByCategory(category)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
