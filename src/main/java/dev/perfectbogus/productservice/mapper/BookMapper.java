package dev.perfectbogus.productservice.mapper;

import dev.perfectbogus.productservice.dto.BookRequestDTO;
import dev.perfectbogus.productservice.dto.BookResponseDTO;
import dev.perfectbogus.productservice.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequestDTO dto) {
        return Book.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .stock(dto.getStock())
                .build();
    }

    public BookResponseDTO toDTO(Book book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .description(book.getDescription())
                .stock(book.getStock())
                .createdAt(book.getCreateAt())
                .updatedAt(book.getUpdateAt())
                .build();
    }
}
