/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ws;

import com.mycompany.arquitectura.hotel.servicios.HabitacionServicio;
import com.mycompany.arquitectura.hotel.util.RespDisponibilidad;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author LENOVO
 */
@WebService(serviceName = "SistemaHotelWS")
public class SistemaHotelWS {

    @EJB
    private HabitacionServicio habitacionServicio;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "consultaDisponibilidadDeHabitaciones")
    public List<RespDisponibilidad> consultaDisponibilidadDeHabitaciones(@WebParam(name = "f_entrada") Date f_entrada,
                                                                         @WebParam(name = "f_salida") Date f_salida,
                                                                         @WebParam(name = "total_personas") Integer total_personas,
                                                                         @WebParam(name = "desayuno") Boolean desayuno) {
        
        
        return habitacionServicio.ConsultaDisponibilidadDeHabitaciones(f_entrada, f_salida, total_personas, desayuno);
    }

    /**
     * Web service operation
     */
   
    
    
}
