<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ attribute name="link" required="true" rtexprvalue="true" %>

<t:template title="Anasayfa">
    <jsp:attribute name="content">
          <div class="row justify-content-center">
            <div class="col-xl-5 col-md-8">
              <form class="bg-white rounded-5 shadow-5-strong p-4 needs-validation" method="post" action="${requestScope['jakarta.servlet.forward.request_uri']}" novalidate>
                <!-- Email input -->
                <div class="form-outline mb-4">
                    <input type="email" id="emailInput" class="form-control <c:if test="${emailError != null}">is-invalid</c:if>" name="email" value="${email}" required />
                  <label class="form-label" for="emailInput">Email adresi</label>
                  <div class="invalid-feedback">${emailError == null ? "Geçerli bir email giriniz." : emailError}</div>
                </div>

                <!-- Password input -->
                <div class="form-outline mb-4">
                  <input type="password" id="passwordInput" class="form-control" name="password" required />
                  <label class="form-label" for="passwordInput">Şifre</label>
                  <div class="invalid-feedback">Şifre boş olamaz.</div>
                </div>

                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block mb-3">Giriş Yap</button>
                    <div class="text-center">
                        <p>Üye değil misiniz? <a href="${link}">Üye olun.</a></p>
                    </div>
              </form>
            </div>
          </div>
    </jsp:attribute>
    <jsp:attribute name="extraScripts">
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
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }

        form.classList.add('was-validated');
      }, false);
    });
})();
        </script>
    </jsp:attribute>
</t:template>