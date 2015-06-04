/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author USUARIO
 */
public class Cita {
    private int value;
    private String dia;
    private String hora;
    private String doctor;
    private String lugar;
    
    public Cita(String inHora, String inDoctor, String inLugar){
        this.hora = inHora;
        this.doctor = inDoctor;
        this.lugar = inLugar;
    }
    
    public Cita(String inDia, String inHora, String inDoctor, String inLugar){
        this.dia = inDia;
        this.hora = inHora;
        this.doctor = inDoctor;
        this.lugar = inLugar;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    
}
