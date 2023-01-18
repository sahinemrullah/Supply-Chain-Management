
package com.webapi.application.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String name, String id) {
        super(String.format("İstenen %s bulunamadı.(id=%s)", name, id));
    }
}
