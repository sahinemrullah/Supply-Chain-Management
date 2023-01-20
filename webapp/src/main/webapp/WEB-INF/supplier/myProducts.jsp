<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="s" tagdir="/WEB-INF/tags/supplier/" %>

<c:choose>

    <c:when test="${isAJAXRequest}">
        <s:myProducts>

        </s:myProducts>
    </c:when>

    <c:otherwise>
        <t:template title="Ürünlerim">
            <jsp:attribute name="content">
                <s:myProducts>

                </s:myProducts>
            </jsp:attribute>
        </t:template>
    </c:otherwise>

</c:choose>
