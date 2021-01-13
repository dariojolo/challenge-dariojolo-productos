package com.dariojolo.app.productos.controller;

import com.dariojolo.app.productos.entities.PreciosValidos;
import com.dariojolo.app.productos.entities.Producto;
import com.dariojolo.app.productos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> getProduct(@PathVariable("id") Long id) {
        Producto producto = service.findProductById(id);
        if (producto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listProducts() {
        List<Producto> productos = new ArrayList<>();
        productos = service.listAllProduct();
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> createProduct(@RequestBody Producto producto) {
        Producto newProducto = service.createProduct(producto);
        if (producto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Producto> updateProduct(@PathVariable(name = "id") Long id, @RequestBody Producto producto) {
        producto.setId(id);
        Producto dbProducto = service.updateProduct(producto);
        if (dbProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dbProducto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> deleteProduct(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/precio/{modelo}", method = RequestMethod.GET)
    public ResponseEntity<PreciosValidos> getPrices(@PathVariable("modelo") String modelo) {
        PreciosValidos precios = service.findPrices(modelo);
        if (precios == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(precios);
    }
}

