<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>  
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>  

<section class="panel">

    <div class="d-flex justify-content-center m-2 mb-3">
        <c:if test="${model.pageNumber > 1}">

            <button class="carousel-control-prev btn btn-pink btn-floating mx-3" type="button" tabindex="0" data-mdb-slide="prev">
                <i class="fas fa-angle-left fa-lg"></i>
            </button>
        </c:if>
        <c:if test="${model.pageNumber < model.numberOfPages}">

            <button class="carousel-control-next btn btn-pink btn-floating mx-3" type="button" tabindex="0" data-mdb-slide="next">
                <i class="fas fa-angle-right fa-lg"></i>
            </button>
        </c:if>
    </div>
</section>
<section style="background-color: #eee;">
    <div class="container py-5">

        <c:forEach items="${model.items}" var="product" >
            <div class="row justify-content-center mb-3">
                <div class="col-md-12 col-xl-10">
                    <div class="card shadow-0 border rounded-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-3 col-xl-3 mb-4 mb-lg-0">
                                    <div class="bg-image hover-zoom ripple rounded ripple-surface">
                                        <img src="${pageContext.request.contextPath}/uploads/${product.imagePath}"
                                             class="w-100" />
                                        <a href="#!">
                                            <div class="hover-overlay">
                                                <div class="mask" style="background-color: rgba(253, 253, 253, 0.15);"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-6 col-xl-6">
                                    <h5>${product.name}</h5>
                                    <p class="mb-4 mb-md-0">
                                        ${product.description}<c:if test="${fn:length(product.description) == 300}">...</c:if>
                                        </p>
                                    </div>
                                    <div class="col-md-6 col-lg-3 col-xl-3 border-sm-start-none border-start">
                                        <div class="d-flex flex-row align-items-center mb-1">

                                        <c:choose>
                                            <c:when test="${product.discount > 0}">
                                                <p class="lead">
                                                    <span class="me-1">
                                                        <del><fmt:formatNumber value="${product.price}" type="currency" /></del>
                                                    </span>
                                                    <span><fmt:formatNumber value="${product.price * (1.0 - product.discount)}" type="currency" /></span>
                                                </p>
                                                <div class="mb-3">
                                                    <a href="javascript:void(0)">
                                                        <span class="badge bg-danger me-1">%${100 * product.discount} İndirim</span>
                                                    </a>
                                                </div>

                                            </c:when>
                                            <c:otherwise>
                                                <p class="lead">
                                                    <span><fmt:formatNumber value="${product.price}" type="currency" /></span>
                                                </p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="d-flex flex-column mt-4">
                                        <button data-id="${product.id}" onclick="getProduct(this)"  class="btn btn-primary btn-sm" type="button">İncele</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
