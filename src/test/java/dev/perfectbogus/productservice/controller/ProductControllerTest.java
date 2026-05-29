package dev.perfectbogus.productservice.controller;

import dev.perfectbogus.productservice.dto.ProductRequestDTO;
import dev.perfectbogus.productservice.dto.ProductResponseDTO;
import dev.perfectbogus.productservice.exception.ResourceNotFoundException;
import dev.perfectbogus.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    @Autowired
    private ObjectMapper objectmapper;

    @Test
    void getById_ShouldReturn200_WhenProductExists() throws Exception {
        UUID id = UUID.randomUUID();
        ProductResponseDTO product = ProductResponseDTO.builder()
                .id(id)
                .name("Laptop")
                .price(new BigDecimal("999.99")).build();

        Mockito.when(service.getProductById(id)).thenReturn(product);

        mockMvc.perform(get("/api/v1/products/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.price").value(999.99));
    }

    @Test
    void create_ShouldReturn400_WhenNameIsMissing() throws Exception {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setPrice(new BigDecimal("10.00"));

        mockMvc.perform(
                post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectmapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.name").value("Name is required")
        );
    }

    @Test
    void getById_ShouldReturn404_WhenNotFound() throws Exception {
        final UUID id = UUID.randomUUID();
        Mockito.when(service.getProductById(id))
                .thenThrow(new ResourceNotFoundException("Product not found: " + id));

        mockMvc.perform(get("/api/v1/products/" + id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"));
    }


}