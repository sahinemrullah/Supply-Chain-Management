<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

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
            <div class="col-xl-5 col-md-8">
              <form class="bg-white rounded-5 shadow-5-strong p-5 needs-validation" method="post" action="${requestScope['jakarta.servlet.forward.request_uri']}" novalidate>
                <!-- Email input -->
                <div class="form-outline mb-4">
                    <input type="email" id="emailInput" class="form-control <c:if test="${emailError != null}">is-invalid</c:if>" name="email" value="${email}" required />
                  <label class="form-label" for="emailInput">Email address</label>
                  <div class="invalid-feedback">${emailError == null ? "Geçerli bir email giriniz." : emailError}</div>
                </div>

                <!-- Password input -->
                <div class="form-outline mb-4">
                  <input type="password" id="passwordInput" class="form-control" name="password" required />
                  <label class="form-label" for="passwordInput">Password</label>
                  <div class="invalid-feedback">Şifre boş olamaz.</div>
                </div>

                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block">Giriş Yap</button>
              </form>
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