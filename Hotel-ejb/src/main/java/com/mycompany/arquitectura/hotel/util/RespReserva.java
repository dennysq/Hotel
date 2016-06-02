/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.util;

/**
 *
 * @author LENOVO
 */
public class RespReserva {  
    
    private Boolean estado;
    private Integer codigoReserva;
    private String mensajeError;

    public RespReserva(Boolean estado, Integer codigoReserva, String mensajeError) {
        this.estado = estado;
        this.codigoReserva = codigoReserva;
        this.mensajeError = mensajeError;
    }
    
    
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(Integer codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
    
    

    
    
}
