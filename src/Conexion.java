/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class Conexion {
    Connection conexion=null;
    public Connection conectar(){
        try { 
            Class.forName("oracle.jdbc.OracleDriver");
            conexion=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","CAMPEONATO","CAMPEONATO");
            //JOptionPane.showMessageDialog(null,"Conexion exitosa");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return conexion;
    }
    
}
