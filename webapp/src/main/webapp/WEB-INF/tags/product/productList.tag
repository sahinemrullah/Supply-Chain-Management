<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="col-lg-10 col-md-11 col-12 mx-auto">
    <div class="card z-index-0 fadeIn3 fadeInBottom">
        <div class="card-body">
            <div class="row">
                <section class="panel">
                    <div class="panel-body">
                        <div class="pull-right">
                            <ul class="pagination pagination-sm pro-page-list">
                                <c:if test="${model.pageNumber > 1}">
                                    <li><a class="btn btn-outline-primary" onclick="addValue(this)" data-value="-1">&laquo;</a></li>
                                    </c:if>
                                    <c:if test="${model.pageNumber < model.numberOfPages}">
                                    <li><a class="btn btn-outline-primary" onclick="addValue(this)" data-value="1">&raquo;</a></li>
                                    </c:if>
                            </ul>
                        </div>
                    </div>
                </section>

                <div class="row product-list">
                    <c:forEach items="${model.items}" var="product" >
                        <div class="col-md-4">
                            <section>
                                <div>
                                    <img src="${pageContext.request.contextPath}/uploads/${product.imagePath}" class="img-fluid" alt="" />
                                    
                                </div>
                                    <div class="d-flex justify-content-center">
                                        <a data-id="${product.id}" onclick="getProduct(this)" class="btn btn-lg btn-icon-only">
                                        <i class="fa fa-shopping-cart fa-lg"></i>
                                    </a>
                                    </div>
                                <div class="panel-body text-center">
                                        <a class="text-lg-center text-bold" data-id="${product.id}">
                                            ${product.name}
                                        </a>
                                    <p class="price">${product.price}₺</p>
                                </div>
                            </section>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
