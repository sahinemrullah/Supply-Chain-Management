<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/WebPage">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>
            İstek Yerine Getirilemedi
        </title>
        <link href="/assets/css/error-page.css"
              rel="stylesheet"
            /> 
    </head>

    <body>
        <h1>500 İstek Yerine Getirilemedi.</h1>
        <p class="zoom-area">İsteğinizi işlerken bir sorun oluştu lütfen daha sonra tekrar deneyiniz.</p>
        <section class="error-container">
          <span>5</span>
          <span><span class="screen-reader-text">0</span></span>
          <span>0</span>
        </section>
        <p class="zoom-area">
            ${message}
        </p>
    </body>
</html>