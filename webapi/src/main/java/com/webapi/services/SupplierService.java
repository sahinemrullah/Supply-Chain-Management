package com.webapi.services;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.models.AccessToken;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.requests.orderhistory.OrderHistoryModel;
import com.webapi.application.requests.orderhistory.OrderHistoryRequest;
import com.webapi.application.requests.orderhistory.OrderHistoryRequestHandler;
import com.webapi.application.requests.supplierlogin.SupplierLoginRequest;
import com.webapi.application.requests.supplierlogin.SupplierLoginRequestHandler;
import com.webapi.application.requests.supplierregister.SupplierRegisterRequest;
import com.webapi.application.requests.supplierregister.SupplierRegisterRequestHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/suppliers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierService {

    @Path("/register")
    @POST
    public void register(SupplierRegisterRequest request) throws SQLException {
        IRequestHandler<SupplierRegisterRequest, Void> retailerRegisterRequestHandler = new SupplierRegisterRequestHandler();
        
        IResult<Void> result = retailerRegisterRequestHandler.handle(request);
        
        result.throwIfNotSucceeded();
    }
    
    @Path("/login")
    @POST
    public AccessToken login(SupplierLoginRequest request) throws SQLException {
        IRequestHandler<SupplierLoginRequest, AccessToken> retailerLoginRequestHandler = new SupplierLoginRequestHandler();
        
        IResult<AccessToken> result = retailerLoginRequestHandler.handle(request);
        
        result.throwIfNotSucceeded();
        
        return result.getItem();
    }
    
    @Path("/{supplierId}/orders")
    public PaginatedListModel<OrderHistoryModel> getOrderHistory(@PathParam("supplierId") int supplierId,
                                                                @DefaultValue("10") @QueryParam("pageSize") int pageSize,
                                                                @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        OrderHistoryRequest request = new OrderHistoryRequest();
        request.setRetailerId(supplierId);
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);
        
        IRequestHandler<OrderHistoryRequest, PaginatedListModel<OrderHistoryModel>> orderHistoryRequestHandler = new OrderHistoryRequestHandler();
        
        IResult<PaginatedListModel<OrderHistoryModel>> result = orderHistoryRequestHandler.handle(request);
        
        return result.getItem();
    }
}
