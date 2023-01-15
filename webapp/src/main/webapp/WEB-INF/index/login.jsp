<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>
            Giriş
        </title>
        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css"
            rel="stylesheet"
            />
    </head>

    <body>
        <main>
            <style>
                #intro {
                    background-image: url(https://mdbootstrap.com/img/new/fluid/city/008.jpg);
                    height: 100vh;
                }

                .navbar .nav-link {
                    color: #fff !important;
                }
            </style> 
            <!-- Background image -->
            <div id="intro" class="bg-image shadow-2-strong">
                <div class="mask d-flex align-items-center h-100" style="background-color: rgba(0, 0, 0, 0.8);">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row justify-content-center">
                                            <ul class="list-group list-group-light text-center">
                                                <li class="list-group-item"><a href="/tedarikci/giris" class="btn btn-primary">Tedarikçi Giriş</a></li>
                                                <li class="list-group-item"><a href="/tedarikci/kayitol" class="btn btn-primary">Tedarikçi Kayıt</a></li>
                                                <li class="list-group-item"><a href="/satici/giris" class="btn btn-primary">Satıcı Giriş</a></li>
                                                <li class="list-group-item"><a href="/satici/kayitol" class="btn btn-primary">Satıcı Kayıt</a></li>
                                            </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Background image -->
        </main>
        <!--Main Navigation-->

        <!-- MDB -->
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"
        ></script>
    </body>
</html>