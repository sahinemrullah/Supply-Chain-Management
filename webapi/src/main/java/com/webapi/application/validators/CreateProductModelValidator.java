/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapi.application.validators;

import com.webapi.application.abstractions.IValidation;
import com.webapi.application.concretes.Validation;
import com.webapi.application.concretes.ValidatorBase;
import com.webapi.application.models.product.CreateProductModel;
import com.webapi.application.utils.StringUtils;

public class CreateProductModelValidator extends ValidatorBase<CreateProductModel> {

    public CreateProductModelValidator(CreateProductModel model) {
        super(model);
    }

    @Override
    public IValidation validate() {
        IValidation validation = new Validation();
        
        if(StringUtils.isEmptyOrWhiteSpace(model.getName())) {
            validation.addError("name", "Ürün adı boş olamaz.");
        }
        
        if(StringUtils.isEmptyOrWhiteSpace(model.getDescription())) {
            validation.addError("description", "Ürün açıklaması boş olamaz.");
        }
        
        if(model.getPrice() <= 0) {
            validation.addError("price", "Ürün fiyatı 0'dan büyük olmalıdır.");
        }
        
        if(model.getStock() < 0) {
            validation.addError("stock", "Ürün stoğu 0'dan küçük olamaz.");
        }
        
        return validation;
    }
    
}
