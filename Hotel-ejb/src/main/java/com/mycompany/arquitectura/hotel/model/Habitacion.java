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
@Table(name = "HABITACION")
public class Habitacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="codigo_hab")
    private Integer id;
    
    @Column(name = "numero_hab")
    private String numero;
    
    @Column(name = "tipo_hab")
    private String tipo;
    
    @Column(name = "CANT_PERSONAS")
    private Integer num_personas;
    
    @OneToMany
    (mappedBy = "habitacion", targetEntity = HabitacionReserva.class,
            fetch = FetchType.EAGER)
    List<HabitacionReserva> habitacion_reservas;

    public Habitacion() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNum_personas() {
        return num_personas;
    }

    public void setNum_personas(Integer num_personas) {
        this.num_personas = num_personas;
    }

    public List<HabitacionReserva> getHabitacion_reservas() {
        return habitacion_reservas;
    }

    public void setHabitacion_reservas(List<HabitacionReserva> habitacion_reserva) {
        this.habitacion_reservas = habitacion_reserva;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Habitacion other = (Habitacion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "id=" + id + ", numero=" + numero + ", tipo=" + tipo + ", num_personas=" + num_personas + ", habitacion_reservas=" + habitacion_reservas + '}';
    }

   

    
    
}
