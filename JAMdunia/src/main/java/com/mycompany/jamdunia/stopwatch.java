/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jamdunia;


import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class stopwatch extends jam{
    
    @Override
    public void menu(){
        Scanner s = new Scanner(System.in);
        boolean aaaa=true;
        try{
            while(aaaa){
                String aaa="""
                        %s=============================%s
                                %sSelamat Datang
                                        di           
                                Mode Stopwatch%s
                            %s======================
                        =============================%s
                        %sSilahkan Pilih Menu :
                        1. Mulai Stopwatch
                        2. Keluar%s
                        %s=============================%s
                        Pilih menu no : """.formatted(BIRU,RESET,
                                                                HIJAU,RESET,
                                                                BIRU,RESET,
                                                                HIJAU,RESET,
                                                                BIRU,RESET);

                System.out.print(aaa);
                String pilih = s.nextLine();
                switch(pilih){
                    case "1":
                        clearScreen();
                        this.jalankan();
                        break;
                    case "2":
                        clearScreen();
                         aaaa=false;
                         jam a=new jam();
                        a.menu();
                        break;
                    default:
                        break;//stopwatchh();
                }
              }
           
            }catch(Exception e){
                System.out.println("Menu StopWatch tidak dapat ditampilkan");
            }
    }
    
    boolean runn;
    String abc;
    @Override
    public void jalankan() {
        try {
            ScheduledExecutorService aa = Executors.newScheduledThreadPool(1);
            runn=true;
            abc=null;
            long startTime = System.currentTimeMillis();
            Runnable runSw = () -> {
                while(runn){
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    abc = "\rStopwatch: %02d:%02d.%03d".formatted(elapsedTime / 60000, 
                            (elapsedTime / 1000) % 60, elapsedTime % 1000);
                    System.out.println(abc);  
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }} };
            aa.execute(runSw);
            this.stop();
            aa.shutdown();
            clearScreen();
            System.out.println(abc); 
            System.out.println("%sStopwatch Berhenti%s ".formatted(MERAH, RESET));
        }catch (Exception e) {
            System.out.println("%sStopwatch (ada kesalahan teknis)%s".formatted(MERAH,RESET));
        }
    }
    @Override
    public void stop(){
    Scanner sc = new Scanner(System.in);
        System.out.println("%sTekan 'Enter' untuk berhenti...%s".formatted(MERAH,RESET));
        sc.nextLine();
        runn = false;
    }
    
}

