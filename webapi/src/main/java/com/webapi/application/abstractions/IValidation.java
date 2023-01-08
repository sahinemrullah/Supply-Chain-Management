package com.webapi.application.abstractions;

import java.util.ArrayList;
import java.util.Map;

public interface IValidation {
	public Map<String, ArrayList<String>> getValidationErrors();
	public void addError(String key, String message);
	public boolean isSucceeded();
}
