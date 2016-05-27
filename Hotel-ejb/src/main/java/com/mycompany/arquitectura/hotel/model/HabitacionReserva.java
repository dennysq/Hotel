/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.model;

import java.io.Serializable;
import java.util.Objects;
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
    
     @ManyToOne
    @JoinColumn(name = "codigo_s", nullable = false,insertable = false,updatable = false)
    private Servicio servicio;
    
     @ManyToOne
    @JoinColumn(name = "codigo_hab", nullable = false,insertable = false,updatable = false)
    private Habitacion habitacion;
     
      @ManyToOne
    @JoinColumn(name = "codigo_r", nullable = false,insertable = false,updatable = false)
    private Reservacion reservacion;

    public HabitacionReserva() {
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
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

    public HabitacionReservaPK getHabitacionReservaPK() {
        return habitacionReservaPK;
    }

    public void setHabitacionReservaPK(HabitacionReservaPK habitacionReservaPK) {
        this.habitacionReservaPK = habitacionReservaPK;
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
        return "HabitacionReserva{" + "habitacionReservaPK=" + habitacionReservaPK + ", servicio=" + servicio + ", habitacion=" + habitacion + ", reservacion=" + reservacion + '}';
    }


}
