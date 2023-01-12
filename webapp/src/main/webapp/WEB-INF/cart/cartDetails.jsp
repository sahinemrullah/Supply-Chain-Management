<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/cart/" %>

<c:choose>

    <c:when test="${isAJAXRequest}">
        <cart:cartDetails>

        </cart:cartDetails>
    </c:when>

    <c:otherwise>
        <t:template title="Sepet">
            <jsp:attribute name="content">
                <cart:cartDetails>

                </cart:cartDetails>
            </jsp:attribute>
        </t:template>
    </c:otherwise>

</c:choose>
