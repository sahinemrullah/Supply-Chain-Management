<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:template title="Anasayfa">
    <jsp:attribute name="content">
        <div class="row align-items-center justify-content-center">
          <div class="col-md-5">
            <h2>Hoş Geldiniz</h2>

            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec erat purus, porta quis porttitor sit amet, feugiat et nibh. Suspendisse a nulla vitae eros vehicula maximus. Vivamus tincidunt, nisi id facilisis fringilla, tortor arcu vehicula enim, et efficitur odio urna ut augue. Sed sed rutrum ante, nec ornare ante. Nulla vel felis facilisis, ullamcorper mauris vitae, tincidunt purus. Phasellus id mi sed ipsum maximus egestas at non diam. Nulla sed efficitur ante, vel facilisis ex.
            </p>
          </div>
          <div class="col-md-5">
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
        </jsp:attribute>
    </t:template>