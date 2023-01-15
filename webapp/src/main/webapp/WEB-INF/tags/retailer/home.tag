<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<div class="card">
    <div class="card-header text-center py-3">
        <h5 class="mb-0 text-center">
            <strong>Siparişler</strong>
        </h5>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-hover text-nowrap table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Satıcı</th>
                        <th scope="col">Tarihi</th>
                        <th scope="col">Tutar</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${model.items}" var="order">
                        <tr>
                            <td class="col-2">${order.orderId}</td>
                            <td class="col-3">${order.supplierName}</td>
                            <td class="col-3">${order.createdDate}</td>
                            <td class="col-2">${order.price}₺</td>
                            <td class="col-2"><a href="/siparis/goruntule?id=${order.orderId}" class="btn btn-primary">Görüntüle</a></td>
                        </tr>
                    </c:forEach>
                        
                    <tr>
                        <td colspan="4"></td>
                        <td>
                            <div class="btn-group" role="group">
                                <c:if test="${model.pageNumber > 1}">
                                    <a class="btn btn-outline-primary" onclick="addValue(this)" data-value="-1">&laquo;</a>
                                </c:if>
                                <c:if test="${model.pageNumber < model.numberOfPages}">
                                    <a class="btn btn-outline-primary" onclick="addValue(this)" data-value="1">&raquo;</a>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>