/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author LENOVO
 */

@Entity
@Table(name = "TARIFA")
public class Tarifa implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="codigo_t")
    private Integer id;
    
    @Column(name = "fecha_inicio")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha_inicio;
    
    @Column(name = "fecha_fin")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha_fin;
    
    @Column(name = "costo")
    private BigDecimal costo;
    
    @Column(name = "costo_desayuno")
    private BigDecimal costo_desayuno;
    
    @Column(name = "tipo")
    private String tipo_habitacion;

    public Tarifa() {
    }
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public BigDecimal getCosto_desayuno() {
        return costo_desayuno;
    }

    public void setCosto_desayuno(BigDecimal costo_desayuno) {
        this.costo_desayuno = costo_desayuno;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Tarifa other = (Tarifa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tarifa{" + "id=" + id + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", costo=" + costo + ", costo_desayuno=" + costo_desayuno + ", tipo_habitacion=" + tipo_habitacion + '}';
    }
    
    



}
