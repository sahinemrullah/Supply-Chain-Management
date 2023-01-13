<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="p" tagdir="/WEB-INF/tags/product/" %>

<div class="card">
    <div class="card-header text-center py-3">
        <h5 class="mb-0 text-center">
            <strong>Ürünlerim</strong>
        </h5>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-hover text-nowrap table-striped text-center">
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Ürün Adı</th>
                        <th scope="col">Tutar</th>
                        <th scope="col">Stok</th>
                        <th scope="col">İndirim</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${model.items}" var="product">
                        <tr>
                            <td class="col-3"><img class="img-thumbnail" src="${pageContext.request.contextPath}/uploads/${product.imagePath}" /></td>
                            <td class="col-3">${product.name}</td>
                            <td class="col-2">${product.price}₺</td>
                            <td class="col-2">${product.stock}</td>
                            <td class="col-2">${product.discount * 100}%</td>
                            <td class="col-1">
                                <a class="btn btn-primary" 
                                   data-id="${product.id}" 
                                   data-stock="${product.stock}" 
                                   onclick="editStockPress(this)" 
                                   data-mdb-toggle="modal" 
                                   data-mdb-target="#stockEditModal">
                                    Stok Düzenle
                                </a>
                            </td>
                            <td class="col-1">
                                   <a class="btn btn-secondary" 
                                   data-id="${product.id}"  
                                   onclick="editDiscountPress(this)" 
                                   data-mdb-toggle="modal" 
                                   data-mdb-target="#discountEditModal">
                                       <c:choose>
                                           <c:when test="${product.discount > 0}">
                                               İndirim Ayarla
                                           </c:when>
                                           <c:otherwise>
                                               İndirim Yap
                                           </c:otherwise>
                                       </c:choose>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<p:edit>

</p:edit>
<p:discount>

</p:discount>