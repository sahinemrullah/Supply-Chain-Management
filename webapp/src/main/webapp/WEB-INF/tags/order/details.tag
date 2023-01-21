<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="p" tagdir="/WEB-INF/tags/product/" %>

<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>  

<div class="card">
    <div class="card-header text-center py-3">
        <h5 class="mb-0 text-center">
            <strong><fmt:formatDate value="${model.createdDate}" type="both" /> Tarihli Sipariş Detayı</strong>
        </h5>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-hover text-nowrap table-striped text-center">
                <thead>
                    <tr>
                        <th scope="col">
                            <c:choose>
                                <c:when test="${role == 'supplier'}">
                                    Tedarikçi
                                </c:when>
                                <c:otherwise>
                                    Satıcı
                                </c:otherwise>
                            </c:choose>
                        </th>
                        <th scope="col">Ürün Adı</th>
                        <th scope="col">Sipariş Adedi</th>
                        <th scope="col">Stok</th>
                        <th scope="col">Tutar(Birim / Toplam)</th>
                            <c:if test="${role == 'supplier'}">
                            <th scope="col"></th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${model.products}" var="product">
                        <tr <c:if test="${product.stock < product.quantity}">class="table-danger <c:if test="${error != null}">text-danger fw-bold</c:if>"</c:if> >
                            <td class="col-2">${product.userName}</td>
                            <td class="col-3">${product.name}</td>
                            <td class="col-2">${product.quantity}</td>
                            <td class="col-2">${product.stock}</td>
                            <td class="col-2"><fmt:formatNumber value="${product.price}" type="currency" /> / <fmt:formatNumber value="${product.price * (1.0 - product.discount) * product.quantity}" type="currency" /></td>
                            <c:if test="${role == 'supplier'}">
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
                    <c:if test="${role == 'supplier'}">
                        <tr>
                            <td colspan="6">
                    <form action="/order/confirm" method="post">
                        <input type="hidden" name="orderId" value="${id}" />
                        <button type="submit" class="btn btn-primary">
                            Siparişi Onayla
                        </button>
                    </form>
                        </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<c:if test="${role == 'supplier'}">
    <p:edit>
        
    </p:edit>
</c:if>        