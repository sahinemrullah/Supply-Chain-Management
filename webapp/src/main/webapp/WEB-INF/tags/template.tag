<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>
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
                        <img
                            src="https://mdbootstrap.com/img/logo/mdb-transaprent-noshadows.png"
                            height="25"
                            alt=""
                            loading="lazy"
                            />
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

                    <!-- Right links -->
                    <ul class="navbar-nav ms-auto d-flex flex-row">
                        <!-- Notification dropdown -->
                        <li class="nav-item dropdown">
                            <a
                                class="nav-link me-3 me-lg-0 dropdown-toggle hidden-arrow"
                                href="#"
                                id="navbarDropdownMenuLink"
                                role="button"
                                data-mdb-toggle="dropdown"
                                aria-expanded="false"
                                >
                                <i class="fas fa-bell"></i>
                                <span class="badge rounded-pill badge-notification bg-danger"
                                      >1</span
                                >
                            </a>
                            <ul
                                class="dropdown-menu dropdown-menu-end"
                                aria-labelledby="navbarDropdownMenuLink"
                                >
                                <li><a class="dropdown-item" href="#">Some news</a></li>
                                <li><a class="dropdown-item" href="#">Another news</a></li>
                                <li>
                                    <a class="dropdown-item" href="#">Something else here</a>
                                </li>
                            </ul>
                        </li>

                        <!-- Icon -->
                        <li class="nav-item">
                            <a class="nav-link me-3 me-lg-0" href="#">
                                <i class="fas fa-fill-drip"></i>
                            </a>
                        </li>
                        <!-- Icon -->
                        <li class="nav-item me-3 me-lg-0">
                            <a class="nav-link" href="#">
                                <i class="fab fa-github"></i>
                            </a>
                        </li>

                        <!-- Icon dropdown -->
                        <li class="nav-item dropdown">
                            <a
                                class="nav-link me-3 me-lg-0 dropdown-toggle hidden-arrow"
                                href="#"
                                id="navbarDropdown"
                                role="button"
                                data-mdb-toggle="dropdown"
                                aria-expanded="false"
                                >
                                <i class="united kingdom flag m-0"></i>
                            </a>
                            <ul
                                class="dropdown-menu dropdown-menu-end"
                                aria-labelledby="navbarDropdown"
                                >
                                <li>
                                    <a class="dropdown-item" href="#"
                                       ><i class="united kingdom flag"></i>English
                                        <i class="fa fa-check text-success ms-2"></i
                                        ></a>
                                </li>
                                <li><hr class="dropdown-divider" /></li>
                                <li>
                                    <a class="dropdown-item" href="#"
                                       ><i class="poland flag"></i>Polski</a
                                    >
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#"
                                       ><i class="china flag"></i>中文</a
                                    >
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#"
                                       ><i class="japan flag"></i>日本語</a
                                    >
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#"
                                       ><i class="germany flag"></i>Deutsch</a
                                    >
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#"
                                       ><i class="france flag"></i>Français</a
                                    >
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#"
                                       ><i class="spain flag"></i>Español</a
                                    >
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#"
                                       ><i class="russia flag"></i>Русский</a
                                    >
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#"
                                       ><i class="portugal flag"></i>Português</a
                                    >
                                </li>
                            </ul>
                        </li>

                        <!-- Avatar -->
                        <li class="nav-item dropdown">
                            <a
                                class="nav-link dropdown-toggle hidden-arrow d-flex align-items-center"
                                href="#"
                                id="navbarDropdownMenuLink"
                                role="button"
                                data-mdb-toggle="dropdown"
                                aria-expanded="false"
                                >
                                <img
                                    src="https://mdbootstrap.com/img/Photos/Avatars/img (31).jpg"
                                    class="rounded-circle"
                                    height="22"
                                    alt=""
                                    loading="lazy"
                                    />
                            </a>
                            <ul
                                class="dropdown-menu dropdown-menu-end"
                                aria-labelledby="navbarDropdownMenuLink"
                                >
                                <li><a class="dropdown-item" href="#">My profile</a></li>
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><a class="dropdown-item" href="#">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- Container wrapper -->
            </nav>
            <!-- Navbar -->
        </header>
        <!--Main Navigation-->

        <!--Main layout-->
        <main style="margin-top: 58px">
            <div class="container pt-4">
                <section class="mb-4" id="content">
                    <jsp:invoke fragment="content"></jsp:invoke>
                </section>
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
                    $('#searchForm').submit();
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
                            if(xhr.status === 401)
                                window.location.href = "/giris";
                        }
                    });
                    if (pushState === true)
                    {
                        var urlParams = new URLSearchParams(data).toString();
                        window.history.pushState({href: href, data: data}, '', href + "?" + urlParams);
                    }

                }
                window.addEventListener('popstate', function (e) {
                    if (e.state)
                        ajaxChangeHistory(e.state.href, e.state.data, false);
                });
        </script>
    </body>

</html>