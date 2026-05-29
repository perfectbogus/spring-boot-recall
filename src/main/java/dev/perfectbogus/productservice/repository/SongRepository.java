package dev.perfectbogus.productservice.repository;

import dev.perfectbogus.productservice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {
    boolean existsSongByName(String name);
}
