/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author LENOVO
 */
@Embeddable
public class HabitacionReservaPK implements Serializable {

    @Column(name = "codigo_hab")
    private Integer codigo_hab;

    @Column(name = "codigo_r")
    private Integer codigo_r;

    public Integer getCodigo_hab() {
        return codigo_hab;
    }

    public void setCodigo_hab(Integer codigo_hab) {
        this.codigo_hab = codigo_hab;
    }

    public Integer getCodigo_r() {
        return codigo_r;
    }

    public void setCodigo_r(Integer codigo_r) {
        this.codigo_r = codigo_r;
    }

    @Override
    public String toString() {
        return "HabitacionReservaPK{" + "codigo_hab=" + codigo_hab + ", codigo_r=" + codigo_r + '}';
    }

}
