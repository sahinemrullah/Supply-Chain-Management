package com.webapi.domain.abstractions;

public abstract class User extends BaseEntity {
	public String name;
	public String phoneNumber;
	public String email;
	public String passwordHash;
}
