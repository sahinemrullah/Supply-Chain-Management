<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ attribute name="link" required="true" rtexprvalue="true" %>

<t:template title="Anasayfa">
    <jsp:attribute name="content">
        <div class="row justify-content-center">
            <div class="col-xl-5 col-md-8">

                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">Kayıt Ol</h4>


                <form role="form" class="bg-white rounded-5 shadow-5-strong p-4 needs-validation" method="post" action="${requestScope['jakarta.servlet.forward.request_uri']}">

                    <div class="mb-1">
                        <small class="text-danger">${nameError}</small>
                    </div>
                    <div class="form-outline mb-3 ${name == null ? "" : "is-filled"} ">
                        <input type="text" class="form-control" name="name" value="${name}">
                        <label class="form-label">Ad</label>
                    </div>
                    <div class="mb-1">
                        <small class="text-danger">${phoneNumberError}</small>
                    </div>
                    <div class="form-outline mb-3 ${phoneNumber == null ? "" : "is-filled"}">
                        <input type="text" pattern="[1-9]{1}[0-9]{9}" class="form-control" name="phoneNumber" value="${phoneNumber}">
                        <label class="form-label">Telefon Numarası</label>
                    </div>
                    <div class="mb-1">
                        <small class="text-danger">${emailError}</small>
                    </div>
                    <div class="form-outline mb-3 ${email == null ? "" : "is-filled"}">
                        <input type="email" class="form-control" name="email" value="${email}">
                        <label class="form-label">Email</label>
                    </div>
                    <div class="mb-1">
                        <small class="text-danger">${passwordError}</small>
                    </div>
                    <div class="form-outline mb-3">
                        <input type="password" class="form-control" name="password">
                        <label class="form-label">Şifre</label>
                    </div>
                    <div class="mb-1">
                        <small class="text-danger">${passwordVerificationError}</small>
                    </div>
                    <div class="form-outline mb-3">
                        <input type="password" class="form-control" name="passwordVerification">
                        <label class="form-label">Şifre Onay</label>
                    </div>
                    <%@ attribute name="extra_fields" %>
                    <div class="text-center mb-3">
                        <button type="submit" class="btn btn-primary w-100 my-4 mb-2">Kayıt Ol</button>
                    </div>
                    <div class="text-center">
                        <p>Üye misiniz? <a href="${link}">Giriş yapın.</a></p>
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