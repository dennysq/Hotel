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
import java.text.SimpleDateFormat;
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
public class ServicioHotel { 
    
    @EJB
    private HabitacionDAO habitacionDAO;
    @EJB
    private HabitacionReservaDAO habitacionReservaDAO;
    @EJB
    private TarifaDAO tarifaDAO;
    
    private SimpleDateFormat sdf;
    
    
    public Habitacion obtenerPorId(Integer id) {
        return this.habitacionDAO.findById(id, false);
    }
    
     //CÁLCULO DE PRECIOS SEGÚN DESAYUNO Y HABITACION
    public BigDecimal calculoPrecios(String tipo, Date f_entrada, Date f_salida,Integer tot_persona, Boolean desayuno){
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
        double dias = (( f_salida.getTime() - f_entrada.getTime() )/ MILLSECS_PER_DAY);
        
        List<Tarifa> listTarifa = this.tarifaDAO.findAll();       
        Double total=0.0;
        
        for(int i=0; i<listTarifa.size();i++){
            total = 0.00;
            if((f_entrada.after(listTarifa.get(i).getFecha_inicio()) || f_entrada.equals(listTarifa.get(i).getFecha_inicio()) ) && f_salida.before(listTarifa.get(i).getFecha_fin() ) )
                if(listTarifa.get(i).getTipo_habitacion().equals(tipo)){
                    total = dias * listTarifa.get(i).getCosto().doubleValue();
                    if(desayuno){
                        total += (listTarifa.get(i).getCosto_desayuno().doubleValue() * dias * tot_persona);
                        //break;
                    }
                    //break;
                }
        }
        
//        total *= tot_persona;
        
        return (new BigDecimal(total));
    }
    
    
    public List<Habitacion> obtenerHabitacionesPorNumPersonas(List<Habitacion> habitaciones, Integer cant_personas){
        for(int i=habitaciones.size()-1;i>=0;i--){
            if(habitaciones.get(i).getNum_personas() > cant_personas){
                habitaciones.remove(i);
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
    
//      public static java.util.Date asDate(XMLGregorianCalendar xgc) {
//        if (xgc == null) {
//            return null;
//        } else {
//            return xgc.toGregorianCalendar().getTime();
//        }
//    }
    
 

    public List<RespDisponibilidad> consulta1(String fechaEntrada, String fechaSalida,
                                              Integer totalPersonas, Boolean incluyeDesayuno) {
        List<RespDisponibilidad> listResp = new ArrayList();
        List<Habitacion> habitaciones = new ArrayList();
    
        
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
        Date f_entrada=sdf.parse(fechaEntrada);
        Date f_salida=sdf.parse(fechaSalida);        
        habitaciones = this.obtenerHabitacionesSinReservaPorFecha(f_entrada, f_salida); //Habitaciones limitadas a la fecha disponible
        habitaciones = this.obtenerHabitacionesPorNumPersonas(habitaciones, totalPersonas);//Habitaciones con fecha disponible por el número de personas
        for(int i=0;i<habitaciones.size();i++){
            listResp.add(new RespDisponibilidad(habitaciones.get(i).getId(),this.calculoPrecios(habitaciones.get(i).getTipo(),f_entrada ,f_salida,totalPersonas,incluyeDesayuno),habitaciones.get(i).getTipo()));
            
        }
        
         }
        catch(Exception e)
        { System.out.println("Error!! Función conulta1");
                                             }
        
        return listResp;
    }
    
    
}
