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

/**
 *
 * @author leovi
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("password");

        if (cpf.isEmpty() || senha.isEmpty()){
            response.sendRedirect("index.html");
        }
        else{
            try{
                PreparedStatement stmt = conexao.prepareStatement("select * from administradores where cpf = (?)");
                stmt.setString(1, cpf);
                ResultSet adminRs = stmt.executeQuery();
                boolean logado = false;
                while(adminRs.next()){
                    if (adminRs.getString("cpf").equals(cpf) && adminRs.getString("senha").equals(senha)){
                        logado = true; 
                        HttpSession session = request.getSession();
                        session.setAttribute("nomeLogado", adminRs.getString("nome"));
                        session.setAttribute("logado", logado);
                        stmt.close();
                        RequestDispatcher resposta = request.getRequestDispatcher("/escolheForm.jsp");
                        resposta.forward(request, response);
                    }
                }
                if (logado == false){
                    stmt = conexao.prepareStatement("select * from usuarios where cpf = (?)");
                    stmt.setString(1, cpf);
                    ResultSet usersRs = stmt.executeQuery();
                    while (usersRs.next()){
                        if (usersRs.getString("cpf").equals(cpf) && usersRs.getString("senha").equals(senha)){
                            logado = true;
                            HttpSession session = request.getSession();
                            session.setAttribute("nomeLogado", usersRs.getString("nome"));
                            session.setAttribute("logado", logado);
                            stmt.close();
                            RequestDispatcher resposta = request.getRequestDispatcher("/escolheForm.jsp");
                            resposta.forward(request, response);
                        }
                    }
                    if (logado == false){
                        HttpSession session = request.getSession();
                        session.setAttribute("logado", logado);
                        stmt.close();
                        RequestDispatcher resposta = request.getRequestDispatcher("/Login.jsp");
                        resposta.forward(request, response);
                    }
                }

            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Problema ao acessar o banco");
            }
        }
    }
    public void destroyCon(){
        try{
            conexao.close();
        }catch(SQLException e){
            System.out.println("Não foi possível fechar o banco");
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
