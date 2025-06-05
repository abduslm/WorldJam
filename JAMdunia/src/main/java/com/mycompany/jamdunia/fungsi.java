/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jamdunia;

 import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Lenovo
 */
abstract class fungsi {
    
    private String idZona;
    protected ZonedDateTime zonaWaktu;
    protected DateTimeFormatter formatJam = DateTimeFormatter.ofPattern("HH:mm:ss z");
    protected static boolean running = true;
    abstract void menu();
    abstract void jalankan();
    abstract void tampilkanZoneId();
    abstract void stop();
    
    public String getIdZone(){
        return this.idZona;
    }
    public void setIdZone(String idZona){
        this.idZona=idZona;
    }

}
   
