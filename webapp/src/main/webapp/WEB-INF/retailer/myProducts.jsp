<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="r" tagdir="/WEB-INF/tags/retailer/" %>

<c:choose>

    <c:when test="${isAJAXRequest}">
        <r:myProducts>

        </r:myProducts>
    </c:when>

    <c:otherwise>
        <t:template title="Detay">
            <jsp:attribute name="content">
                <r:myProducts>

                </r:myProducts>
            </jsp:attribute>
        </t:template>
    </c:otherwise>

</c:choose>
