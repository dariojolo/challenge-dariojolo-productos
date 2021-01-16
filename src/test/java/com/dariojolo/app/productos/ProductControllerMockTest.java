package com.dariojolo.app.productos;

import com.dariojolo.app.productos.controller.ProductController;
import com.dariojolo.app.productos.entities.Producto;
import com.dariojolo.app.productos.services.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerMockTest {

    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockmvc() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void testGetProduct() throws Exception {
        Mockito.when(service.findProductById(1L)).thenReturn(Producto.builder().id(1L).precioMinimo(111d).build());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testListProducts() throws Exception {
        Mockito.when(service.listAllProduct()).thenReturn(List.of(Producto.builder().id(1L).precioMinimo(111d).build(), Producto.builder().id(2L).modelo("Sedan").precioMinimo(111d).precioMaximo(22222d).build()));

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/products/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].modelo").value("Sedan"));
    }

    @Test
    void testCreateProduct() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"modelo\":\"Sedan\",\"precioMinimo\":\"100d\",\"precioMaximo\":\"10000d\"}"))
                .andExpect(status().isCreated());
    }
}
