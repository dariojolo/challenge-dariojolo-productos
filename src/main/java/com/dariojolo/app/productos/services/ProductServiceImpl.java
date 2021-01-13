package com.dariojolo.app.productos.services;

import com.dariojolo.app.productos.DTOs.ProductRepository;
import com.dariojolo.app.productos.entities.PreciosValidos;
import com.dariojolo.app.productos.entities.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository dao;

    @Override
    public List<Producto> listAllProduct() {
        return dao.findAll();
    }

    @Override
    public Producto findProductById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Producto createProduct(Producto producto) {
        return dao.save(producto);
    }

    @Override
    public Producto updateProduct(Producto producto) {
        Producto productoDB = findProductById(producto.getId());
        if (productoDB == null) {
            return null;
        }
        productoDB.setPrecioMinimo(producto.getPrecioMinimo());
        productoDB.setPrecioMaximo(producto.getPrecioMaximo());
        return dao.save(productoDB);

    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public PreciosValidos findPrices(String modelo){
        double minimo = dao.findPrecioMinimoByModelo(modelo);
        double maximo = dao.findPrecioMaximoByModelo(modelo);
        return new PreciosValidos(minimo,maximo);
    }

}
