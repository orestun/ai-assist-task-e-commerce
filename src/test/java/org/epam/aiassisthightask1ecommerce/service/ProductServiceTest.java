package org.epam.aiassisthightask1ecommerce.service;

import org.epam.aiassisthightask1ecommerce.dto.ProductDTO;
import org.epam.aiassisthightask1ecommerce.entity.Product;
import org.epam.aiassisthightask1ecommerce.mapper.ProductMapper;
import org.epam.aiassisthightask1ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        ProductDTO productDTO = ProductDTO.builder().name("Product Name").price(100.0).build();
        Product product = Product.builder().name("Product Name").price(100.0).build();
        Product savedProduct = Product.builder().id(1L).name("Product Name").price(100.0).build();
        ProductDTO savedProductDTO = ProductDTO.builder().id(1L).name("Product Name").price(100.0).build();

        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(productMapper.toDTO(savedProduct)).thenReturn(savedProductDTO);

        ProductDTO result = productService.createProduct(productDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Product Name", result.getName());
        assertEquals(100.0, result.getPrice());
    }

    @Test
    void testGetProductById() {
        Product product = Product.builder().id(1L).name("Product Name").price(100.0).build();
        ProductDTO productDTO = ProductDTO.builder().id(1L).name("Product Name").price(100.0).build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Product Name", result.getName());
        assertEquals(100.0, result.getPrice());
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ProductDTO result = productService.getProductById(1L);

        assertNull(result);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = Product.builder().id(1L).name("Product 1").price(50.0).build();
        Product product2 = Product.builder().id(2L).name("Product 2").price(150.0).build();
        ProductDTO productDTO1 = ProductDTO.builder().id(1L).name("Product 1").price(50.0).build();
        ProductDTO productDTO2 = ProductDTO.builder().id(2L).name("Product 2").price(150.0).build();

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        when(productMapper.toDTO(product1)).thenReturn(productDTO1);
        when(productMapper.toDTO(product2)).thenReturn(productDTO2);

        var result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals("Product 2", result.get(1).getName());
    }

    @Test
    void testUpdateProduct() {
        ProductDTO productDTO = ProductDTO.builder().name("Updated Product").price(120.0).build();
        Product product = Product.builder().id(1L).name("Updated Product").price(120.0).build();
        ProductDTO updatedProductDTO = ProductDTO.builder().id(1L).name("Updated Product").price(120.0).build();

        when(productRepository.existsById(1L)).thenReturn(true);
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(updatedProductDTO);

        ProductDTO result = productService.updateProduct(1L, productDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Product", result.getName());
        assertEquals(120.0, result.getPrice());
    }

    @Test
    void testUpdateProductNotFound() {
        ProductDTO productDTO = ProductDTO.builder().name("Updated Product").price(120.0).build();

        when(productRepository.existsById(1L)).thenReturn(false);

        ProductDTO result = productService.updateProduct(1L, productDTO);

        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);

        boolean result = productService.deleteProduct(1L);

        assertTrue(result);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProductNotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        boolean result = productService.deleteProduct(1L);

        assertFalse(result);
        verify(productRepository, never()).deleteById(1L);
    }
}