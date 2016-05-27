/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.dao;

import com.mycompany.arquitectura.hotel.model.Habitacion;
import com.persist.common.dao.DefaultGenericDAOImple;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author LENOVO
 */
@LocalBean
@Stateless
public class HabitacionDAO extends DefaultGenericDAOImple<Habitacion, Integer> {

    public HabitacionDAO() {
        super(Habitacion.class);
    }
}
