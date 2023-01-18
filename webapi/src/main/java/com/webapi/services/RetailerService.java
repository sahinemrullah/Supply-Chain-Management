package com.webapi.services;

import com.webapi.application.requests.retailerproducts.RetailerProductsRequest;
import com.webapi.application.requests.retailerproducts.RetailerProductsRequestHandler;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.models.AccessToken;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.requests.retailerlogin.RetailerLoginRequest;
import com.webapi.application.requests.retailerlogin.RetailerLoginRequestHandler;
import com.webapi.application.requests.retailerproducts.RetailerProductsListModel;
import com.webapi.application.requests.retailerregister.RetailerRegisterRequest;
import com.webapi.application.requests.retailerregister.RetailerRegisterRequestHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/retailers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RetailerService {

    @Path("/register")
    @POST
    public void register(RetailerRegisterRequest request) throws SQLException {
        IRequestHandler<RetailerRegisterRequest, Void> retailerRegisterRequestHandler = new RetailerRegisterRequestHandler();
        
        IResult<Void> result = retailerRegisterRequestHandler.handle(request);
        
        result.throwIfNotSucceeded();
    }
    
    @Path("/login")
    @POST
    public AccessToken login(RetailerLoginRequest request) throws SQLException {
        IRequestHandler<RetailerLoginRequest, AccessToken> retailerLoginRequestHandler = new RetailerLoginRequestHandler();
        
        IResult<AccessToken> result = retailerLoginRequestHandler.handle(request);
        
        result.throwIfNotSucceeded();
        
        return result.getItem();
    }
    @Path("/{retailerId}/products")
    public PaginatedListModel<RetailerProductsListModel> getProducts(@PathParam("retailerId") int retailerId,
                                                                @DefaultValue("10") @QueryParam("pageSize") int pageSize,
                                                                @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        RetailerProductsRequest request = new RetailerProductsRequest();
        request.setRetailerId(retailerId);
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);
        
        IRequestHandler<RetailerProductsRequest, PaginatedListModel<RetailerProductsListModel>> retailerProductsRequestHandler = new RetailerProductsRequestHandler();
        
        IResult<PaginatedListModel<RetailerProductsListModel>> result = retailerProductsRequestHandler.handle(request);
        
        return result.getItem();
    }
}
