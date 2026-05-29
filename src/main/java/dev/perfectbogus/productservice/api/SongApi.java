package dev.perfectbogus.productservice.api;

import dev.perfectbogus.productservice.dto.SongRequestDTO;
import dev.perfectbogus.productservice.dto.SongResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/songs")
public interface SongApi {
    @GetMapping("/")
    ResponseEntity<List<SongResponseDTO>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<SongResponseDTO> getById(@PathVariable UUID id);

    @PostMapping
    ResponseEntity<SongResponseDTO> create(@Valid @RequestBody SongRequestDTO dto);

    @PutMapping("/{id}")
    ResponseEntity<SongResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody SongRequestDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);
}
