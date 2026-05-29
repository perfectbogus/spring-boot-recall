package dev.perfectbogus.productservice.controller;

import dev.perfectbogus.productservice.api.BookApi;
import dev.perfectbogus.productservice.dto.BookRequestDTO;
import dev.perfectbogus.productservice.dto.BookResponseDTO;
import dev.perfectbogus.productservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController implements BookApi {

    private final BookService service;

    @Override
    public ResponseEntity<List<BookResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @Override
    public ResponseEntity<BookResponseDTO> getById(UUID id) {
        return ResponseEntity.ok(service.getBookById(id));
    }

    @Override
    public ResponseEntity<BookResponseDTO> create(BookRequestDTO dto) {
        BookResponseDTO created = service.createBook(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public ResponseEntity<BookResponseDTO> update(UUID id, BookRequestDTO dto) {
        return ResponseEntity.ok(service.updateBook(id, dto));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        return ResponseEntity.noContent().build();
    }
}
