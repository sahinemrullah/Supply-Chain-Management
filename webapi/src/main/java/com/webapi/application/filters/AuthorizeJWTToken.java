
package com.webapi.application.filters;

import jakarta.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface AuthorizeJWTToken {
}
