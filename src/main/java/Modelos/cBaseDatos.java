/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.*;
import java.util.*;

public class cBaseDatos {
    String driver  = "com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    String usuario = "root";
    String clave   = "";

    private Connection Conectar() {
        try {
            Class.forName(driver);
            Connection xcon = DriverManager.getConnection(url,usuario,clave);
            return xcon;
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return null;
    
    }
    public String Estado(String xnom, String xcla) {
        try {
            Connection xcon = this.Conectar();
            String sql = "select estado from t_usuarios where nombre=? AND clave=? LIMIT 1";
            PreparedStatement ps=xcon.prepareStatement(sql);
            ps.setString(1, xnom );
            ps.setString(2, xcla );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Procesa el primer resultado aquí
                return rs.getString("estado");
            }
        } catch (SQLException ex ) {
            System.out.println(ex.toString());
            return "no se cargo";

        }
        return "no se cargo";
    }
    
    public boolean validarUsuario(String xnom, String xcla) {
        try {
            Connection xcon = this.Conectar();
            String sql = "select count(*) from t_usuarios where nombre=? AND clave=?";
            PreparedStatement ps=xcon.prepareStatement(sql);
            ps.setString(1, xnom );
            ps.setString(2, xcla );
            ResultSet rs = ps.executeQuery();
 	     rs.next();
            String cantidad = rs.getString(1);
            int xcant = Integer.parseInt( cantidad );
            if ( xcant > 0 )
                return true;
            xcon.close();
            return false;
            
        } catch (Exception ex ) {
            System.out.println(ex.toString());
        }
        return false;
    }
    
}
