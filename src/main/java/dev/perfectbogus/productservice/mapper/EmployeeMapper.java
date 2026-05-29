package dev.perfectbogus.productservice.mapper;

import dev.perfectbogus.productservice.dto.EmployeeRequestDTO;
import dev.perfectbogus.productservice.dto.EmployeeResponseDTO;
import dev.perfectbogus.productservice.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequestDTO dto) {
        return Employee.builder()
                .name(dto.getName())
                .name(dto.getName())
                .build();
    }

    public EmployeeResponseDTO toDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }
}
