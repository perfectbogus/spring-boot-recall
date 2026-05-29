package dev.perfectbogus.productservice.api;

import dev.perfectbogus.productservice.dto.BookRequestDTO;
import dev.perfectbogus.productservice.dto.BookResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/books")
public interface BookApi {

    @GetMapping("/")
    ResponseEntity<List<BookResponseDTO>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<BookResponseDTO> getById(@PathVariable UUID id);

    @PostMapping
    ResponseEntity<BookResponseDTO> create(@Valid @RequestBody BookRequestDTO dto);

    @PutMapping("/{id}")
    ResponseEntity<BookResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody BookRequestDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);
}
