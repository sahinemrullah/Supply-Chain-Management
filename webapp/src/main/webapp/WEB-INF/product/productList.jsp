<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="p" tagdir="/WEB-INF/tags/product/" %>

<c:choose>
    
    <c:when test="${isAJAXRequest}">
        <p:productList>

        </p:productList>
    </c:when>
    
    <c:otherwise>
        <t:template title="Ürünler">
            <jsp:attribute name="content">
                <p:productList>

                </p:productList>
            </jsp:attribute>
        </t:template>
    </c:otherwise>
    
</c:choose>
