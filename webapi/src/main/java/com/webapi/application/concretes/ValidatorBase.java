/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapi.application.concretes;

import com.webapi.application.abstractions.IValidator;

public abstract class ValidatorBase<T> implements IValidator {
    protected T model;
    
    public ValidatorBase(T model) {
        this.model = model;
    }
}
