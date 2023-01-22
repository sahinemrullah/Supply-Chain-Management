<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>  

<t:template title="Anasayfa">
    <jsp:attribute name="content">
        <div class="card">
            <div class="card-header text-center py-3">
                <h5 class="mb-0 text-center">
                    <strong>Faturalar</strong>
                </h5>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover text-nowrap table-striped text-center">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Tarihi</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${model.items}" var="invoice">
                                <tr>
                                    <td>${invoice.id}</td>
                                    <td><fmt:formatDate type="both" value="${invoice.createdDate}" /></td>
                                    <td><a href="/faturalarim/goruntule?id=${invoice.id}" class="btn btn-primary">Görüntüle</a></td>
                                </tr>
                            </c:forEach>

                            <tr>
                                <td colspan="2"></td>
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