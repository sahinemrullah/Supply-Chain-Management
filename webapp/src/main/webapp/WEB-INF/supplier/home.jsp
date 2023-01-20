<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="s" tagdir="/WEB-INF/tags/supplier/" %>

<c:choose>

    <c:when test="${isAJAXRequest}">
        <s:home>

        </s:home>
    </c:when>

    <c:otherwise>
        <t:template title="Siparişler">
            <jsp:attribute name="content">
                <s:home>

                </s:home>
            </jsp:attribute>
        </t:template>
    </c:otherwise>

</c:choose>
