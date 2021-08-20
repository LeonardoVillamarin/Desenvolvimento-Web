<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Admin</title>
    </head>
    <style>
        body{background-color: rgb(37, 71, 94);
            }
        h1{text-align: center;
           color: aliceblue;}
        p{text-align: center;
          color: rgb(37, 71, 94);}
    </style>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-2">
                </div>
                <div class="col-8">
                    <h1>O que gostaria de cadastrar?</h1>
                    <div class="card">
                        <%
                        Object logado = session.getAttribute("logado");
                        if (logado != null){
                            boolean check = (boolean)logado;
                            if (check == true){
                                out.println("<p>Bem Vindo "+session.getAttribute("nomeLogado"));
                            }
                            else{
                                %><jsp:forward page="Login.jsp"/><%
                            }
                        }
                        else{
                            %><jsp:forward page="Login.jsp"/><%
                        }%>
                        <div class="card-body" align="center">
                            <a href="formCategorias.html" class="btn btn-info" role="button">Categoria</a>
                            <a href="formUsuarios.html" class="btn btn-info" role="button">Usuário</a>
                            <a href="formAdmin.html" class="btn btn-info" role="button">Administrador</a>     
                        </div>
                    </div>
                </div>
                <div class="col-2">
                </div>
           </div>      
        </div>
        <div class="row">
            <div class="col-8"></div>
            <div class="col-4">
            <%
                if (session == null){
                    %><jsp:forward page="Login.jsp"/><%
                }
                else{
                    Object enviado = request.getAttribute("envio");
                    if (enviado != null){
                        boolean envio = (boolean)enviado;
                        if (envio == true){
                            %>
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <strong>Formulário Enviado com Sucesso!!</strong>
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        <%
                        }
                        if (envio == false){
                            %>
                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                    <strong>Formulário não enviado!</strong> Preencha todos os campos
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                            </div>
                        <%
                        }
                    }
                    Object idRep = request.getAttribute("IDrepetido");
                    if (idRep != null){
                        boolean repetido = (boolean)idRep;
                        if (repetido == true){%>
                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                    <strong>ID já está cadastrado</strong> 
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                            </div>
                        <%}
                    }
                    Object CPFrep = request.getAttribute("CPFrepetido");
                    if (CPFrep != null){
                        boolean repetido = (boolean)CPFrep;
                        if (repetido == true){%>
                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                    <strong>CPF já está cadastrado</strong> 
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                            </div>
                      <%}
                    }
                }%>
            </div>
        <script src="jquery-3.6.0.min.js"></script>
        <script src="https://unpkg.com/@popperjs/core@2"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
