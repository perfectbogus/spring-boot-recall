package dev.perfectbogus.productservice.controller;

import dev.perfectbogus.productservice.api.SongApi;
import dev.perfectbogus.productservice.dto.SongRequestDTO;
import dev.perfectbogus.productservice.dto.SongResponseDTO;
import dev.perfectbogus.productservice.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SongController implements SongApi {
    private final SongService service;

    @Override
    public ResponseEntity<List<SongResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<SongResponseDTO> getById(UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<SongResponseDTO> create(SongRequestDTO dto) {
        SongResponseDTO created = service.create(dto);

        URI location = UriComponentsBuilder.fromPath("/{id}")
                .buildAndExpand(created)
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public ResponseEntity<SongResponseDTO> update(UUID id, SongRequestDTO dto) {
        SongResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
