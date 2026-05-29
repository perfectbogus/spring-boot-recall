package dev.perfectbogus.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDTO {

    private UUID id;
    private String name;
    private String description;
    private Integer stock;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
