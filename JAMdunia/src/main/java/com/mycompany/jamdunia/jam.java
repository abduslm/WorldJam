/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jamdunia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 *
 * @author Lenovo
 */
public class jam extends fungsi {
    protected static final String RESET = "\u001B[0m";
    protected static final String MERAH = "\u001B[31m";
    protected static final String HIJAU = "\u001B[32m";
    protected static final String BIRU = "\u001B[34m";
    protected static final String CYAN = "\u001B[36m";
    protected static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void menu(){
     String a,b;       
     Scanner s = new Scanner(System.in);
        try{
            clearScreen();
            Date datee = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
            String StringDate=sdf.format(datee).toString();
            while(true){
                String aaa="""
                        %s=============================%s
                                 %sSelamat Datang%s
                                        di           
                                %sAplikasi WorldJam%s
                              %sDev By Fawwas Projek%s
                               %s%s%s
                        %s=============================%s
                        %sSilahkan Pilih Menu :
                        1. Lihat Jam
                        2. Lihat ID Zona Waktu
                        3. Keluar%s
                        %s=============================%s
                        Pilih menu no : """.formatted(BIRU,RESET,
                                                                HIJAU, RESET,
                                                                BIRU, RESET,
                                                                CYAN, RESET,
                                                                HIJAU, StringDate, RESET,
                                                                BIRU,RESET,
                                                                HIJAU, RESET,
                                                                BIRU,RESET);
                System.out.print(aaa);
                String pilih = s.nextLine();
                
                

                if(pilih.equals("1")){
                    clearScreen();
                    this.jalankan();
                }else if(pilih.equals("2")){
                    clearScreen();
                    this.tampilkanZoneId();
                }else if(pilih.equals("StopWatch")){
                    System.out.print("Masukkan username: ");
                    String nama = s.nextLine();
                    System.out.print("Masukkan password: ");
                    String kode=s.nextLine();
                    daftarSw(nama,kode);
                    clearScreen();
                    System.out.println("ketik \" %s".formatted(CYAN)+nama+"@"+kode+"%s \"untuk masuk ke menu StopWatch".formatted(RESET));
                    
                }else if(pilih.contains("@")){
                    String z=pilih.substring(0, pilih.indexOf("@"));
                    String zz=pilih.substring(pilih.indexOf("@")+1, pilih.length());
                    if(masukSw(z,zz)){
                        clearScreen();
                        System.out.println("Masuk ke menu StopWatch");
                        jam sw = new stopwatch();
                        sw.menu();
                    }else{
                        System.out.println("Maaf username atau password anda tidak ditemukan");
                    }
                    
                }else if(pilih.equals("3")){
                    clearScreen();
                    System.out.println("""
        %s=============================%s
              %sSampai Jumpa Lagi%s
        %s=============================%s""".formatted(BIRU,RESET,
                                                                                                HIJAU, RESET,
                                                                                               BIRU, RESET));
                    System.exit(0);
                    
                }else{
                    clearScreen();
                    System.out.println("""
        %s=============================%s
              %sHanya ada menu 1, 2, & 3%s
        %s=============================%s""".formatted(BIRU,RESET,
                                                                                                 MERAH, RESET,
                                                                                                 BIRU, RESET));
                }
                try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } 
        }
        }
        finally {
                s.close();
                System.exit(0);
    }
    } 
    
    
    
    @Override
    public void jalankan() {
        super.zonaWaktu=null;
        Scanner scan = new Scanner(System.in);
        System.out.println("""                        
%s======================%s
       %sJAM%s
%s======================%s""".formatted(BIRU,RESET,
                                                                            HIJAU,RESET,
                                                                            BIRU,RESET));
        System.out.print("Masukkan zona waktunya ('00' untuk kembali) (contoh : Asia/Jakarta) : ");
        String idZo = scan.nextLine();
        if(idZo.equals("00")){
            clearScreen();
            menu();
        }
        else if(idZo.isEmpty()){
            super.setIdZone("Asia/Jakarta");
        }else{
            super.setIdZone(idZo);
        }
        try {
            ScheduledExecutorService a = Executors.newScheduledThreadPool(1);
            super.running=true;
            Runnable task = () -> {
                while(super.running){
                super.zonaWaktu = ZonedDateTime.now(ZoneId.of(super.getIdZone()));
                System.out.println("Jam saat ini: " + zonaWaktu.format(super.formatJam));
                try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            a.execute(task);
            this.stop();
            a.shutdown();
            clearScreen();
            System.out.println("Jam saat ini: " + zonaWaktu.format(super.formatJam));
        }catch (Exception e) {
            System.out.println("%sError: ID zona waktu '".formatted(MERAH) + super.getIdZone() + "' tidak valid.%s".formatted(RESET));
            System.out.println("Coba periksa daftar zona waktu yang tersedia di menu no 2 ");
        }
    }
    

    @Override
    public void stop(){
    Scanner sc = new Scanner(System.in);
        System.out.println("%sTekan 'Enter' untuk berhenti...%s".formatted(MERAH,RESET));
        sc.nextLine();
        super.running = false;
    }
    
     @Override
    public void tampilkanZoneId(){
        Set<String> zoneIds = new TreeSet<>(ZoneId.getAvailableZoneIds());
        zoneIds.forEach(System.out::println);
    }
    
    
    
    
    
    
    
private static String url = "jdbc:mysql://localhost:3306/daftarstopwatch";
private static String user = "root";
private static String pass = "root";

public static java.sql.Connection konek(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,user,pass);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }    
    
    
 public static String hash(String pass) {
        if (pass == null) {
            return null;
        }
        StringBuilder h = new StringBuilder();
        for (char c : pass.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                char ganti = (char)((c - 'a' + 5) % 26 + 'a');
                h.append(ganti);
            } else if (c >= 'A' && c <= 'Z') {
                char ganti = (char)((c - 'A' + 5) % 26 + 'A');
                h.append(ganti);
            } else if (c >= '0' && c <= '9') {
                char ganti = (char)((c - '0' + 5) % 10 + '0');
                h.append(ganti);
            } else {
                h.append(c);
            }
        }
        return h.toString();
    }
 
 
 
 Connection con;
 PreparedStatement pre;
ResultSet r;
 public void daftarSw(String nama, String kode){
     String b=hash(kode);
     try{
           con = konek();
            
            String sql = "INSERT INTO `member`(`nama_member`, `kode`) VALUES (?,?);";
            pre = con.prepareStatement(sql);
            pre.setString(1, nama);
            pre.setString(2, b);
            pre.executeUpdate();
            System.out.println("Pendaftaran Berhasil");
            pre.close();
        }catch (SQLException e){
            System.out.println("Koneksi Gagal" + e.getMessage());
        }                 
 }

 public boolean masukSw(String nama, String kode){
     String b=hash(kode);
     try{
          con = konek();
            
            String sql = "SELECT `id_member`, `nama_member`, `kode` FROM `member` WHERE nama_member=? AND kode=? ;";
            pre = con.prepareStatement(sql);
            pre.setString(1, nama);
            pre.setString(2, b);
            r= pre.executeQuery();
            if (!r.next()) {
                return false;
            }
            pre.close();
        }catch (SQLException e){
            System.out.println("Koneksi Gagal" + e.getMessage());
            return false;
        }
     return true;
 }
}
