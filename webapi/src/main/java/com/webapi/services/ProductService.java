
package com.webapi.services;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.filters.AuthorizeJWTToken;
import com.webapi.application.filters.Role;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.requests.productdetails.ProductDetailsModel;
import com.webapi.application.requests.productdetails.ProductDetailsRequest;
import com.webapi.application.requests.productdetails.ProductDetailsRequestHandler;
import com.webapi.application.requests.productsearch.ProductSearchModel;
import com.webapi.application.requests.productsearch.ProductSearchRequest;
import com.webapi.application.requests.productsearch.ProductSearchRequestHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
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
    
    @GET
    @Path("/{productId}")
    public ProductDetailsModel getProduct(@PathParam("productId") int productId) throws SQLException {
        ProductDetailsRequest request = new ProductDetailsRequest();
        request.setId(productId);
        
        IRequestHandler<ProductDetailsRequest, ProductDetailsModel> productDetailsRequestHandler = new ProductDetailsRequestHandler();
        
        IResult<ProductDetailsModel> result = productDetailsRequestHandler.handle(request);
        
        return result.getItem();
    }
    
    
    
    @Path("/search")
    @GET
    @AuthorizeJWTToken
    @Role(role = "retailer")
    public PaginatedListModel<ProductSearchModel> searchProducts(@QueryParam("query") String query,
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
}
