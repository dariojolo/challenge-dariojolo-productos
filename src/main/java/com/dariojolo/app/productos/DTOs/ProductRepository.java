package com.dariojolo.app.productos.DTOs;

import com.dariojolo.app.productos.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Producto,Long> {
    public Optional<Producto> findById(Long id);

    @Query("select distinct p.precioMinimo from Producto p where p.modelo = ?1")
    public double findPrecioMinimoByModelo(String modelo);

    @Query("select distinct p.precioMaximo from Producto p where p.modelo = ?1")
    public double findPrecioMaximoByModelo(String modelo);


}
