package dev.perfectbogus.productservice.service;

import dev.perfectbogus.productservice.dto.BookRequestDTO;
import dev.perfectbogus.productservice.dto.BookResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface BookService {
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(UUID id);
    BookResponseDTO createBook(BookRequestDTO dto);
    BookResponseDTO updateBook(UUID id, BookRequestDTO dto);
    void deleteBook(UUID id);
}

