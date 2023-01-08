package com.webapi.application.concretes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.webapi.application.abstractions.IValidation;

public class Validation implements IValidation {

	private HashMap<String, ArrayList<String>> errors;
	
	public Validation() {
		errors = new HashMap<String, ArrayList<String>>();
	}

	@Override
	public Map<String, ArrayList<String>> getValidationErrors() {
		return errors;
	}

	@Override
	public void addError(String key, String message) {
		ArrayList<String> list;
		if(!errors.containsKey(key))
			errors.put(key, new ArrayList<String>());
		
		errors.get(key).add(message);
	}

	@Override
	public boolean isSucceeded() {
		return errors.isEmpty();
	}

}
