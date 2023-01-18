package com.webapi.services;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.requests.confirmorder.ConfirmOrderRequest;
import com.webapi.application.requests.confirmorder.ConfirmOrderRequestHandler;
import com.webapi.application.requests.createorder.CreateOrderRequest;
import com.webapi.application.requests.createorder.CreateOrderRequestHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderService {

    @POST
    public void createOrder(CreateOrderRequest request) throws SQLException {
        IRequestHandler<CreateOrderRequest, Void> createOrderRequestHandler = new CreateOrderRequestHandler();
        
        IResult<Void> result = createOrderRequestHandler.handle(request);
        
        result.throwIfNotSucceeded();
    }
    
    @Path("/{orderId}")
    @PATCH
    public void confirmOrder(@PathParam("orderId") int orderId) throws SQLException {
        ConfirmOrderRequest confirmOrderRequest = new ConfirmOrderRequest();
        confirmOrderRequest.setId(orderId);
        
        IRequestHandler<ConfirmOrderRequest, Void> confirmOrderRequestHandler = new ConfirmOrderRequestHandler();
        
        IResult<Void> result = confirmOrderRequestHandler.handle(confirmOrderRequest);
        
        result.throwIfNotSucceeded();
    }
}
