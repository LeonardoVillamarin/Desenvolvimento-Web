/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leovi
 */
@WebServlet(name = "Conexao", urlPatterns = {"/Conexao"})
public class Conexao extends HttpServlet {

    private static Connection conexao = null;
       
    public static Connection criaConexao() throws SQLException{
        if (conexao == null){
            try{
            Class.forName("com.mysql.jdbc.Driver");
            conexao = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/financeiro","root","");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
                System.out.println("Não foi possível encontrar o Driver");
            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Não foi possível conectar ao banco");
            }
        }
        return conexao;
    }
}
