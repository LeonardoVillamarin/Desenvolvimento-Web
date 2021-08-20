package aplicacao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ContatoDAO;

/**
 *
 * @author leovi
 */
@WebServlet(urlPatterns = {"/formsAdmin"})
public class formsAdmin extends HttpServlet {
    Connection conexao = null;
    public void init() throws ServletException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/financeiro","root","");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Não foi possível encontrar o Driver");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Não foi possível conectar ao banco");
        }   
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");      
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("password");
        
        if (id.isEmpty() || nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()){
            request.setAttribute("envio", false);
            RequestDispatcher rd = request.getRequestDispatcher("/escolheForm.jsp");
            rd.forward(request, response);
        }
        else{
            try{
                ContatoDAO dao = new ContatoDAO();
                ResultSet rs = dao.getAdmin(id, cpf);
                boolean repetido = false;
                while(rs.next()){
                    if (rs.getString("id").equals(id)){
                        repetido = true;
                        request.setAttribute("IDrepetido", repetido);
                     
                        RequestDispatcher resposta = request.getRequestDispatcher("/escolheForm.jsp");
                        resposta.forward(request, response);
                    }
                    if (rs.getString("cpf").equals(cpf)){
                        repetido = true;
                        request.setAttribute("CPFrepetido", repetido);
                    
                        RequestDispatcher resposta = request.getRequestDispatcher("/escolheForm.jsp");
                        resposta.forward(request, response);
                    }
                }
                if (repetido == false){
                    dao.setAdmin(id, nome, cpf, senha);

                    request.setAttribute("envio", true);

                    RequestDispatcher rd = request.getRequestDispatcher("/escolheForm.jsp");
                    rd.forward(request, response);
                }

            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Problema ao acessar o banco");
            }
        }
    }
}