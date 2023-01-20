
package com.webapi.application.filters;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Role
@Priority(Priorities.AUTHORIZATION)
public class RoleFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        SecurityContext securityContext = requestContext.getSecurityContext();
        Role methodRoleAnnotatiton = resourceInfo.getResourceMethod().getAnnotation(Role.class);
        Role classRoleAnnotatiton = resourceInfo.getResourceClass().getAnnotation(Role.class);
        Role roleAnnotation = methodRoleAnnotatiton == null ? classRoleAnnotatiton : methodRoleAnnotatiton;
        if(roleAnnotation != null) {
            if(!securityContext.isUserInRole(roleAnnotation.role())) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        }
    }

}
