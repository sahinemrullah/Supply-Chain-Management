<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="o" tagdir="/WEB-INF/tags/order/" %>

<c:choose>

    <c:when test="${isAJAXRequest}">
        <o:details>

        </o:details>
    </c:when>

    <c:otherwise>
        <t:template title="Sipariş Detayı">
            <jsp:attribute name="content">
                <o:details>

                </o:details>
            </jsp:attribute>
        </t:template>
    </c:otherwise>

</c:choose>
