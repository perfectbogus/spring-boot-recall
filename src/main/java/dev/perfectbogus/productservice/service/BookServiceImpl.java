package dev.perfectbogus.productservice.service;

import dev.perfectbogus.productservice.dto.BookRequestDTO;
import dev.perfectbogus.productservice.dto.BookResponseDTO;
import dev.perfectbogus.productservice.exception.ResourceNotFoundException;
import dev.perfectbogus.productservice.mapper.BookMapper;
import dev.perfectbogus.productservice.model.Book;
import dev.perfectbogus.productservice.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookMapper mapper;


    @Override
    public List<BookResponseDTO> getAllBooks() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public BookResponseDTO getBookById(UUID id) {
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return mapper.toDTO(book);
    }

    @Override
    @Transactional
    public BookResponseDTO createBook(BookRequestDTO dto) {
        if (repository.existsByName((dto.getName())))
            throw new IllegalArgumentException("Book already exists: " + dto.getName());

        Book saved = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }

    @Override
    @Transactional
    public BookResponseDTO updateBook(UUID id, BookRequestDTO dto) {
        Book existing = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book not found with id: " + id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setStock(dto.getStock());

        Book saved = repository.save(existing);
        return mapper.toDTO(saved);
    }

    @Override
    @Transactional
    public void deleteBook(UUID id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Book not found with id: " + id);

        repository.deleteById(id);
    }
}
