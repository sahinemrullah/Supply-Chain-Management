<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="p" tagdir="/WEB-INF/tags/product/" %>

<c:choose>

    <c:when test="${isAJAXRequest}">
        <p:createProduct>

        </p:createProduct>
    </c:when>

    <c:otherwise>
        <t:template title="Yeni Ürün">
            <jsp:attribute name="content">
                <p:createProduct>

                </p:createProduct>
            </jsp:attribute>
        </t:template>
    </c:otherwise>

</c:choose>
