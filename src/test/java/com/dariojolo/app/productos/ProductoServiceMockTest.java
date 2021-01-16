package com.dariojolo.app.productos;

import com.dariojolo.app.productos.DTOs.ProductRepository;
import com.dariojolo.app.productos.entities.PreciosValidos;
import com.dariojolo.app.productos.entities.Producto;
import com.dariojolo.app.productos.services.ProductService;
import com.dariojolo.app.productos.services.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductoServiceMockTest {

    @Mock
    private ProductRepository dao;
    private ProductService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new ProductServiceImpl(dao);

        List<Producto> listado = new ArrayList<>();
        listado.add(Producto.builder().id(1L).modelo("Sedan").precioMinimo(1d).precioMaximo(1111d).build());
        listado.add(Producto.builder().id(2L).modelo("Familiar").precioMinimo(2d).precioMaximo(222d).build());

        Producto p1 = Producto.builder()
                .id(3L)
                .modelo("Coupe")
                .precioMinimo(22222d)
                .precioMaximo(555555555555d)
                .build();

        Mockito.when(dao.findById(3L))
                .thenReturn(Optional.of(p1));
        Mockito.when(dao.save(p1))
                .thenReturn(p1);
        Mockito.when(dao.save(Producto.builder().id(5L).precioMinimo(111d).build()))
                .thenReturn(Producto.builder().id(5L).precioMinimo(111d).build());
        Mockito.when(dao.findAll())
                .thenReturn(listado);
        Mockito.when(dao.findById(4L))
                .thenReturn(Optional.ofNullable(null));
        Mockito.when(dao.findPrecioMinimoByModelo("Sedan"))
                .thenReturn(100d);
        Mockito.when(dao.findPrecioMaximoByModelo("Sedan"))
                .thenReturn(10000d);
    }

    @Test
    public void whenValidFindProductByID_ThenReturnProduct() {
        Producto found = service.findProductById(3L);
        Assertions.assertThat(found.getModelo().equals("Coupe"));
    }

    @Test
    public void WhenCreateProduct_ThenReturnNewProduct() {
        Producto updateProducto = service.createProduct(Producto.builder().id(5L).precioMinimo(111d).build());
        Assertions.assertThat(updateProducto.getId()).isEqualTo(5L);
    }

    @Test
    public void WhenUpdate_ThenReturnUpdatedStock() {
        Producto updateProducto = service.updateProduct(Producto.builder().id(3L).precioMinimo(111d).build());
        Assertions.assertThat(updateProducto.getPrecioMinimo()).isEqualTo(111d);
    }
    @Test
    public void WhenUpdateNullProduct_ThenReturnNull() {
        Producto updateProducto = service.updateProduct(Producto.builder().id(4L).precioMinimo(111d).build());
        Assertions.assertThatNullPointerException();
    }

    @Test
    public void whenFindAll_thenReturnListProduct() {
        List<Producto> founds = service.listAllProduct();

        Assertions.assertThat(founds.size()).isEqualTo(2);
    }

    @Test
    public void whenDeleteProduct_thenReturnNoError() {
        service.deleteById(1L);
        Assertions.assertThatNoException();
    }
    @Test
    public void whenFindPrices_thenReturnPreciosValidos() {
        PreciosValidos pv = service.findPrices("Sedan");
        Assertions.assertThat(pv.getPrecioMinimo()).isEqualTo(100d);
        Assertions.assertThat(pv.getPrecioMaximo()).isEqualTo(10000d);
    }
}
