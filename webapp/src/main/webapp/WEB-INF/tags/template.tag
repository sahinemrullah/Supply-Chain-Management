<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ attribute name="title" required="true" rtexprvalue="true" %>
<%@ attribute name="content" fragment="true" %>

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
        <link id="pagestyle" href="${pageContext.request.contextPath}/assets/css/product.css" rel="stylesheet" />
    </head>

    <body>


        <header>
            <!-- Sidebar -->
            <nav
                id="sidebarMenu"
                class="collapse d-lg-block sidebar collapse bg-white"
                >
                <div class="position-sticky">
                    <div class="list-group list-group-flush mx-3 mt-4">
                        <a
                            href="/"
                            class="list-group-item list-group-item-action py-2 ripple active"
                            aria-current="true"
                            >
                            <i class="fas fa-tachometer-alt fa-fw me-3"></i
                            ><span>Ana Sayfa</span>
                        </a>
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
                    </div>
                </div>
            </nav>
            <!-- Sidebar -->

            <!-- Navbar -->
            <nav
                id="main-navbar"
                class="navbar navbar-expand-lg navbar-light bg-white fixed-top"
                >
                <!-- Container wrapper -->
                <div class="container-fluid">
                    <!-- Toggle button -->
                    <button
                        class="navbar-toggler"
                        type="button"
                        data-mdb-toggle="collapse"
                        data-mdb-target="#sidebarMenu"
                        aria-controls="sidebarMenu"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                        >
                        <i class="fas fa-bars"></i>
                    </button>

                    <!-- Brand -->
                    <a class="navbar-brand" href="#">

                    </a>
                    <!-- Search form -->
                    <form id="searchForm" class="d-none d-md-flex input-group w-auto my-auto">
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
                        <span class="input-group-text border-0"
                              ><i class="fas fa-search"></i
                            ></span>
                    </form>
                </div>
                <!-- Container wrapper -->
            </nav>
            <!-- Navbar -->
        </header>
        <!--Main Navigation-->

        <!--Main layout-->
        <main style="margin-top: 58px">
            <div id="intro" class="bg-image shadow-2-strong">
                <div class="mask d-flex align-items-center" style="background-color: rgba(0, 0, 0, 0.8);">
                    <div class="container pt-4">
                        <style>
                            #intro {
                                background-image: url(https://mdbootstrap.com/img/new/fluid/city/008.jpg);
                                height: 100vh;
                            }

                            .navbar .nav-link {
                                color: #fff !important;
                            }
                        </style> 
                        <!-- Background image -->
                        <section class="mb-4" id="content">
                            <jsp:invoke fragment="content"></jsp:invoke>
                        </section>
                    </div>
                </div>
            </div>
        </main>

        <!-- MDB -->
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"
        ></script>
        <script
            src="https://code.jquery.com/jquery-3.6.3.min.js"
            integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
        crossorigin="anonymous"></script>
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