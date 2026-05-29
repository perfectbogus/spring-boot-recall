package dev.perfectbogus.productservice.repository;

import dev.perfectbogus.productservice.dto.BookResponseDTO;
import dev.perfectbogus.productservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    boolean existsByName(String name);
}
