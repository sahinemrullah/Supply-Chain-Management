package com.webapi.application.filters;

import com.webapi.application.utils.EncryptionUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Provider
@AuthorizeJWTToken
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTH_PREFIX = "Bearer ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(AUTH_PREFIX)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        } else {
            String authToken = header.substring(AUTH_PREFIX.length());

            try {
                Claims claims = EncryptionUtils.decodeJWT(authToken);

                final SecurityContext currentSecurityContext = requestContext.getSecurityContext();

                requestContext.setSecurityContext(getNewSecurityContext(currentSecurityContext, claims));
            } catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }

    private static SecurityContext getNewSecurityContext(SecurityContext currentSecurityContext, Claims claims) {
        return new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {
                return () -> (String) claims.get("userId");
            }

            @Override
            public boolean isUserInRole(String role) {
                return ((String) claims.get("role")).equalsIgnoreCase(role);
            }

            @Override
            public boolean isSecure() {
                return currentSecurityContext.isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return "Bearer";
            }
        };
    }

}
