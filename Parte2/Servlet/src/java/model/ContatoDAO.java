/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leovi
 */
@WebServlet(name = "ContatoDAO", urlPatterns = {"/ContatoDAO"})
public class ContatoDAO extends HttpServlet {
    private Connection conexao;
    
    public ContatoDAO(){
        try{
            conexao = Conexao.criaConexao();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public ResultSet getAdmin(String id, String cpf){
        ResultSet rs = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement("select * from administradores where id = (?) or cpf = (?)");
            stmt.setString(1, id);
            stmt.setString(2, cpf);
            rs = stmt.executeQuery();
        }catch(SQLException e){
            System.out.println(e);
        }
        return rs;
    }
    public ResultSet getUser(String id, String cpf){
        ResultSet rs = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement("select * from usuarios where id = (?) or cpf = (?)");
            stmt.setString(1, id);
            stmt.setString(2, cpf);
            rs = stmt.executeQuery();
        }catch(SQLException e){
            System.out.println(e);
        }
        return rs;
    }
    public ResultSet getCat(String id){
        ResultSet rs = null;
        try{
            PreparedStatement stmt = conexao.prepareStatement("select * from categorias where id = (?)");
            stmt.setString(1, id);
            rs = stmt.executeQuery();
        }catch(SQLException e){
            System.out.println(e);
        }
        return rs;
    }
    public void setCat (String id, String descricao){
        try{
            PreparedStatement sql = conexao.prepareStatement("insert into categorias(id,descricao) values (?,?)");
            sql.setString(1, id);
            sql.setString(2, descricao);
            sql.executeUpdate();
      
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void setUser (String id, String nome, String cpf, String senha, String suspenso){
        try{
            PreparedStatement sql = conexao.prepareStatement("insert into usuarios(id,nome,cpf,senha,suspenso) values (?,?,?,?,?)");
            sql.setString(1, id);
            sql.setString(2, nome);
            sql.setString(3, cpf);
            sql.setString(4, senha);
            sql.setString(5, suspenso);
            sql.executeUpdate();
     
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void setUserUpdate(String id, String cpf,String suspenso){
        try{
            PreparedStatement sql = conexao.prepareStatement("update usuarios set suspenso = (?) where id = (?) and cpf = (?)");
            sql.setString(1, suspenso);
            sql.setString(2, id);
            sql.setString(3, cpf);
            sql.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void setAdmin (String id, String nome, String cpf, String senha){
        try{
            PreparedStatement sql = conexao.prepareStatement("insert into administradores(id,nome,cpf,senha) values (?,?,?,?)");
            sql.setString(1, id);
            sql.setString(2, nome);
            sql.setString(3, cpf);
            sql.setString(4, senha);
            sql.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
