package com.webapi.services;

import com.webapi.application.requests.supplierproducts.SupplierProductsRequest;
import com.webapi.application.requests.supplierproducts.SupplierProductsRequestHandler;
import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.models.AccessToken;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.requests.retailerlogin.RetailerLoginRequest;
import com.webapi.application.requests.retailerlogin.RetailerLoginRequestHandler;
import com.webapi.application.requests.supplierproducts.SupplierProductsListModel;
import com.webapi.application.requests.retailerregister.RetailerRegisterRequest;
import com.webapi.application.requests.retailerregister.RetailerRegisterRequestHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;
import com.webapi.application.filters.AuthorizeJWTToken;
import com.webapi.application.filters.Role;

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
    
    @AuthorizeJWTToken
    @Role(role = "retailer")
    @Path("/{userId}/products")
    @GET
    public PaginatedListModel<SupplierProductsListModel> getProducts(@PathParam("userId") int userId,
                                                                @DefaultValue("10") @QueryParam("pageSize") int pageSize,
                                                                @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        SupplierProductsRequest request = new SupplierProductsRequest();
        request.setSupplierId(userId);
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);
        
        IRequestHandler<SupplierProductsRequest, PaginatedListModel<SupplierProductsListModel>> retailerProductsRequestHandler = new SupplierProductsRequestHandler();
        
        IResult<PaginatedListModel<SupplierProductsListModel>> result = retailerProductsRequestHandler.handle(request);
        
        return result.getItem();
    }
}
