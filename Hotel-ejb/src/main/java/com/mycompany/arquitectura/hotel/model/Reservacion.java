/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "RESERVACION")
public class Reservacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_r")
    private Integer id;

    @Column(name = "fecha_entrada_r")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha_entrada;

    @Column(name = "fecha_salida_r")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha_salida;

    @Column(name = "timestamp_r")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "descuento_r")
    private BigDecimal descuento;

    @OneToMany(mappedBy = "reservacion", targetEntity = HabitacionReserva.class,
            fetch = FetchType.EAGER)
    List<HabitacionReserva> habitacion_reservas;

    @ManyToOne
    @JoinColumn(name = "CODIGO_C", nullable = false, insertable = false, updatable = false)
    private Cliente cliente;

    public Reservacion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<HabitacionReserva> getHabitacion_reservas() {
        return habitacion_reservas;
    }

    public void setHabitacion_reservas(List<HabitacionReserva> habitacion_reservas) {
        this.habitacion_reservas = habitacion_reservas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Reservacion other = (Reservacion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservacion{" + "id=" + id + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida + ", timestamp=" + timestamp + ", descuento=" + descuento + ", habitacion_reservas=" + habitacion_reservas + ", cliente=" + cliente + '}';
    }

}
