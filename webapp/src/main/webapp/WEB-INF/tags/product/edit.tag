<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="modal top fade" id="stockEditModal" tabindex="-1" aria-labelledby="stockEditModalLabel" aria-hidden="true" data-mdb-backdrop="true" data-mdb-keyboard="true">
    <div class="modal-dialog   modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="stockEditModalLabel">Stok Düzenle</h5>
                <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/product/edit-stock" onsubmit="editStock()">
                <div class="modal-body">
                    <div class="form-outline mb-4">
                        <input type="hidden" name="id" value="0" id="productId" />
                        <input type="number" id="stockInput" min="0" step="1" class="form-control <c:if test="${stockError != null}">is-invalid</c:if>" name="stock" value="${stock}" required />
                            <label class="form-label" for="stockInput">Yeni Stok</label>
                            <div class="invalid-feedback">${stockError == null ? "Geçerli bir sayı giriniz." : stockError}</div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
                        Kapat
                    </button>
                    <button type="submit" class="btn btn-primary">
                        Kaydet
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
