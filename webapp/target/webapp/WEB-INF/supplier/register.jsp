<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:template title="Sign Up">
    <jsp:attribute name="content">
        <div class="col-lg-4 col-md-8 col-12 mx-auto">
          <div class="card z-index-0 fadeIn3 fadeInBottom">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">Kayıt Ol</h4>

              </div>
            </div>
            <div class="card-body">
                <form role="form" class="text-start" method="post" action="${requestScope['jakarta.servlet.forward.request_uri']}">
                <div class="input-group input-group-static my-3 ${name == null ? "" : "is-filled"} ">
                  <label class="form-label">Ad</label>
                  <input type="text" class="form-control" name="name" value="${name}">
                  <small class="text-danger">${nameError}</small>
                </div>
                <div class="input-group input-group-static my-3 ${phoneNumber == null ? "" : "is-filled"}">
                  <label class="form-label">Telefon Numarası</label>
                  <input type="text" pattern="[1-9]{1}[0-9]{9}" class="form-control" name="phoneNumber" value="${phoneNumber}">
                  <small class="text-danger">${phoneNumberError}</small>
                </div>
                <div class="input-group input-group-static my-3 ${email == null ? "" : "is-filled"}">
                  <label class="form-label">Email</label>
                  <input type="email" class="form-control" name="email" value="${email}">
                  <small class="text-danger">${emailError}</small>
                </div>
                <div class="input-group input-group-static mb-3">
                  <label class="form-label">Şifre</label>
                  <input type="password" class="form-control" name="password">
                <small class="text-danger">${passwordError}</small>
                </div>
                <div class="input-group input-group-static mb-3">
                  <label class="form-label">Şifre Onay</label>
                  <input type="password" class="form-control" name="passwordVerification">
                  <small class="text-danger">${passwordVerificationError}</small>
                </div>
                <div class="text-center">
                  <button type="submit" class="btn bg-gradient-primary w-100 my-4 mb-2">Kayıt Ol</button>
                </div>
                <p class="mt-4 text-sm text-center">
                  Hesabınız var mı? Giriş yapın.
                </p>
              </form>
            </div>
          </div>
        </div>
    </jsp:attribute>
</t:template>