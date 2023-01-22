package com.webapi.services;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.filters.AuthorizeJWTToken;
import com.webapi.application.filters.Role;
import com.webapi.application.models.AccessToken;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.requests.confirmorder.ConfirmOrderRequest;
import com.webapi.application.requests.confirmorder.ConfirmOrderRequestHandler;
import com.webapi.application.requests.createproduct.CreateProductRequest;
import com.webapi.application.requests.createproduct.CreateProductRequestHandler;
import com.webapi.application.requests.editdiscount.EditDiscountRequest;
import com.webapi.application.requests.editdiscount.EditDiscountRequestHandler;
import com.webapi.application.requests.editstock.EditStockRequest;
import com.webapi.application.requests.editstock.EditStockRequestHandler;
import com.webapi.application.requests.invoicedetails.InvoiceDetailsModel;
import com.webapi.application.requests.invoicedetails.InvoiceDetailsRequest;
import com.webapi.application.requests.invoicedetails.InvoiceDetailsRequestHandler;
import com.webapi.application.requests.invoicelist.InvoiceListModel;
import com.webapi.application.requests.invoicelist.InvoiceListRequest;
import com.webapi.application.requests.invoicelist.InvoiceListRequestHandler;
import com.webapi.application.requests.outofstockproducts.OutOfStockProductsRequest;
import com.webapi.application.requests.outofstockproducts.OutOfStockProductsRequestHandler;
import com.webapi.application.requests.pendingorders.PendingOrderListModel;
import com.webapi.application.requests.pendingorders.PendingOrderRequest;
import com.webapi.application.requests.pendingorders.PendingOrderRequestHandler;
import com.webapi.application.requests.supplierlogin.SupplierLoginRequest;
import com.webapi.application.requests.supplierlogin.SupplierLoginRequestHandler;
import com.webapi.application.requests.supplierproducts.SupplierProductsListModel;
import com.webapi.application.requests.supplierproducts.SupplierProductsRequest;
import com.webapi.application.requests.supplierproducts.SupplierProductsRequestHandler;
import com.webapi.application.requests.supplierregister.SupplierRegisterRequest;
import com.webapi.application.requests.supplierregister.SupplierRegisterRequestHandler;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.sql.SQLException;

