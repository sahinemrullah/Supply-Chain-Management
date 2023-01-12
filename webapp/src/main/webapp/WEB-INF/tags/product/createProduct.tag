<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<div class="col-lg-4 col-md-8 col-12 mx-auto">
    <div class="card z-index-0 fadeIn3 fadeInBottom">
        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">Yeni Ürün</h4>

            </div>
        </div>
        <div class="card-body">
            <form role="form" class="text-start" method="post" enctype="multipart/form-data" action="${requestScope['jakarta.servlet.forward.request_uri']}">
                <div class="input-group input-group-static my-3 ${name == null ? "" : "is-filled"}">
                    <label class="form-label">Ürün Adı</label>
                    <input type="text" class="form-control" name="name" value="${name}" required="true">
                    <small class="text-danger">${nameError}</small>
                </div>
                <div class="input-group input-group-static mb-3 ${description == null ? "" : "is-filled"}"">
                    <label class="form-label">Ürün Açıklaması</label>
                    <textarea cols="8" rows="10" class="form-control" name="description" value="${description}" required="true">
                    </textarea>
                    <small class="text-danger">${descriptionError}</small>
                </div>
                <div class="input-group input-group-static my-3 ${price == null ? "" : "is-filled"}">
                    <label class="form-label">Ürün Fiyatı</label>
                    <input type="number" step=".01" min="0.00" class="form-control" name="price" value="${price}" required="true">
                    <small class="text-danger">${priceError}</small>
                </div>
                <div class="input-group input-group-static my-3 ${stock == null ? "" : "is-filled"}">
                    <label class="form-label">Ürün Stoğu</label>
                    <input type="number" step="1" min="0" class="form-control" name="stock" value="${stock}" required="true">
                    <small class="text-danger">${stockError}</small>
                </div>
                <div class="input-group input-group-static my-3 ${stock == null ? "" : "is-filled"}">
                    <label class="form-label">Ürün Resimleri</label>
                    <input type="file" multiple="true" class="form-control" name="images" accept="image/png, image/jpeg" required="true">
                    <small class="text-danger">${imageError}</small>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn bg-gradient-primary w-100 my-4 mb-2">Oluştur</button>
                </div>
            </form>
        </div>
    </div>
</div>
