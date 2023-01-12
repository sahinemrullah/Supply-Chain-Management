<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ tag import ="com.webapp.models.ProductDetailsModel" %>
<div class="card">
    <div class="card-header text-center py-3">
        <h5 class="mb-0 text-center">
            <strong>Sepetim</strong>
        </h5>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-hover text-nowrap table-striped">
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Tedarikçi</th>
                        <th scope="col">Ürün Adı</th>
                        <th scope="col">Tutar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cart.items}" var="product">
                        <tr>
                            <td class="col-3"><img class="img-thumbnail" src="${pageContext.request.contextPath}/uploads/${product.productImages[0]}" /></td>
                            <td class="col-3">${product.retailerName}</td>
                            <td class="col-3">${product.name}</td>
                            <td class="col-3">${product.price}₺</td>
                        </tr>
                    </c:forEach>
                        <tr>
                            <td colspan="2"></td>
                            <td colspan="2">Toplam Tutar: ${priceSum}₺</td>
                        </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>