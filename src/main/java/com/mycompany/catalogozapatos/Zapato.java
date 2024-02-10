/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catalogozapatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */

public class Zapato {

    public Zapato(int Id, String Modelo, String Talla, String Color, float precio) {
        this.Id = Id;
        this.Modelo = Modelo;
        this.Talla = Talla;
        this.Color = Color;
        this.precio = precio;
    }
    public Zapato(){
        
    }
    
    int Id;
    String Modelo ;
    String Talla;
    String Color;
    float precio;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String Talla) {
        this.Talla = Talla;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public void GuardarZapato(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "INSERT INTO Zapatos (Id, modelo , color,talla,precio) VALUES (null,?,?,?,?)";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getModelo());
            parametro.setString(2, this.getColor());
            parametro.setString(3, this.getTalla());
            parametro.setFloat(4, this.getPrecio());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    
    public void ModificarZapato(){
        Connection conexionDb = ConexionDb.getConnection();
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "UPDATE Zapatos SET Modelo=?, color=?,talla=?,precio=? WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getModelo());
            parametro.setString(2, this.getColor());
            parametro.setString(3, this.getTalla());
            parametro.setFloat(4, this.getPrecio());
            parametro.setInt(5, this.getId());


            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public ArrayList<Zapato> ObtenerZapatos(){
        Connection conexionDb = ConexionDb.getConnection();
        ResultSet rsZapatos;
        
        var zapatos = new ArrayList<Zapato>();
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "SELECT * FROM camisas";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            
            //Ejecutar la sentecia SQL
             rsZapatos=parametro.executeQuery();           
            
            while(rsZapatos.next()){              
                    zapatos.add(new Zapato(rsZapatos.getInt("Id"),rsZapatos.getString("Modelo"),rsZapatos.getString("talla"),rsZapatos.getString("color"),rsZapatos.getFloat("precio")));

            }
            
            conexionDb.close();
            return zapatos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        
    }
    
   public void EliminarZapatos() {
Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "DELETE FROM camisas WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setInt(1, this.getId());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
