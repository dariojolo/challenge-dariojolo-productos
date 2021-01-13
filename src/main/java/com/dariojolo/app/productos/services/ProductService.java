package com.dariojolo.app.productos.services;

import com.dariojolo.app.productos.entities.PreciosValidos;
import com.dariojolo.app.productos.entities.Producto;

import java.util.List;

public interface ProductService {
    public List<Producto> listAllProduct();

    public Producto findProductById(Long id);

    public Producto createProduct(Producto producto);

    public Producto updateProduct(Producto producto);

    public void deleteById(Long id);

    public PreciosValidos findPrices(String modelo);
}
