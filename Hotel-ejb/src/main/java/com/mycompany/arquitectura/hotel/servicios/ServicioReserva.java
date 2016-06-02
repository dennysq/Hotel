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
//import com.persist.common.dao.Order;
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
        System.out.println("codCLiente: " + codCliente);
        Cliente temp = this.clienteDAO.findById(codCliente, false);
        System.out.println("" + temp);
        Reservacion re = new Reservacion();
        re.setCliente(temp);
        re.setCodCliente(codCliente);
        re.setFecha_entrada(f_entrada);
        re.setFecha_salida(f_salida);
        this.reservacionDAO.insert(re);
        return re.getId();
    }

    public Cliente verificacionCliente(String nombCliente, String cedulaCliente) {
        List<Cliente> listCliente;
        Cliente c = new Cliente();
        c.setIdentificacion(cedulaCliente);
        listCliente = this.clienteDAO.find(c);
        //try {
        if (listCliente == null || listCliente.isEmpty()) {
            c.setNombres(nombCliente);
            this.clienteDAO.insert(c);
            this.mensaje = "Condición cliente: Nuevo.";
            System.out.println("" + c);
            return c;
        } else {
            this.mensaje = "Condición cliente: Antiguo";
            return listCliente.get(0);
        }
//        } catch (Exception e) {
//            return null;
//        }
    }

    public RespReserva reservaHabitacionHotel(String fechaEntrada, String fechaSalida,
                                            Integer total_personas, Boolean desayuno,
                                            BigDecimal precio, Integer codigoHabitacion,
                                            String nombCliente, String cedulaCliente) {
        RespReserva respuesta = null;
        Integer codCliente;
        Integer codReservacion = null;
        HabitacionReserva habReserva;
        Boolean estado = false;

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date f_entrada =sdf.parse(fechaEntrada);
            Date f_salida = sdf.parse(fechaSalida);
            List<Cliente> listCliente;
            Cliente ctemp = new Cliente();
            ctemp.setIdentificacion(cedulaCliente);
            listCliente = this.clienteDAO.find(ctemp);
            //try {

            if (listCliente == null || listCliente.isEmpty()) {
                ctemp.setNombres(nombCliente);
                this.clienteDAO.insert(ctemp);
                this.clienteDAO.flush();

            } else {
                System.out.println("" + listCliente);
                ctemp = listCliente.get(0);
            }
            System.out.println("" + ctemp);

            //codReservacion = this.guardarReservacion(ctemp.getId(), f_entrada, f_salida);
            Reservacion re = new Reservacion();
            re.setCliente(ctemp);
            re.setCodCliente(ctemp.getId());
            java.sql.Date sqlDateEntrada = new java.sql.Date(f_entrada.getTime());
            re.setFecha_entrada(sqlDateEntrada);
            java.sql.Date sqlDateSalida = new java.sql.Date(f_salida.getTime());
            re.setFecha_salida(sqlDateSalida);
            this.reservacionDAO.insert(re);
            this.reservacionDAO.flush();
            HabitacionReservaPK pk = new HabitacionReservaPK();
            pk.setCodigo_hab(codigoHabitacion);
            pk.setCodigo_r(re.getId());
            habReserva = new HabitacionReserva();
            habReserva.setHabitacionReservaPK(pk);
            habReserva.setNumero_personas(total_personas);
            habReserva.setPrecio_total(precio);
            habReserva.setServicio_desayuno(desayuno);
            this.habitacionReservaDAO.insert(habReserva);
//            this.habitacionReservaDAO.flush();
            estado = true;
            respuesta = new RespReserva(estado,
                    re.getId(),
                    "¡Reserva realizada exitosamente!");
        } catch (Exception e) {
            //estado = false;
            mensaje= " " + e;
            respuesta = new RespReserva(estado,
                    -1,
                    mensaje);
            System.out.println("Error!" + e.toString());
        }

        return respuesta;
    }

}
