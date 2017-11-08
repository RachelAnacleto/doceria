/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doceria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anamm
 */
public class FabricaConexao {
    private static Connection conn;
    public static Connection getConexao() throws SQLException{
        try{
            if (conn==null){
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/doceria","postgres","12345678"); 
            }
            return conn;
        }catch(SQLException ex){
            throw ex;
        }
    }
}
