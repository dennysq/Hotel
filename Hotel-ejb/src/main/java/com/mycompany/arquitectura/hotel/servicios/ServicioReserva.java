/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.servicios;

import com.mycompany.arquitectura.hotel.dao.ClienteDAO;
import com.mycompany.arquitectura.hotel.dao.HabitacionDAO;
import com.mycompany.arquitectura.hotel.dao.HabitacionReservaDAO;
import com.mycompany.arquitectura.hotel.dao.ReservacionDAO;
import com.mycompany.arquitectura.hotel.model.Cliente;
import com.mycompany.arquitectura.hotel.model.HabitacionReserva;
import com.mycompany.arquitectura.hotel.model.HabitacionReservaPK;
import com.mycompany.arquitectura.hotel.model.Reservacion;
import com.mycompany.arquitectura.hotel.util.RespReserva;
import com.persist.common.dao.Order;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author LENOVO
 */
@LocalBean
@Stateless
public class ServicioReserva {

    @EJB
    private HabitacionDAO habitacionDAO;
    @EJB
    private HabitacionReservaDAO habitacionReservaDAO;
    @EJB
    private ReservacionDAO reservacionDAO;
    @EJB
    private ClienteDAO clienteDAO;

    private String mensaje;

    private SimpleDateFormat sdf;

    public Integer guardarReservacion(Integer codCliente, Date f_entrada, Date f_salida) {
        Reservacion re = new Reservacion(codCliente, f_entrada, f_salida);

        this.reservacionDAO.insert(re);
        List<Reservacion> listReservacion;
        listReservacion = this.reservacionDAO.findO(re, f_entrada.toString());//NO SÉ SI FUNCIONA

        return listReservacion.get(0).getId();
    }

    public Cliente verificacionCliente(String nombCliente, String cedulaCliente) {
        List<Cliente> listCliente;
        Cliente c = new Cliente();
        c.setIdentificacion(cedulaCliente);
        listCliente = this.clienteDAO.find(c);

        if (listCliente == null || listCliente.isEmpty()) {
            c.setNombres(nombCliente);
            this.clienteDAO.insert(c);
            this.mensaje = "Condición cliente: Nuevo.";
            return c;
        } else {
            this.mensaje = "Condición cliente: Antiguo";
            return listCliente.get(0);
        }
    }

    public RespReserva reservaHabitacionHotel(String fechaEntrada, String fechaSalida,
            Integer total_personas, Boolean desayuno,
            BigDecimal precio, Integer codigoHabitacion,
            String nombCliente, String cedulaCliente) {
        RespReserva respuesta = new RespReserva();

        Integer codReservacion = null;
        HabitacionReserva habReserva;
        Boolean estado = false;

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date f_entrada = sdf.parse(fechaEntrada);
            Date f_salida = sdf.parse(fechaSalida);

            Cliente c = this.verificacionCliente(nombCliente, cedulaCliente);
            codReservacion = this.guardarReservacion(c.getId(), f_entrada, f_salida);
            habReserva = new HabitacionReserva();
            habReserva.setHabitacionReservaPK(new HabitacionReservaPK(codigoHabitacion, codReservacion));
            habReserva.setNumero_personas(total_personas);
            habReserva.setPrecio_total(precio);
            habReserva.setServicio_desayuno(desayuno);
            this.habitacionReservaDAO.insert(habReserva);
            respuesta.setEstado(true);
            respuesta.setCodigoReserva(codReservacion);
        } catch (Exception e) {

            respuesta.setEstado(false);
            respuesta.setMensajeError("Error: " + e.toString());
            System.out.println("Error!");
        }

        return respuesta;
    }

}
