<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ attribute name="title" required="true" rtexprvalue="true" %>
<%@ attribute name="content" fragment="true" %>
<%@ attribute name="extraScripts" fragment="true" %>

<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/favicon.png">
        <title>
            ${title}
        </title>
        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css"
            rel="stylesheet"
            />
    </head>

    <body>


        <header>
            <!-- Navbar -->
            <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-primary">
                <!-- Container wrapper -->
                <div class="container">    
                    <!-- Toggle button -->
                    <button
                        class="navbar-toggler"
                        type="button"
                        data-mdb-toggle="collapse"
                        data-mdb-target="#navbarSupportedContent1"
                        aria-controls="navbarSupportedContent1"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                        >
                        <i class="fas fa-bars"></i>
                    </button>

                    <!-- Collapsible wrapper -->
                    <div class="collapse navbar-collapse" id="navbarSupportedContent1">    
                        <!-- Left links -->
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <c:choose>
                                <c:when test="${not empty role}">
                                    <c:choose>
                                        <c:when test="${role == 'retailer'}">
                                            <t:retailerSidebar>

                                            </t:retailerSidebar>
                                        </c:when>
                                        <c:otherwise>
                                            <t:supplierSidebar>

                                            </t:supplierSidebar>
                                        </c:otherwise>
                                    </c:choose>
                                    <li class="nav-item active">
                                        <a class="nav-link text-white" href="/cikis"><i class="fas fa-sign-out-alt"></i>
                                            <span>Çıkış Yap</span></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="nav-item active">
                                        <a class="nav-link text-white" href="/giris"><i class="fas fa-home"></i>
                                            <span>Ana Sayfa</span></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                        <!-- Left links -->      
                    </div>
                    <!-- Collapsible wrapper -->

                </div>
                <c:if test="${role == 'retailer'}">
                    <!-- Container wrapper -->
                    <div class="d-flex align-items-center">
                        <form id="searchForm">
                            <input
                                autocomplete="off"
                                type="search"
                                class="form-control rounded"
                                placeholder='Urun Ara'
                                style="min-width: 225px"
                                id="query"
                                value="${param.query}"
                                />
                            <input type="hidden" id="pageNumber" value="${param.pageNumber == null ? 1 : param.pageNumber}" />
                        </form>
                    </div>
                </c:if>
            </nav>
            <!-- Navbar -->
        </header>
        <!--Main Navigation-->

        <!--Main layout-->
        <main style="margin-top: 58px">
            <div class="bg-image shadow-2-strong">
                <div class="d-flex align-items-center">
                    <div class="container pt-4">
                        <!-- Background image -->
                        <section class="mb-4" id="content">

                            <c:if test="${error != null}">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <strong>${error}</strong>
                                    <button type="button" class="btn-close" data-mdb-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>
                            <jsp:invoke fragment="content"></jsp:invoke>
                            </section>
                        </div>
                    </div>
                </div>
            </main>
            <footer class="fixed-bottom text-center text-white mt-4 bg-primary">
                <!-- Copyright -->
                <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2); text-color: #E0E0E0">
                    ©
                </div>
                <!-- Copyright -->
            </footer>
            <!-- MDB -->
            <script
                type="text/javascript"
                src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"
            ></script>
            <script
                src="https://code.jquery.com/jquery-3.6.3.min.js"
                integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
            crossorigin="anonymous"></script>
        <jsp:invoke fragment="extraScripts"></jsp:invoke>
        <script
            type="text/javascript"
            >
                $(document).ready(function () {
                    $('#searchForm').on('submit', function (e) {
                        e.preventDefault();
                        ajaxChangeHistory('/urun/ara', {
                            query: $('#query').val(),
                            pageNumber: $('#pageNumber').val()
                        }, true);
                    });
                });
                function addValue(el) {
                    var addValue = $(el).data("value");
                    var pageNumber = parseInt($('#pageNumber').val()) + parseInt(addValue);
                    $('#pageNumber').val(pageNumber);
                    $.ajax({
                        url: updateURLParameter(window.location.href, "pageNumber", pageNumber),
                        success: function (responseText) {
                            $('#content').html(responseText);
                        },
                        error: function (xhr, textStatus, errorThrown) {
                            if (xhr.status === 401)
                                window.location.href = "/giris";
                        }
                    });

                    var urlParams = new URLSearchParams(data).toString();
                    window.history.pushState({href: href, data: data}, '', href + "?" + urlParams);

                    return false;
                }
                function getProduct(el) {
                    ajaxChangeHistory('/urun/detay', {
                        id: $(el).data("id")
                    }, true);
                    return false;
                }
                function ajaxChangeHistory(href, data, pushState) {
                    $.ajax({
                        url: href,
                        data: data,
                        success: function (responseText) {
                            $('#content').html(responseText);
                        },
                        error: function (xhr, textStatus, errorThrown) {
                            if (xhr.status === 401)
                                window.location.href = "/giris";
                        }
                    });
                    if (pushState === true)
                    {
                        var urlParams = new URLSearchParams(data).toString();
                        window.history.pushState({href: href, data: data}, '', href + "?" + urlParams);
                    }

                }
                function editStockPress(el) {
                    $("#productId").val($(el).data("id"));
                    $("#stockInput").val($(el).data("stock"));
                    $("#stockInput").addClass("active");
                }
                function editDiscountPress(el) {
                    $("#discountProductId").val($(el).data("id"));
                    $("#discountInput").val($(el).data("stock"));
                    $("#discountInput").addClass("active");
                }
                window.addEventListener('popstate', function (e) {
                    if (e.state)
                        ajaxChangeHistory(e.state.href, e.state.data, false);
                });
                function updateURLParameter(url, param, paramVal) {
                    var newAdditionalURL = "";
                    var tempArray = url.split("?");
                    var baseURL = tempArray[0];
                    var additionalURL = tempArray[1];
                    var temp = "";
                    if (additionalURL) {
                        tempArray = additionalURL.split("&");
                        for (var i = 0; i < tempArray.length; i++) {
                            if (tempArray[i].split('=')[0] != param) {
                                newAdditionalURL += temp + tempArray[i];
                                temp = "&";
                            }
                        }
                    }

                    var rows_txt = temp + "" + param + "=" + paramVal;
                    return baseURL + "?" + newAdditionalURL + rows_txt;
                }
                function setHeights() {
                    var heights = [];
                    $('.carousel-item').each(function () {
                        heights.push($(this).height());
                    });

                    $('.carousel-item img').height(Math.min(...heights));
                }

                setHeights();
        </script>
    </body>

</html>