package dev.perfectbogus.productservice.mapper;

import dev.perfectbogus.productservice.dto.SongRequestDTO;
import dev.perfectbogus.productservice.dto.SongResponseDTO;
import dev.perfectbogus.productservice.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {

    public SongResponseDTO toDTO(Song song) {
        return SongResponseDTO.builder()
                .id(song.getId())
                .name(song.getName())
                .length(song.getLength())
                .artist(song.getArtist())
                .build();
    }

    public Song toEntity(SongRequestDTO dto) {
        return Song.builder()
                .name(dto.getName())
                .length(dto.getLength())
                .artist(dto.getArtist())
                .build();
    }
}
