<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <!-- Meta tags Obrigatórias -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <title>Login</title>
    </head>
    <style>
        body{background-color: rgb(37, 71, 94);}
        h1{color: white;
           text-align: center;}
    </style>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-3">
                </div>
                <div class="col-6">
                    <h1>Login</h1>
                    <div class="card">
                        <div class="card-body">
                            <form method="POST" action="login">
                                <div class="form-group">
                                    <label for="cpf">CPF</label><br>
                                    <input type="text" class="form-control mascara_cpf" id="cpf" name="cpf"><br>
                                </div>
                                <div class="form-group"></div>
                                    <label for="password">Senha</label><br>
                                    <input type="password" class="form-control" id="password" name="password"><br>
                                </div>
                                <button type="submit" class="btn btn-success">Enviar</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-3">
                </div>
            </div>
            <%if (session != null){
                Object logado = session.getAttribute("logado");
                if (logado != null){
                    boolean check = (boolean)logado;
                    if (check == false){%>
                        <div class="row">
                            <div class="col-3" align="right">
                                <div class="alert alert-danger" role="alert">
                                    CPF ou senha incorretos!! Tente novamente
                                </div>
                            </div>
                        </div>
                  <%}
                }
            }%>
        </div>
         
        <script src="jquery-3.6.0.min.js"></script>
        <script src="https://unpkg.com/@popperjs/core@2"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.mask.min.js"></script>
        <script>
            $(document).ready(function(){
                $('.mascara_cpf').mask('000.000.000-00', {reverse: true});
        });
        </script>
    </body>
</html>
