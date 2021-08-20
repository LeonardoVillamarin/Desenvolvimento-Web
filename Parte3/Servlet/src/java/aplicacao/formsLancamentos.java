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
@WebServlet(urlPatterns = {"/formsLancamentos"})
public class formsLancamentos extends HttpServlet {
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
        String idConta = request.getParameter("id_conta");
        String idCategoria = request.getParameter("id_categoria");
        String valor = request.getParameter("valor");
        String data = request.getParameter("data");
        String operacao = request.getParameter("operacao");
        String descricao = request.getParameter("descricao");

        if (id.isEmpty() || idConta.isEmpty() || idCategoria.isEmpty() || valor.isEmpty() || data.isEmpty() || operacao.isEmpty() || descricao.isEmpty()){
            request.setAttribute("envio", false);
            RequestDispatcher rd = request.getRequestDispatcher("/escolheForm.jsp");
            rd.forward(request, response);
        }
        else{
            try{
                PreparedStatement stmt = conexao.prepareStatement("select * from contas where id = (?)");
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                boolean repetido = false;
                while(rs.next()){
                    if (rs.getString("id").equals(id)){
                        repetido = true; 
                        request.setAttribute("IDrepetido", repetido);
                        RequestDispatcher resposta = request.getRequestDispatcher("/escolheForm.jsp");
                        resposta.forward(request, response);
                    }
                }
                stmt.close();
                if (repetido == false){
                    PreparedStatement sql = conexao.prepareStatement("insert into lancamentos(id,id_conta,id_categoria,valor,operacao,data,descricao) values (?,?,?,?,?,?,?)");
                    sql.setString(1, id);
                    sql.setString(2, idConta);
                    sql.setString(3, idCategoria);
                    sql.setString(4, valor);
                    sql.setString(5, operacao);
                    sql.setString(6, data);
                    sql.setString(7, descricao);
                    sql.executeUpdate();

                    request.setAttribute("envio", true);
                    sql.close();
                    RequestDispatcher rd = request.getRequestDispatcher("escolheForm.jsp");
                    rd.forward(request, response);
                }

            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Problema ao acessar o banco");
            }
        }
    }
}