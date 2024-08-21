package org.epam.aiassisthightask1ecommerce.mapper;

import org.epam.aiassisthightask1ecommerce.dto.ProductDTO;
import org.epam.aiassisthightask1ecommerce.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = new ProductMapper();
    }

    @Test
    void testToDTO() {
        Product product = Product.builder()
                .id(1L)
                .name("Test Product")
                .description("Product Description")
                .price(99.99)
                .stockQuantity(10)
                .category("Electronics")
                .build();

        ProductDTO productDTO = productMapper.toDTO(product);

        assertNotNull(productDTO);
        assertEquals(1L, productDTO.getId());
        assertEquals("Test Product", productDTO.getName());
        assertEquals("Product Description", productDTO.getDescription());
        assertEquals(99.99, productDTO.getPrice());
        assertEquals(10, productDTO.getStockQuantity());
        assertEquals("Electronics", productDTO.getCategory());
    }

    @Test
    void testToEntity() {
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Test Product")
                .description("Product Description")
                .price(99.99)
                .stockQuantity(10)
                .category("Electronics")
                .build();

        Product product = productMapper.toEntity(productDTO);

        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("Product Description", product.getDescription());
        assertEquals(99.99, product.getPrice());
        assertEquals(10, product.getStockQuantity());
        assertEquals("Electronics", product.getCategory());
    }
}