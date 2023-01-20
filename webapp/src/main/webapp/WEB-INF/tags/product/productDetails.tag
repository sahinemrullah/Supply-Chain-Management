<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>  

<!--Grid row-->
<div class="card">
    <div class="row">
        <!--Grid column-->
        <div class="col-md-4 mb-4">
            <!-- Carousel wrapper -->
            <div id="carouselExampleIndicators" class="carousel slide" data-mdb-ride="carousel">
                <!-- Slides -->
                <div class="text-center carousel-inner rounded-5 shadow-4-strong mb-5">
                    <c:forEach items="${model.productImages}" var="imagePath" varStatus="loop">
                        <div class="carousel-item ${loop.index == 0 ? "active" : ""}">
                            <img src="${pageContext.request.contextPath}/uploads/${imagePath}" class="img-fluid" alt="" />
                        </div>
                    </c:forEach>
                </div>
                <!-- Slides -->

                <!-- Controls -->
                <button class="carousel-control-prev" type="button" data-mdb-target="#carouselExampleIndicators"
                        data-mdb-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Önceki</span>
                </button>
                <button class="carousel-control-next" type="button" data-mdb-target="#carouselExampleIndicators"
                        data-mdb-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Sonraki</span>
                </button>
                <!-- Controls -->

                <!-- Thumbnails -->
                <div class="carousel-indicators d-flex align-items-stretch" style="margin-bottom: -20px;">
                    <c:forEach items="${model.productImages}" var="imagePath" varStatus="loop">
                        <button type="button" data-mdb-target="#carouselExampleIndicators" data-mdb-slide-to="${loop.index}" <c:if test="${loop.index == 0}">class="active"</c:if> aria-current="true" aria-label="Fotoğraf ${loop.index}">
                                <img class="d-block img-thumbnail"
                                     src="${pageContext.request.contextPath}/uploads/${imagePath}" />
                        </button>
                    </c:forEach>

                </div>
                <!-- Thumbnails -->
            </div>
            <!-- Carousel wrapper -->
        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-md-8 mb-4">
            <!--Content-->
            <div class="p-4">
                <c:choose>
                    <c:when test="${model.discount > 0}">
                        <div class="mb-3">
                            <a href="">
                                <span class="badge bg-danger me-1">İndirim</span>
                            </a>
                        </div>

                        <p class="lead">
                            <span class="me-1">
                                <del><fmt:formatNumber value="${model.price}" type="currency" /></del>
                            </span>
                            <span><fmt:formatNumber value="${model.price * (1.0 - model.discount)}" type="currency" /></span>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p class="lead">
                            <span><fmt:formatNumber value="${model.price}" type="currency" /></span>
                        </p>
                    </c:otherwise>
                </c:choose>

                <strong><p style="font-size: 20px;">Ürün Açıklaması</p></strong>

                <p>${model.description}</p>

                <form class="d-flex justify-content-left">
                    <!-- Default input -->
                    <div class="form-outline me-1" style="width: 100px;">
                        <input type="number" value="1" class="form-control" />
                    </div>
                    <button class="btn btn-primary ms-1" type="submit">
                        Sepete Ekle
                        <i class="fas fa-shopping-cart ms-1"></i>
                    </button>
                </form>
            </div>
            <!--Content-->
        </div>
        <!--Grid column-->
    </div>
    <!--Grid row-->
</div>
