package dev.perfectbogus.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {

    @NotBlank
    @Size(max = 100, message = "Max 100 chars")
    private String name;

    @Size(max = 500)
    private String description;

    @NotNull
    @Min(0)
    private Integer stock;

}
