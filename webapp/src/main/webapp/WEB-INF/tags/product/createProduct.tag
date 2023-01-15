<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<div class="col-lg-8 col-md-10 col-12 mx-auto">
    <div class="card z-index-0 fadeIn3 fadeInBottom">
        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                <h4 class="text-center mt-2 mb-0">Yeni Ürün</h4>
            </div>
        </div>
        <div class="card-body">
            <form role="form" class="bg-white rounded-5 shadow-5-strong p-5 needs-validation" method="post" enctype="multipart/form-data" action="${requestScope['jakarta.servlet.forward.request_uri']}">
                <div class="form-outline mb-4 ${name == null ? "" : "is-filled"}">
                    <input type="text" class="form-control" name="name" value="${name}" required="true">
                    <label class="form-label" for="name">Ürün Adı</label>
                    <small class="text-danger">${nameError}</small>
                </div>
                <div class="form-outline mb-4 ${description == null ? "" : "is-filled"}">
                    <textarea cols="8" rows="10" class="form-control" name="description" value="${description}" required="true"></textarea>
                    <label class="form-label" for="description">Ürün Açıklaması</label>
                    <small class="text-danger">${descriptionError}</small>
                </div>
                <div class="form-outline mb-4 ${price == null ? "" : "is-filled"}">
                    <input type="number" step=".01" min="0.00" class="form-control" name="price" value="${price}" required="true">
                    <label class="form-label">Ürün Fiyatı</label>
                    <small class="text-danger">${priceError}</small>
                </div>
                <div class="form-outline mb-4 ${stock == null ? "" : "is-filled"}">
                    <input type="number" step="1" min="0" class="form-control" name="stock" value="${stock}" required="true">
                    <label class="form-label">Ürün Stoğu</label>
                    <small class="text-danger">${stockError}</small>
                </div>
                <div class="form-floating mb-4 ${stock == null ? "" : "is-filled"}">
                    <input type="file" multiple="true" class="form-control" name="images" accept="image/png, image/jpeg" required="true">
                    <label class="form-label">Ürün Resimleri</label>
                    <small class="text-danger">${imageError}</small>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary w-100 my-4 mb-2">Oluştur</button>
                </div>
            </form>
        </div>
    </div>
</div>