@Path("/suppliers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierService {

    @Inject
    SecurityContext userContext;

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

    @Path("/{supplierId}/orders/pending")
    @GET
    @AuthorizeJWTToken
    @Role(role = "supplier")
    public Response getPendingOrder(@PathParam("supplierId") int supplierId,
            @DefaultValue("10") @QueryParam("pageSize") int pageSize,
            @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == supplierId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            PendingOrderRequest request = new PendingOrderRequest();
            request.setSupplierId(supplierId);
            request.setPageNumber(pageNumber);
            request.setPageSize(pageSize);

            IRequestHandler<PendingOrderRequest, PaginatedListModel<PendingOrderListModel>> orderHistoryRequestHandler = new PendingOrderRequestHandler();

            IResult<PaginatedListModel<PendingOrderListModel>> result = orderHistoryRequestHandler.handle(request);

            return Response.ok(result.getItem(), MediaType.APPLICATION_JSON).build();
        }
    }

    @Path("/{supplierId}/products/out-of-stock")
    @GET
    @AuthorizeJWTToken
    @Role(role = "supplier")
    public Response getOutOfStockProducts(@PathParam("supplierId") int supplierId,
            @DefaultValue("10") @QueryParam("pageSize") int pageSize,
            @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == supplierId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            OutOfStockProductsRequest request = new OutOfStockProductsRequest();
            request.setSupplierId(supplierId);
            request.setPageNumber(pageNumber);
            request.setPageSize(pageSize);

            IRequestHandler<OutOfStockProductsRequest, PaginatedListModel<SupplierProductsListModel>> requestHandler = new OutOfStockProductsRequestHandler();

            IResult<PaginatedListModel<SupplierProductsListModel>> result = requestHandler.handle(request);

            return Response.ok(result.getItem(), MediaType.APPLICATION_JSON).build();
        }
    }

    @Path("/{supplierId}/products")
    @POST
    @AuthorizeJWTToken
    @Role(role = "supplier")
    public Response createProduct(CreateProductRequest request, @PathParam("supplierId") int supplierId) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == supplierId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            IRequestHandler<CreateProductRequest, Void> createProductRequestHandler = new CreateProductRequestHandler();

            request.setSupplierId(supplierId);

            IResult<Void> result = createProductRequestHandler.handle(request);

            result.throwIfNotSucceeded();

            return Response.ok().build();
        }
    }

    @Path("/{supplierId}/products/in-stock")
    @GET
    @AuthorizeJWTToken
    @Role(role = "supplier")
    public Response getProducts(@PathParam("supplierId") int supplierId,
            @DefaultValue("1") @QueryParam("pageSize") int pageSize,
            @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == supplierId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            SupplierProductsRequest request = new SupplierProductsRequest();
            request.setSupplierId(supplierId);
            request.setPageNumber(pageNumber);
            request.setPageSize(pageSize);

            IRequestHandler<SupplierProductsRequest, PaginatedListModel<SupplierProductsListModel>> requestHandler = new SupplierProductsRequestHandler();

            IResult<PaginatedListModel<SupplierProductsListModel>> result = requestHandler.handle(request);

            return Response.ok(result.getItem(), MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Path("/{supplierId}/products/{productId}/edit-stock")
    @AuthorizeJWTToken
    @Role(role = "supplier")
    public Response editStock(@PathParam("supplierId") int supplierId,
            @PathParam("productId") int productId,
            EditStockRequest request) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == supplierId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            IRequestHandler<EditStockRequest, Void> editStockRequestHandler = new EditStockRequestHandler();

            IResult<Void> result = editStockRequestHandler.handle(request);

            result.throwIfNotSucceeded();

            return Response.ok().build();
        }
    }

    @POST
    @Path("/{supplierId}/products/{productId}/edit-discount")
    @AuthorizeJWTToken
    @Role(role = "supplier")
    public Response editDiscount(@PathParam("supplierId") int supplierId,
            @PathParam("productId") int productId,
            EditDiscountRequest request) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == supplierId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            IRequestHandler<EditDiscountRequest, Void> editDiscountRequestHandler = new EditDiscountRequestHandler();

            IResult<Void> result = editDiscountRequestHandler.handle(request);

            result.throwIfNotSucceeded();

            return Response.ok().build();
        }
    }

    @Path("/{supplierId}/orders/{orderId}")
    @POST
    @AuthorizeJWTToken
    @Role(role = "supplier")
    public Response confirmOrder(@PathParam("supplierId") int supplierId, @PathParam("orderId") int orderId) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == supplierId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            ConfirmOrderRequest confirmOrderRequest = new ConfirmOrderRequest();
            confirmOrderRequest.setId(orderId);
            confirmOrderRequest.setUserId(supplierId);

            IRequestHandler<ConfirmOrderRequest, Void> confirmOrderRequestHandler = new ConfirmOrderRequestHandler();

            IResult<Void> result = confirmOrderRequestHandler.handle(confirmOrderRequest);

            result.throwIfNotSucceeded();
            
            return Response.ok().build();
        }
    }

    @Path("/{supplierId}/invoices")
    @GET
    @AuthorizeJWTToken
    @Role(role = "supplier")
    public Response getInvoices(@PathParam("supplierId") int supplierId,
            @DefaultValue("10") @QueryParam("pageSize") int pageSize,
            @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == supplierId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            InvoiceListRequest request = new InvoiceListRequest();
            request.setUserId(supplierId);
            request.setPageNumber(pageNumber);
            request.setPageSize(pageSize);
            request.setRole("supplier");
            
            IRequestHandler<InvoiceListRequest, PaginatedListModel<InvoiceListModel>> invoiceListRequestHandler = new InvoiceListRequestHandler();

            IResult<PaginatedListModel<InvoiceListModel>> result = invoiceListRequestHandler.handle(request);

            return Response.ok(result.getItem(), MediaType.APPLICATION_JSON).build();
        }
    }

    @Path("/{supplierId}/invoices/{invoiceId}")
    @GET
    @AuthorizeJWTToken
    @Role(role = "supplier")
    public Response getInvoice(@PathParam("supplierId") int supplierId, @PathParam("invoiceId") int invoiceId) throws SQLException {
        Principal principal = userContext.getUserPrincipal();
        String userId = principal.getName();
        if (!(Integer.parseInt(userId) == supplierId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            InvoiceDetailsRequest request = new InvoiceDetailsRequest();
            request.setUserId(supplierId);
            request.setId(invoiceId);
            request.setRole("supplier");
            
            IRequestHandler<InvoiceDetailsRequest, InvoiceDetailsModel> invoiceDetailsRequestHandler = new InvoiceDetailsRequestHandler();

            IResult<InvoiceDetailsModel> result = invoiceDetailsRequestHandler.handle(request);

            return Response.ok(result.getItem(), MediaType.APPLICATION_JSON).build();
        }
    }
}
