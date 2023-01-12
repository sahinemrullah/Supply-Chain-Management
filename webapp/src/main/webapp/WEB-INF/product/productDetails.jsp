<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="p" tagdir="/WEB-INF/tags/product/" %>

<c:choose>

    <c:when test="${isAJAXRequest}">
        <p:productDetails>

        </p:productDetails>
    </c:when>

    <c:otherwise>
        <t:template title="Detay">
            <jsp:attribute name="content">
                <p:productDetails>

                </p:productDetails>
            </jsp:attribute>
        </t:template>
    </c:otherwise>

</c:choose>
