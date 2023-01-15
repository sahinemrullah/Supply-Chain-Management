<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>
            Kayıt Ol
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
                            <div class="col-xl-5 col-md-8">

                                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">Kayıt Ol</h4>


                                <form role="form" class="bg-white rounded-5 shadow-5-strong p-5 needs-validation" method="post" action="${requestScope['jakarta.servlet.forward.request_uri']}">
                                    <div class="form-outline mb-4 ${name == null ? "" : "is-filled"} ">
                                        <input type="text" class="form-control" name="name" value="${name}">
                                        <label class="form-label">Ad</label>
                                        <small class="text-danger">${nameError}</small>
                                    </div>
                                    <div class="form-outline mb-4 ${phoneNumber == null ? "" : "is-filled"}">
                                        <input type="text" pattern="[1-9]{1}[0-9]{9}" class="form-control" name="phoneNumber" value="${phoneNumber}">
                                        <label class="form-label">Telefon Numarası</label>
                                        <small class="text-danger">${phoneNumberError}</small>
                                    </div>
                                    <div class="form-outline mb-4 ${email == null ? "" : "is-filled"}">
                                        <input type="email" class="form-control" name="email" value="${email}">
                                        <label class="form-label">Email</label>
                                        <small class="text-danger">${emailError}</small>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input type="password" class="form-control" name="password">
                                        <label class="form-label">Şifre</label>
                                        <small class="text-danger">${passwordError}</small>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input type="password" class="form-control" name="passwordVerification">
                                        <label class="form-label">Şifre Onay</label>
                                        <small class="text-danger">${passwordVerificationError}</small>
                                    </div>
                                    <%@ attribute name="extra_fields" %>
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary w-100 my-4 mb-2">Kayıt Ol</button>
                                    </div>
                                </form>

                            </div>
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
<script type="text/javascript">
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';

// Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation');

// Loop over them and prevent submission
        Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        console.log("a");
                        if (!form.checkValidity()) {
                            event.preventDefault();
                            event.stopPropagation();
                        }

                        form.classList.add('was-validated');
                    }, false);
                });
    })();
</script>
</body>
</html>