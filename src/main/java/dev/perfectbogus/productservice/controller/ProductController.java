package dev.perfectbogus.productservice.controller;

import dev.perfectbogus.productservice.api.ProductApi;
import dev.perfectbogus.productservice.dto.ProductRequestDTO;
import dev.perfectbogus.productservice.dto.ProductResponseDTO;
import dev.perfectbogus.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductApi {
    private final ProductService service;

    @Override
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }


    @Override
    public ResponseEntity<ProductResponseDTO> getById(UUID id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    public ResponseEntity<List<ProductResponseDTO>> getByCategory(String category) {
        return ResponseEntity.ok(service.getByCategory(category));
    }

    @Override
    public ResponseEntity<ProductResponseDTO> create(ProductRequestDTO dto) {
        ProductResponseDTO created = service.createProduct(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public ResponseEntity<ProductResponseDTO> update(UUID id, ProductRequestDTO dto) {
        return ResponseEntity.ok(service.updateProduct(id, dto));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
