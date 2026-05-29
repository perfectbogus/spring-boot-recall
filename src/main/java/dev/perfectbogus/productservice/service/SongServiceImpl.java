package dev.perfectbogus.productservice.service;

import dev.perfectbogus.productservice.dto.SongRequestDTO;
import dev.perfectbogus.productservice.dto.SongResponseDTO;
import dev.perfectbogus.productservice.exception.ResourceNotFoundException;
import dev.perfectbogus.productservice.mapper.SongMapper;
import dev.perfectbogus.productservice.model.Song;
import dev.perfectbogus.productservice.repository.SongRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository repository;
    private final SongMapper mapper;
    @Override
    public List<SongResponseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public SongResponseDTO findById(UUID id) {
        Song existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song not found with id: " + id));
        return mapper.toDTO(existing);
    }

    @Override
    public SongResponseDTO create(SongRequestDTO dto) {
        if (repository.existsSongByName(dto.getName())) throw new IllegalArgumentException("Song already exists with name: " + dto.getName());

        Song created = mapper.toEntity(dto);
        Song saved = repository.save(created);

        return mapper.toDTO(saved);
    }

    @Override
    public SongResponseDTO update(UUID id, SongRequestDTO dto) {
        Song saved = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song not found with id: " + id));
        saved.setName(dto.getName());
        saved.setLength(dto.getLength());
        saved.setArtist(dto.getArtist());

        Song updated = repository.save(saved);
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
