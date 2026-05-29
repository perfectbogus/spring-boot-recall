package dev.perfectbogus.productservice.api;

import dev.perfectbogus.productservice.dto.ProductRequestDTO;
import dev.perfectbogus.productservice.dto.ProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Products", description = "Product catalog operations")
@RequestMapping("/api/v1/products")
public interface ProductApi {

    @Operation(summary = "Get all products")
    @ApiResponse(responseCode = "200", description = "List of products")
    @GetMapping("/")
    ResponseEntity<List<ProductResponseDTO>> getAll();

    @Operation(summary = "Get product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDTO> getById(
            @Parameter(description = "Product ID", example = "c0b25638-eb77-4550-89d7-9cc8d7878b6b")
            @PathVariable UUID id);

    @Operation(summary = "Create a product")
    @ApiResponse(responseCode = "200", description = "Created")
    @ApiResponse(responseCode = "400", description = "Validation Error")
    @PostMapping
    ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO dto);

    @Operation(summary = "Update a product")
    @PutMapping("/{id}")
    ResponseEntity<ProductResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody ProductRequestDTO dto
    );

    @Operation(summary = "Delete a product")
    @ApiResponse(responseCode = "204", description = "Deleted")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "Product ID") @PathVariable UUID id);

}