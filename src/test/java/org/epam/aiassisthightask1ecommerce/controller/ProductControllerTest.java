package org.epam.aiassisthightask1ecommerce.controller;

import org.epam.aiassisthightask1ecommerce.dto.ProductDTO;
import org.epam.aiassisthightask1ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().name("Product Name").price(100.0).build();
        ProductDTO createdProductDTO = ProductDTO.builder().id(1L).name("Product Name").price(100.0).build();

        when(productService.createProduct(productDTO)).thenReturn(createdProductDTO);

        mockMvc.perform(post("/api/products")
                        .contentType("application/json")
                        .content("{\"name\":\"Product Name\",\"price\":100.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product Name"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    void testGetProductById() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().id(1L).name("Product Name").price(100.0).build();

        when(productService.getProductById(1L)).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product Name"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    void testGetProductByIdNotFound() throws Exception {
        when(productService.getProductById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllProducts() throws Exception {
        ProductDTO productDTO1 = ProductDTO.builder().id(1L).name("Product 1").price(50.0).build();
        ProductDTO productDTO2 = ProductDTO.builder().id(2L).name("Product 2").price(150.0).build();
        List<ProductDTO> products = Arrays.asList(productDTO1, productDTO2);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[0].price").value(50.0))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Product 2"))
                .andExpect(jsonPath("$[1].price").value(150.0));
    }

    @Test
    void testUpdateProduct() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().name("Updated Product").price(120.0).build();
        ProductDTO updatedProductDTO = ProductDTO.builder().id(1L).name("Updated Product").price(120.0).build();

        when(productService.updateProduct(1L, productDTO)).thenReturn(updatedProductDTO);

        mockMvc.perform(put("/api/products/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Updated Product\",\"price\":120.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.price").value(120.0));
    }

    @Test
    void testUpdateProductNotFound() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().name("Updated Product").price(120.0).build();

        when(productService.updateProduct(1L, productDTO)).thenReturn(null);

        mockMvc.perform(put("/api/products/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Updated Product\",\"price\":120.0}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteProduct() throws Exception {
        when(productService.deleteProduct(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteProductNotFound() throws Exception {
        when(productService.deleteProduct(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNotFound());
    }
}
