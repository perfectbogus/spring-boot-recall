package dev.perfectbogus.productservice.api;

import dev.perfectbogus.productservice.dto.EmployeeRequestDTO;
import dev.perfectbogus.productservice.dto.EmployeeResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/employees")
public interface EmployeeApi {

    @GetMapping("/")
    ResponseEntity<List<EmployeeResponseDTO>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<EmployeeResponseDTO> getById(@PathVariable UUID id);

    @PostMapping
    ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeRequestDTO dto);

    @PutMapping("/{id}")
    ResponseEntity<EmployeeResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody EmployeeRequestDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);
}
