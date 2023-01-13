<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="p" tagdir="/WEB-INF/tags/product/" %>

<div class="card">
    <div class="card-header text-center py-3">
        <h5 class="mb-0 text-center">
            <strong>${model.createdDate} Tarihli Sipariş Detayı</strong>
        </h5>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-hover text-nowrap table-striped text-center">
                <thead>
                    <tr>
                        <th scope="col">
                            <c:choose>
                                <c:when test="${isRetailer}">
                                    Satıcı
                                </c:when>
                                <c:otherwise>
                                    Tedarikçi
                                </c:otherwise>
                            </c:choose>
                        </th>
                        <th scope="col">Ürün Adı</th>
                        <th scope="col">Sipariş Adedi</th>
                        <th scope="col">Stok</th>
                        <th scope="col">Tutar(Birim / Toplam)</th>
                            <c:if test="${isRetailer}">
                            <th scope="col"></th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${model.products}" var="product">
                        <tr <c:if test="${product.stock < product.quantity}">class="table-danger" </c:if>>
                            <td class="col-2">${product.userName}</td>
                            <td class="col-3">${product.name}</td>
                            <td class="col-2">${product.quantity}</td>
                            <td class="col-2">${product.stock}</td>
                            <td class="col-2">${product.price}₺ / ${product.price * product.quantity}₺</td>
                            <c:if test="${isRetailer}">
                                <td class="col-1">
                                    <c:if test="${product.stock < product.quantity}">
                                        <a class="btn btn-danger" 
                                           data-id="${product.id}" 
                                           data-stock="${product.stock}" 
                                           onclick="editStockPress(this)" 
                                           data-mdb-toggle="modal" 
                                           data-mdb-target="#stockEditModal">
                                            Stok Düzenle
                                        </a>
                                    </c:if>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<c:if test="${isRetailer}">
    <p:edit>
        
    </p:edit>
</c:if>        