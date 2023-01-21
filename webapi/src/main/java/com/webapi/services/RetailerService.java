package com.webapi.services;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.models.AccessToken;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.requests.retailerlogin.RetailerLoginRequest;
import com.webapi.application.requests.retailerlogin.RetailerLoginRequestHandler;
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
import com.webapi.application.requests.invoicelist.InvoiceListModel;
import com.webapi.application.requests.invoicelist.InvoiceListRequest;
import com.webapi.application.requests.invoicelist.InvoiceListRequestHandler;
import com.webapi.application.requests.orderhistory.OrderHistoryModel;
import com.webapi.application.requests.orderhistory.OrderHistoryRequest;
import com.webapi.application.requests.orderhistory.OrderHistoryRequestHandler;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/retailers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RetailerService {

    @Inject
    SecurityContext userContext;

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
    
    @Path("/{retailerId}/orders")
    @GET
    @AuthorizeJWTToken
    @Role(role = "retailer")
    public PaginatedListModel<OrderHistoryModel> getOrderHistory(@PathParam("retailerId") int retailerId,
                                                                @DefaultValue("10") @QueryParam("pageSize") int pageSize,
                                                                @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        OrderHistoryRequest request = new OrderHistoryRequest();
        request.setRetailerId(retailerId);
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);
        
        IRequestHandler<OrderHistoryRequest, PaginatedListModel<OrderHistoryModel>> orderHistoryRequestHandler = new OrderHistoryRequestHandler();
        
        IResult<PaginatedListModel<OrderHistoryModel>> result = orderHistoryRequestHandler.handle(request);
        
        return result.getItem();
    }

    @Path("/{retailerId}/invoices")
    @GET
    @AuthorizeJWTToken
    @Role(role = "retailer")
    public Response getInvoices(@PathParam("retailerId") int retailerId,
            @DefaultValue("10") @QueryParam("pageSize") int pageSize,
            @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == retailerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            InvoiceListRequest request = new InvoiceListRequest();
            request.setUserId(retailerId);
            request.setPageNumber(pageNumber);
            request.setPageSize(pageSize);

            IRequestHandler<InvoiceListRequest, PaginatedListModel<InvoiceListModel>> invoiceListRequestHandler = new InvoiceListRequestHandler();

            IResult<PaginatedListModel<InvoiceListModel>> result = invoiceListRequestHandler.handle(request);

            return Response.ok(result.getItem(), MediaType.APPLICATION_JSON).build();
        }
    }
}
