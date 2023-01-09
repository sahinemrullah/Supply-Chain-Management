<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:template title="Sign Up">
    <jsp:attribute name="content">
        <div class="col-lg-4 col-md-8 col-12 mx-auto">
          <div class="card z-index-0 fadeIn3 fadeInBottom">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">Giriş Yap</h4>

              </div>
            </div>
            <div class="card-body">
                <form role="form" class="text-start" method="post" action="${requestScope['jakarta.servlet.forward.request_uri']}">
                <div class="input-group input-group-static my-3 ${email == null ? "" : "is-filled"}">
                  <label class="form-label">Email</label>
                  <input type="email" class="form-control" name="email" value="${email}">
                  <small class="text-danger">${emailError}</small>
                </div>
                <div class="input-group input-group-static mb-3">
                  <label class="form-label">Şifre</label>
                  <input type="password" class="form-control" name="password">
                </div>
                <div class="text-center">
                  <button type="submit" class="btn bg-gradient-primary w-100 my-4 mb-2">Giriş Yap</button>
                </div>
              </form>
            </div>
          </div>
        </div>
    </jsp:attribute>
</t:template>