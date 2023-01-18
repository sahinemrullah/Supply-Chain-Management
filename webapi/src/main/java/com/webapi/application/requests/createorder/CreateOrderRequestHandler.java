
package com.webapi.application.requests.createorder;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.concretes.Result;
import com.webapi.application.exceptions.NotFoundException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateOrderRequestHandler implements IRequestHandler<CreateOrderRequest, Void> {

    @Override
    public IResult<Void> handle(CreateOrderRequest request) throws SQLException {
        IResult<Void> result = new Result<>();
        
        ISQLOperation<Set<Integer>, List<CreateOrderProductModel>> getProductsByProductIdsQuery = new GetProductsByProductIdsQuery();
        
        List<CreateOrderProductModel> products = getProductsByProductIdsQuery.execute(request.getProducts().keySet());
        
        if(products.size() == request.getProducts().keySet().size()) {
            Map<Integer, List<CreateOrderProductModel>> productsByRetailer = products.stream()
                                                                            .collect(Collectors.groupingBy(CreateOrderProductModel::getRetailerId));
            
            ISQLOperation<CreateOrderRequest, Integer> createOrderCommand = new CreateOrderCommand(productsByRetailer);
            
            createOrderCommand.execute(request);
        } else {
            List<Integer> foundProductIds = products.stream()
                    .map(CreateOrderProductModel::getId)
                    .collect(Collectors.toList());
            
            Set<Integer> productIds = request.getProducts().keySet();
            
            productIds.removeAll(foundProductIds);
            
            throw new NotFoundException("products", Arrays.toString(productIds.toArray(new Integer[0])));
        }
        
        return result;
    }
    
}
