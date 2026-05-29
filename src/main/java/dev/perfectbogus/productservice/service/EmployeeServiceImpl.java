package dev.perfectbogus.productservice.service;

import dev.perfectbogus.productservice.dto.EmployeeRequestDTO;
import dev.perfectbogus.productservice.dto.EmployeeResponseDTO;
import dev.perfectbogus.productservice.exception.ResourceNotFoundException;
import dev.perfectbogus.productservice.mapper.EmployeeMapper;
import dev.perfectbogus.productservice.model.Employee;
import dev.perfectbogus.productservice.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public List<EmployeeResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public EmployeeResponseDTO getById(UUID id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee not found with id: " + id));
        return mapper.toDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO create(EmployeeRequestDTO dto) {
        if (repository.existsEmployeeByName(dto.getName()))
            throw new IllegalArgumentException("Employee already exists: " + dto.getName());

        Employee saved = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO update(UUID id, EmployeeRequestDTO dto) {
        Employee exists = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id: " + id)
        );

        exists.setName(dto.getName());
        exists.setPayment(dto.getPayment());

        Employee updated = repository.save(exists);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Employee not found with id: " + id);

        repository.deleteById(id);
    }
}
