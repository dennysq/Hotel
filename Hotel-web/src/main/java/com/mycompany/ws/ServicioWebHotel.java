/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ws;

import com.mycompany.arquitectura.hotel.servicios.ServicioHotel;
import com.mycompany.arquitectura.hotel.util.RespDisponibilidad;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;

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
}
