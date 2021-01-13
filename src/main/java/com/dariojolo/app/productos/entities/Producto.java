package com.dariojolo.app.productos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_products")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    @Column(name = "precio_minimo")
    private Double precioMinimo;
    @Column(name = "precio_maximo")
    private Double precioMaximo;
}
