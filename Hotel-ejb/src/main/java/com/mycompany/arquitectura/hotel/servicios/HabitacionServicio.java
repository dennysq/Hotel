/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arquitectura.hotel.servicios;

import com.mycompany.arquitectura.hotel.dao.HabitacionDAO;
import com.mycompany.arquitectura.hotel.dao.HabitacionReservaDAO;
import com.mycompany.arquitectura.hotel.dao.TarifaDAO;
import com.mycompany.arquitectura.hotel.model.Habitacion;
import com.mycompany.arquitectura.hotel.model.HabitacionReserva;
import com.mycompany.arquitectura.hotel.model.Tarifa;
import com.mycompany.arquitectura.hotel.util.RespDisponibilidad;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class HabitacionServicio {
    
    @EJB
    private HabitacionDAO habitacionDAO;
    @EJB
    private HabitacionReservaDAO habitacionReservaDAO;
    @EJB
    private TarifaDAO tarifaDAO;
    
    
    public Habitacion obtenerPorId(Integer id) {
        return this.habitacionDAO.findById(id, false);
    }
    
     //CÁLCULO DE PRECIOS SEGÚN DESAYUNO Y HABITACION
    public BigDecimal calculoPrecios(String tipo, Date f_entrada, Date f_salida,Integer tot_persona, Boolean desayuno){
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
        Long dias= ( f_entrada.getTime() - f_salida.getTime() )/ MILLSECS_PER_DAY;
        
        List<Tarifa> listTarifa = this.tarifaDAO.findAll();       
        Double total = null;
        
        //listTarifa.get(i).getFecha_inicio().before(f_entrada) && listTarifa.get(i).getFecha_fin().after(f_salida)
        for(int i=0; i<listTarifa.size();i++){
            if((f_entrada.after(listTarifa.get(i).getFecha_inicio()) || f_entrada.equals(listTarifa.get(i).getFecha_inicio()) ) && ( f_entrada.before(listTarifa.get(i).getFecha_fin()) || f_entrada.equals(listTarifa.get(i).getFecha_fin()) ) )
                if(listTarifa.get(i).getTipo_habitacion().equals(tipo)){
                    total = dias.doubleValue() * listTarifa.get(i).getCosto().byteValue();
                    if(desayuno){
                        total += (listTarifa.get(i).getCosto_desayuno().byteValue() * dias.doubleValue());
                        break;
                    }
                    break;
                }
        }
        
        total *= tot_persona;
        
        return (new BigDecimal(total));
    }
    
    
    public List<Habitacion> obtenerHabitacionesPorNumPersonas(List<Habitacion> habitaciones, Integer cant_personas){
        for(int i=0;i<=habitaciones.size();i++){
            if(habitaciones.get(i).getNum_personas() > cant_personas){
                habitaciones.remove(i);
                i--;
            }
        }
        return habitaciones;
    }

    
    public List<Habitacion> obtenerHabitacionesSinReservaPorFecha(Date fechaInicial, Date fechaFinal)
    {
        //List<Habitacion> habitacionesDep = new ArrayList<>();
        List<Habitacion> habitaciones = this.habitacionDAO.findAll();
        List<HabitacionReserva> reservasHabitaciones = this.habitacionReservaDAO.findAll();
        for(HabitacionReserva res:reservasHabitaciones)
        {
                if((res.getReservacion().getFecha_entrada().before(fechaInicial) ||
                        res.getReservacion().getFecha_entrada().equals(fechaInicial)) &&
                        (res.getReservacion().getFecha_salida().after(fechaFinal) ||
                        res.getReservacion().getFecha_salida().equals(fechaFinal)))
                {
                    for(int i=0; i<habitaciones.size();i++)
                    {
                        if(res.getHabitacion().getId().equals(habitaciones.get(i).getId()))
                        {
                            habitaciones.remove(i);
                            i--;
                        }
                    }
                }
        }
        return habitaciones;
    }
    
    
    public List<RespDisponibilidad> ConsultaDisponibilidadDeHabitaciones(Date f_entrada, Date f_salida,Integer tot_persona, Boolean desayuno){
        List<RespDisponibilidad> listResp = new ArrayList();
        List<Habitacion> habitaciones = new ArrayList();
        habitaciones = this.obtenerHabitacionesSinReservaPorFecha(f_entrada, f_salida); //Habitaciones limitadas a la fecha disponible
        habitaciones = this.obtenerHabitacionesPorNumPersonas(habitaciones, tot_persona);//Habitaciones con fecha disponible por el número de personas
        for(int i=0;i<habitaciones.size();i++){
            listResp.add(new RespDisponibilidad(habitaciones.get(i).getId(),this.calculoPrecios(habitaciones.get(i).getTipo(),f_entrada ,f_salida,tot_persona,desayuno),habitaciones.get(i).getTipo()));
            
        }
        
        //List<HabitacionReserva> reservasHabitaciones = this.habitacionReservaDAO.findAll();     
        
        return listResp;
    }
    
    
    
    
}
