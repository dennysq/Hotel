/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ws;

import com.mycompany.arquitectura.hotel.servicios.ServicioHotel;
import com.mycompany.arquitectura.hotel.util.RespDisponibilidad;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author LENOVO
 */
@WebService(serviceName = "ServicioWebHotel")
@Stateless()
public class ServicioWebHotel {
@EJB
    private ServicioHotel shotel;

    
    @WebMethod(operationName = "consultaDisponibilidadDeHabitaciones")
    public List<RespDisponibilidad> consultaDisponibilidadDeHabitaciones(@WebParam(name = "fechaEntrada") String fechaEntrada, @WebParam(name = "fechaSalida") String fechaSalida,
                                                                         @WebParam(name = "totalPersonas") Integer totalPersonas, @WebParam(name = "incluyeDesayuno") Boolean incluyeDesayuno) {
        System.out.println("fEntrada: "+fechaEntrada);
        return shotel.consulta1(fechaEntrada, fechaSalida, totalPersonas, incluyeDesayuno);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "reservaHabitacionHotel")
    public String reservaHabitacionHotel(@WebParam(name = "fechaEntrada") String fechaEntrada, @WebParam(name = "fechaSalida") String fechaSalida, @WebParam(name = "total_personas") Integer total_personas, @WebParam(name = "desayuno") Boolean desayuno, @WebParam(name = "precio") BigDecimal precio, @WebParam(name = "codigosHabitantes") List<String> codigosHabitantes) {
        //TODO write your implementation code here:
        return null;
    }
}
