package com.dariojolo.app.productos;

import com.dariojolo.app.productos.DTOs.ProductRepository;
import com.dariojolo.app.productos.entities.Producto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductoRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindById_thenReturnProduct() {
        Producto p01 = Producto.builder()
                .modelo("Sedan")
                .precioMinimo(1d)
                .precioMaximo(100d)
                .build();
        productRepository.save(p01);

        Producto producto = Producto.builder().build();

        Optional<Producto> found = productRepository.findById(p01.getId());
        if (found.isPresent()) {
            producto = found.get();
        }
        Assertions.assertThat(producto.getPrecioMaximo()).isEqualTo(100d);
    }
}
