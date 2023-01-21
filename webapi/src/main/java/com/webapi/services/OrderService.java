package com.webapi.services;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.requests.createorder.CreateOrderRequest;
import com.webapi.application.requests.createorder.CreateOrderRequestHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;
import com.webapi.application.filters.AuthorizeJWTToken;
import com.webapi.application.requests.orderdetails.OrderDetailsModel;
import com.webapi.application.requests.orderdetails.OrderDetailsRequest;
import com.webapi.application.requests.orderdetails.OrderDetailsRequestHandler;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@AuthorizeJWTToken
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderService {

    @Inject
    SecurityContext userContext;

    @POST
    public void createOrder(CreateOrderRequest request) throws SQLException {
        IRequestHandler<CreateOrderRequest, Void> createOrderRequestHandler = new CreateOrderRequestHandler();

        IResult<Void> result = createOrderRequestHandler.handle(request);

        result.throwIfNotSucceeded();
    }

    @Path("/{orderId}")
    @GET
    @AuthorizeJWTToken
    public Response getOrder(@PathParam("orderId") int orderId) throws SQLException {
        OrderDetailsRequest request = new OrderDetailsRequest();
        request.setId(orderId);
        request.setUserId(Integer.parseInt(userContext.getUserPrincipal().getName()));
        request.setRole(userContext.isUserInRole("retailer") ? "retailer" : "supplier");
        
        IRequestHandler<OrderDetailsRequest, OrderDetailsModel> orderDetailsRequestHandler = new OrderDetailsRequestHandler();

        IResult<OrderDetailsModel> result = orderDetailsRequestHandler.handle(request);

        result.throwIfNotSucceeded();

        return Response.ok(result.getItem(), MediaType.APPLICATION_JSON).build();
    }
}
