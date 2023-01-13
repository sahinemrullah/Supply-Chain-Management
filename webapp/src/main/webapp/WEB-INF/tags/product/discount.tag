<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="modal top fade" id="discountEditModal" tabindex="-1" aria-labelledby="discountEditModalLabel" aria-hidden="true" data-mdb-backdrop="true" data-mdb-keyboard="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="discountEditModalLabel">İndirim Ayarla</h5>
                <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/product/editDiscount" onsubmit="editDiscount()">
                <div class="modal-body">
                    <div class="form-outline mb-4">
                        <input type="hidden" name="id" value="0" id="discountProductId" />
                        <input type="number" id="discountInput" min="0" step="1" max="100" placeholder="0-100 Arası değer giriniz." class="form-control <c:if test="${discountError != null}">is-invalid</c:if>" name="discount" value="${discount}" required />
                            <label class="form-label" for="discountInput">İndirim (%)</label>
                            <div class="invalid-feedback">${discountError == null ? "Geçerli bir sayı giriniz." : discountError}</div>
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
