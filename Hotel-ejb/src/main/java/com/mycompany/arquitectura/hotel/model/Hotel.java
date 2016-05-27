/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */

@Entity
@Table(name = "HOTEL")
public class Hotel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_h")
    Integer id;
    
    @Column(name = "nombre_h")
    String nombre;
    
    @Column(name = "direccion_h")
    String direccion;
    
    @Column(name = "telefono_h")
    String telefono;
    
    @Column(name = "ciudad_h")
    String ciudad;
    
    @OneToMany
        (mappedBy = "hotel", targetEntity = Habitacion.class,
            fetch = FetchType.EAGER)
    List<Habitacion> habitaciones;

    public Hotel(Integer id, String nombre, String direccion, String telefono, String ciudad, List<Habitacion> habitaciones) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.habitaciones = habitaciones;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }    

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Hotel other = (Hotel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", ciudad=" + ciudad + ", habitaciones=" + habitaciones + '}';
    }
    
    
    
}
