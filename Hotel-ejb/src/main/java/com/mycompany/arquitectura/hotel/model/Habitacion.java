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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    
    @Column(name = "camas_hab")
    private Integer camas;
    
    @Column(name = "tipo_hab")
    private String tipo;
    
    @ManyToOne
    @JoinColumn(name = "CODIGO_H", nullable = false,insertable = false,updatable = false)
    private Hotel hotel;
    
    
    @OneToMany
    (mappedBy = "habitacion", targetEntity = Tarifa.class,
            fetch = FetchType.EAGER)
    List<Tarifa> tarifas;
    
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

    public Integer getCamas() {
        return camas;
    }

    public void setCamas(Integer camas) {
        this.camas = camas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public List<HabitacionReserva> getHabitacion_reservas() {
        return habitacion_reservas;
    }

    public void setHabitacion_reservas(List<HabitacionReserva> habitacion_reserva) {
        this.habitacion_reservas = habitacion_reserva;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        return "Habitacion{" + "id=" + id + ", numero=" + numero + ", camas=" + camas + ", tipo=" + tipo + ", hotel=" + hotel + ", tarifas=" + tarifas + ", hab_reserva=" + habitacion_reservas + '}';
    }
    
    
    
    
    
}
