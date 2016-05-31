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
@Table(name = "CLIENTE")
public class Cliente implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_c")
    Integer id;
    
    @Column(name = "idenitificacion_c")
    private String identificacion;
    
    @Column(name = "nombres_c")
    private String nombres;
    
    @Column(name = "num_tarjeta_c")
    private String num_tarjeta;
    
    @OneToMany
        (mappedBy = "CLIENTE", targetEntity = Reservacion.class,
            fetch = FetchType.EAGER)
    List<Reservacion> reservacion;

    public Cliente() {
    }

    public Cliente(Integer id, String identificacion, String nombres, String num_tarjeta, List<Reservacion> reservacion) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.num_tarjeta = num_tarjeta;
        this.reservacion = reservacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public List<Reservacion> getReservacion() {
        return reservacion;
    }

    public void setReservacion(List<Reservacion> reservacion) {
        this.reservacion = reservacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", identificacion=" + identificacion + ", nombres=" + nombres + ", num_tarjeta=" + num_tarjeta + ", reservacion=" + reservacion + '}';
    }
    
    
}
