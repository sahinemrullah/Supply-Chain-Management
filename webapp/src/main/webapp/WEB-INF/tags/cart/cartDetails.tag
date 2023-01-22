<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>  

<section class="h-100" style="background-color: #eee;">
    <div class="container h-100 py-5">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-10">

                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h3 class="fw-normal mb-0 text-black">Sepetim</h3>
                </div>
                <c:if test="${cart.items.size() == 0}">
                    <div class="card rounded-3 mb-4">
                        <div class="card-body p-4">
                            <div class="row d-flex justify-content-between align-items-center">
                                <div class="text-center">Sepetinizde ürün bulunmamaktadır.</div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:forEach items="${cart.items}" var="product">
                    <div class="card rounded-3 mb-4">
                        <div class="card-body p-4">
                            <div class="row d-flex justify-content-between align-items-center">
                                <div class="col-md-2 col-lg-2 col-xl-2">
                                    <c:choose>
                                        <c:when test="${not empty product.productImages[0]}">
                                            <img
                                                src="${pageContext.request.contextPath}/uploads/${product.productImages[0]}"
                                                class="img-fluid rounded-3">
                                        </c:when>
                                        <c:otherwise>
                                            <img
                                                src="${pageContext.request.contextPath}/uploads/default.jpg"
                                                class="img-fluid rounded-3">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-3 col-lg-3 col-xl-3">
                                    <p class="lead fw-normal mb-2">${product.name}</p>
                                    <p><span class="text-muted">${product.supplierName}</span></p>
                                </div>
                                <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                    <button class="btn btn-link px-2"
                                            onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                        <i class="fas fa-minus"></i>
                                    </button>

                                    <input id="form1" min="0" name="quantity" value="${product.quantity}" type="number"
                                           class="form-control form-control-sm" />

                                    <button class="btn btn-link px-2"
                                            onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                        <i class="fas fa-plus"></i>
                                    </button>
                                </div>
                                <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                    <h5 class="mb-0">
                                        <c:choose>
                                            <c:when test="${product.discount > 0}">
                                                <div class="mb-3">
                                                    <a href="javascript:void(0)">
                                                        <span class="badge bg-danger me-1">İndirim</span>
                                                    </a>
                                                </div>

                                                <p class="lead">
                                                    <span class="me-1">
                                                        <del><fmt:formatNumber value="${product.price}" type="currency" /></del>
                                                    </span>
                                                    <span><fmt:formatNumber value="${product.price * (1.0 - product.discount)}" type="currency" /></span>
                                                </p>
                                            </c:when>
                                            <c:otherwise>
                                                <p class="lead">
                                                    <span><fmt:formatNumber value="${product.price}" type="currency" /></span>
                                                </p>
                                            </c:otherwise>
                                        </c:choose>
                                    </h5>
                                </div>
                                <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                    <form method="POST">
                                        <input type="hidden" name="id" value="${product.id}" />
                                        <button type="submit" class="btn btn-icon-only text-danger"><i class="fas fa-trash fa-lg"></i></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
                <c:if test="${!cart.items.isEmpty()}">
                    <div class="card rounded-3 mb-4">
                        <div class="card-body p-4">
                            <div class="row d-flex justify-content-between align-items-center">
                                <form method="post" action="/order/create">
                                    <button class="btn btn-warning btn-block btn-lg">Satın Al: <fmt:formatNumber value="${priceSum}" type="currency" /></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</section>