<%--
  Created by IntelliJ IDEA.
  User: mickl
  Date: 15/04/2023
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Inscription - Informations sur l’intelligence artificielle">
    <title>Inscription - Information d'intelligence artificielle</title>
    <link rel="stylesheet" href="public/css/simplebar.css">
    <link href="https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="public/css/feather.css">
    <link rel="stylesheet" href="public/css/daterangepicker.css">
    <link rel="stylesheet" href="public/css/app-light.css" id="lightTheme" disabled>
    <link rel="stylesheet" href="public/css/app-dark.css" id="darkTheme">
</head>
<body class="dark ">
<div class="wrapper vh-100">
    <div class="row align-items-center h-100">
        <form class="col-lg-3 col-md-4 col-10 mx-auto text-center" action="<%=request.getContextPath()%>/Inscription" method="post">
            <a class="navbar-brand mx-auto mt-2 flex-fill text-center">
                <svg version="1.1" id="logo" class="navbar-brand-img brand-md" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 120 120" xml:space="preserve">
              <g>
                  <polygon class="st0" points="78,105 15,105 24,87 87,87 	" />
                  <polygon class="st0" points="96,69 33,69 42,51 105,51 	" />
                  <polygon class="st0" points="78,33 15,33 24,15 87,15 	" />
              </g>
            </svg>
            </a>
            <h1 class="h6 mb-3">S'inscrire rapidement</h1>
            <div class="form-group">
                <label class="sr-only">Votre Email</label>
                <input type="texte" name="mail" class="form-control form-control-lg" placeholder="Votre Email">
            </div>
            <div class="form-group">
                <label class="sr-only">Votre Mot de passe</label>
                <input type="text" name="mdp" class="form-control form-control-lg" placeholder="Votre Mot de passe">
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">S'inscrire</button>
            <p class="mt-5 mb-3 text-muted">© <script>document.write(new Date().getFullYear())</script> - Aldonis</p>
        </form>
    </div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/moment.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/simplebar.min.js"></script>
<script src="js/tinycolor-min.js"></script>
<script src="js/config.js"></script>
<script src="js/apps.js"></script>

<script async src="https://www.googletagmanager.com/gtag/js?id=UA-56159088-1"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag()
    {
        dataLayer.push(arguments);
    }
    gtag('js', new Date());
    gtag('config', 'UA-56159088-1');
</script>
</body>
</html>