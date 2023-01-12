<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="col-lg-10 col-md-11 col-12 mx-auto">
    <div class="card z-index-0 fadeIn3 fadeInBottom">
        <div class="card-body">
            <!-- product -->
            <div class="clearfix">
                <div class="row">
                    <div class="col-md-5 col-sm-12 col-xs-12">
                        <div>
                            <div id="productImagesCarousel" class="carousel slide carousel-fade" data-mdb-ride="carousel">
                                <div class="carousel-indicators">
                                    <c:forEach items="${product.productImages}" var="imagePath" varStatus="loop">
                                        <button type="button" data-mdb-target="#productImagesCarousel" data-mdb-slide-to="${loop.index}" <c:if test="${loop.index == 0}">class="active"</c:if> aria-current="true" aria-label="Slide ${loop.index}"></button>
                                    </c:forEach>
                                </div>
                                <div class="carousel-inner">
                                    <c:forEach items="${product.productImages}" var="imagePath" varStatus="loop">
                                        <div class="carousel-item ${loop.index == 0 ? "active" : ""}">
                                            <img src="${pageContext.request.contextPath}/uploads/${imagePath}" class="img-fluid" alt="" />
                                        </div>
                                    </c:forEach>
                                </div>
                                <button class="carousel-control-prev" type="button" data-mdb-target="#productImagesCarousel" data-mdb-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-mdb-target="#productImagesCarousel" data-mdb-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-md-offset-1 col-sm-12 col-xs-12">
                        <h2>
                            ${product.retailerName} ${product.name}
                        </h2>
                        <hr />
                        <h3>
                            ${product.price}₺
                        </h3>
                        <hr />
                        <a>Ürün Açıklaması</a>

                        <p>
                            ${product.description}
                        </p>
                        <hr />
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <form method="post" action="/cart/add">
                                    <input type="hidden" name="id" value="${product.id}" />
                                    <button type="submit" class="btn btn-success btn-lg">Sepete Ekle (${product.price}₺)</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end product -->
