/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */

@Entity
@Table(name = "habitacion_reservacion")
public class HabitacionReserva implements Serializable{
    
    @EmbeddedId
    HabitacionReservaPK habitacionReservaPK;
    
    @Column(name = "codigo_r")
    private Integer codReservacion;
    
    @Column(name = "codigo_hab")
    private Integer codHabitacion;
    
    @Column(name = "num_personas")
    private Integer numero_personas;
    
    @Column(name = "servicio_desayuno")
    private Boolean servicio_desayuno;
        
    @Column(name = "precio_total")
    private BigDecimal precio_total;
    
    
    @ManyToOne
    @JoinColumn(name = "codigo_hab", nullable = false,insertable = false,updatable = false)
    private Habitacion habitacion;
     
    @ManyToOne
    @JoinColumn(name = "codigo_r", nullable = false,insertable = false,updatable = false)
    private Reservacion reservacion;

    public HabitacionReserva() {
    }

    public HabitacionReservaPK getHabitacionReservaPK() {
        return habitacionReservaPK;
    }

    public void setHabitacionReservaPK(HabitacionReservaPK habitacionReservaPK) {
        this.habitacionReservaPK = habitacionReservaPK;
    }

    public Integer getNumero_personas() {
        return numero_personas;
    }

    public void setNumero_personas(Integer numero_personas) {
        this.numero_personas = numero_personas;
    }

    public Boolean getServicio_desayuno() {
        return servicio_desayuno;
    }

    public void setServicio_desayuno(Boolean servicio_desayuno) {
        this.servicio_desayuno = servicio_desayuno;
    }

    public BigDecimal getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(BigDecimal precio_total) {
        this.precio_total = precio_total;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }



    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.habitacionReservaPK);
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
        final HabitacionReserva other = (HabitacionReserva) obj;
        if (!Objects.equals(this.habitacionReservaPK, other.habitacionReservaPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HabitacionReserva{" + "habitacionReservaPK=" + habitacionReservaPK + ", numero_personas=" + numero_personas + ", servicio_desayuno=" + servicio_desayuno + ", precio_total=" + precio_total + ", habitacion=" + habitacion + ", reservacion=" + reservacion + '}';
    }


}