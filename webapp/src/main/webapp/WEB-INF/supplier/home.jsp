<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:template title="Anasayfa">
    <jsp:attribute name="content">
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
                                <th scope="col">Tedarikçi</th>
                                <th scope="col">Tarihi</th>
                                <th scope="col">Tutar</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${model.items}" var="order">
                                <tr>
                                    <td class="col-2">${order.id}</td>
                                    <td class="col-3">${order.retailerName}</td>
                                    <td class="col-3">${order.createdDate}</td>
                                    <td class="col-2">${order.total}₺</td>
                                    <td class="col-2">
                                        <div class="btn-group">
                                            <a href="/siparis/goruntule?id=${order.id}" class="btn btn-primary">Görüntüle</a>
                                            <c:if test="${!order.isPending}">
                                                <a href="/fatura/goruntule?id=${order.id}" class="btn btn-secondary">Fatura Görüntüle</a>
                                            </c:if>
                                        </div>
                                    </td>
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
    </jsp:attribute>
</t:template>