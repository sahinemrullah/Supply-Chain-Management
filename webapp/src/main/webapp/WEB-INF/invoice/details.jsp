<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>  

<t:template title="Anasayfa">
    <jsp:attribute name="content">
        <div class="card">
            <div class="card-body">
                <div class="container mb-5 mt-3">
                    <div class="row d-flex align-items-baseline">
                        <div class="col-xl-9">
                            <p style="color: #7e8d9f;font-size: 20px;">Fatura &gt;&gt; <strong>Numara: ${model.id}</strong></p>
                        </div>
                    </div>
                    <div class="container">
                        <div class="col-md-12">
                            <div class="text-lg-start">
                                <p class="pt-2">Satan: <span class="fw-bold">${model.supplier}</span></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <ul class="list-unstyled">
                                    <li class="text-muted">Satın Alan: <span class="fw-bold" style="color:#8f8061 ;">${model.retailer}</span></li>
                                </ul>
                            </div>
                            <div class="col-md-4">
                                <p class="text-muted">Fatura</p>
                                <ul class="list-unstyled">
                                    <li class="text-muted"><i class="fas fa-circle" style="color:#8f8061 ;"></i> <span
                                            class="fw-bold">Numara:</span> ${model.id}</li>
                                    <li class="text-muted"><i class="fas fa-circle" style="color:#8f8061 ;"></i> <span
                                            class="fw-bold">Tarih: </span><fmt:formatDate type="both" value="${model.createdDate}" /></li>
                                </ul>
                            </div>
                        </div>
                        <div class="row d-flex align-items-center my-2 mx-1 justify-content-center">
                            <div class="col-md-2 mb-4 mb-md-0 text-center">

                                <h5 class="mb-2">Ürün Resmi</h5>
                            </div>
                            <div class="col-md-5 mb-4 mb-md-0 text-center">
                                <h5 class="mb-2">Adı</h5>
                            </div>
                            <div class="col-md-2 mb-4 mb-md-0 text-center">
                                <h5 class="mb-2">Birim Fiyatı</h5>
                            </div>
                            <div class="col-md-1 mb-4 mb-md-0 text-center">
                                <h5 class="mb-2">Adet</h5>
                            </div>
                            <div class="col-md-2 mb-4 mb-md-0 text-center">
                                <h5 class="mb-2">Toplam Tutar</h5>
                            </div>
                        </div>
                        <c:set var="total" value="${0}"/>
                        <c:forEach items="${model.items}" var="product">
                            <div class="row d-flex align-items-center my-2 mx-1 justify-content-center">
                                <div class="col-md-2 mb-4 mb-md-0">
                                    <div class="
                                         bg-image
                                         ripple
                                         rounded-5
                                         mb-4
                                         overflow-hidden
                                         d-block
                                         " data-ripple-color="light">
                                        <img src="${pageContext.request.contextPath}/uploads/${product.imagePath}"
                                             class="img-fluid"/>
                                        <a href="javascript:void(0)">
                                            <div class="hover-overlay">
                                                <div class="mask" style="background-color: hsla(0, 0%, 98.4%, 0.2)"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-5 mb-4 mb-md-0 text-center">
                                    <p class="fw-bold">${product.name}</p>
                                </div>
                                <div class="col-md-2 mb-4 mb-md-0 text-center">
                                    <h5 class="mb-2"><fmt:formatNumber value="${product.price}" type="currency" /> </h5>
                                </div>
                                <div class="col-md-1 mb-4 mb-md-0 text-center">
                                    <h5 class="mb-2">${product.quantity}</h5>
                                </div>
                                <div class="col-md-2 mb-4 mb-md-0 text-center">
                                    <c:choose>
                                        <c:when test="${product.discount > 0}">
                                            <h5 class="mb-2">
                                                <s class="text-muted me-2 small align-middle"><fmt:formatNumber value="${product.price * product.quantity}" type="currency" /> </s><span class="align-middle"><fmt:formatNumber value="${product.price * (1 - product.discount) * product.quantity}" type="currency" /></span>
                                            </h5>
                                            <p class="text-danger"><small>İndirim <fmt:formatNumber value="${product.discount}" type="percent" /></small></p>
                                        </c:when>
                                        <c:otherwise>
                                            <h5 class="mb-2">
                                                <fmt:formatNumber value="${product.price * product.quantity}" type="currency" />
                                            </h5>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:set var="total" value="${total + (product.price * (1 - product.discount) * product.quantity)}" />
                                </div>
                            </div>
                            <hr>
                        </c:forEach>
                        <div class="row d-flex align-items-center">
                            <div class="col-md-8">
                            </div>
                            <div class="col-md-3">
                                <p class="text-black float-start"><span class="text-black me-3"> Toplam</span><span
                                        style="font-size: 25px;"><fmt:formatNumber value="${total}" type="currency" /></span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:template>