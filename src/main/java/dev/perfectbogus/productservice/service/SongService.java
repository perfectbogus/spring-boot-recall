package dev.perfectbogus.productservice.service;

import dev.perfectbogus.productservice.dto.SongRequestDTO;
import dev.perfectbogus.productservice.dto.SongResponseDTO;
import dev.perfectbogus.productservice.model.Song;

import java.util.List;
import java.util.UUID;

public interface SongService {
    List<SongResponseDTO> findAll();
    SongResponseDTO findById(UUID id);
    SongResponseDTO create(SongRequestDTO dto);
    SongResponseDTO update(UUID id, SongRequestDTO dto);
    void delete(UUID id);
}
