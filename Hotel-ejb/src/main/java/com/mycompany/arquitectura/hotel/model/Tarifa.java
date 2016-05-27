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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private Date fecha_inicio;
    
    @Column(name = "fecha_fin")
    private Date fecha_fin;
    
    @Column(name = "costo")
    private BigDecimal costo;
    
        
    @ManyToOne
    @JoinColumn(name = "codigo_hab", nullable = false,insertable = false,updatable = false)
    private Habitacion habitacion;

    public Tarifa(Integer id, Date fecha_inicio, Date fecha_fin, BigDecimal costo, Habitacion habitacion) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.costo = costo;
        this.habitacion = habitacion;
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

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        return "Tarifa{" + "id=" + id + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", costo=" + costo + ", habitacion=" + habitacion + '}';
    }
    
    
}
