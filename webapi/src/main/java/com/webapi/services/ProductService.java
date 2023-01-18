
package com.webapi.services;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.requests.createproduct.CreateProductRequest;
import com.webapi.application.requests.createproduct.CreateProductRequestHandler;
import com.webapi.application.requests.editdiscount.EditDiscountRequest;
import com.webapi.application.requests.editdiscount.EditDiscountRequestHandler;
import com.webapi.application.requests.editstock.EditStockRequest;
import com.webapi.application.requests.editstock.EditStockRequestHandler;
import com.webapi.application.requests.productdetails.ProductDetailsModel;
import com.webapi.application.requests.productdetails.ProductDetailsRequest;
import com.webapi.application.requests.productdetails.ProductDetailsRequestHandler;
import com.webapi.application.requests.productsearch.ProductSearchModel;
import com.webapi.application.requests.productsearch.ProductSearchRequest;
import com.webapi.application.requests.productsearch.ProductSearchRequestHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductService {
    @POST
    public void createProduct(CreateProductRequest request) throws SQLException {
        IRequestHandler<CreateProductRequest, Void> createProductRequestHandler = new CreateProductRequestHandler();
        
        IResult<Void> result = createProductRequestHandler.handle(request);
        
        result.throwIfNotSucceeded();
    }
    
    @GET
    @Path("/{productId}")
    public ProductDetailsModel getProduct(@PathParam("productId") int productId) throws SQLException {
        ProductDetailsRequest request = new ProductDetailsRequest();
        request.setId(productId);
        
        IRequestHandler<ProductDetailsRequest, ProductDetailsModel> productDetailsRequestHandler = new ProductDetailsRequestHandler();
        
        IResult<ProductDetailsModel> result = productDetailsRequestHandler.handle(request);
        
        return result.getItem();
    }
    
    @GET
    public PaginatedListModel<ProductSearchModel> getProducts(@QueryParam("query") String query,
                                                                @DefaultValue("10") @QueryParam("pageSize") int pageSize,
                                                                @DefaultValue("1") @QueryParam("pageNumber") int pageNumber) throws SQLException {
        ProductSearchRequest request = new ProductSearchRequest();
        request.setQuery(query);
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);
        
        IRequestHandler<ProductSearchRequest, PaginatedListModel<ProductSearchModel>> productSearchRequestHandler = new ProductSearchRequestHandler();
        
        IResult<PaginatedListModel<ProductSearchModel>> result = productSearchRequestHandler.handle(request);
        
        return result.getItem();
    }
    
    @PATCH
    @Path("/{productId}/edit-stock")
    public void editStock(@PathParam("productId") int productId, EditStockRequest request) throws SQLException {
        IRequestHandler<EditStockRequest, Void> editStockRequestHandler = new EditStockRequestHandler();
        
        IResult<Void> result = editStockRequestHandler.handle(request);
        
        result.throwIfNotSucceeded();
    }
    
    @PATCH
    @Path("/{productId}/edit-discount")
    public void editDiscount(@PathParam("productId") int productId, EditDiscountRequest request) throws SQLException {
        IRequestHandler<EditDiscountRequest, Void> editDiscountRequestHandler = new EditDiscountRequestHandler();
        
        IResult<Void> result = editDiscountRequestHandler.handle(request);
        
        result.throwIfNotSucceeded();
    }
}
