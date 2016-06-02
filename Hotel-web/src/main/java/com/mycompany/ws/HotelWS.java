/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ws;

import com.mycompany.arquitectura.hotel.servicios.ServicioHotel;
import com.mycompany.arquitectura.hotel.servicios.ServicioReserva;
import com.mycompany.arquitectura.hotel.util.RespDisponibilidad;
import com.mycompany.arquitectura.hotel.util.RespReserva;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Dennys
 */
@WebService(serviceName = "HotelWS")
public class HotelWS {

    @EJB
    private ServicioHotel shotel;
    @EJB
    private ServicioReserva sreserva;

    @WebMethod(operationName = "consultaDisponibilidadDeHabitaciones")
    public List<RespDisponibilidad> consultaDisponibilidadDeHabitaciones(@WebParam(name = "fechaEntrada") String fechaEntrada, @WebParam(name = "fechaSalida") String fechaSalida,
            @WebParam(name = "totalPersonas") Integer totalPersonas, @WebParam(name = "incluyeDesayuno") Boolean incluyeDesayuno) {
        System.out.println("fEntrada: " + fechaEntrada);
        return shotel.consulta1(fechaEntrada, fechaSalida, totalPersonas, incluyeDesayuno);
    }

    /**
     * Web service operation
     *
     * @param fechaEntrada
     */
    @WebMethod(operationName = "reservaHabitacionHotel")
    public RespReserva reservaHabitacionHotel(@WebParam(name = "fechaEntrada") String fechaEntrada,
            @WebParam(name = "fechaSalida") String fechaSalida,
            @WebParam(name = "total_personas") Integer total_personas,
            @WebParam(name = "desayuno") Boolean desayuno,
            @WebParam(name = "precio") BigDecimal precio,
            @WebParam(name = "codigoHabitacion") Integer codigoHabitacion,
            @WebParam(name = "nombreCliente") String nombreCliente,
            @WebParam(name = "cedulaCliente") String cedulaCliente) {
        return sreserva.reservaHabitacionHotel(fechaEntrada, fechaSalida, total_personas, desayuno, precio, codigoHabitacion, nombreCliente, cedulaCliente);
    }
}
