package dev.perfectbogus.productservice.service;

import dev.perfectbogus.productservice.dto.EmployeeRequestDTO;
import dev.perfectbogus.productservice.dto.EmployeeResponseDTO;

import java.util.List;
import java.util.UUID;


public interface EmployeeService {
    List<EmployeeResponseDTO> getAll();

    EmployeeResponseDTO getById(UUID id);

    EmployeeResponseDTO create(EmployeeRequestDTO dto);

    EmployeeResponseDTO update(UUID id, EmployeeRequestDTO dto);

    void delete(UUID id);
}
