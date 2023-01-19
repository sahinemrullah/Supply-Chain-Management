package com.webapi.application.requests.createorder;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.concretes.ResultBuilder;
import com.webapi.application.exceptions.NotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateOrderRequestHandler implements IRequestHandler<CreateOrderRequest, Void> {
    
    private static final String INVALID_PRODUCTS_ERROR_KEY = "products";
    private static final String UNKNOWN_ERROR_KEY = "";
    private static final String UNKNOWN_ERROR_MESSAGE = "Bilinmeyen bir nedenden dolayı siparişiniz oluşturulamadı lütfen yeniden deneyiniz.";
    
    @Override
    public IResult<Void> handle(CreateOrderRequest request) throws SQLException {

        ISQLOperation<Set<Integer>, List<CreateOrderProductModel>> getProductsByProductIdsQuery = new GetProductsByProductIdsQuery();
        Set<Integer> productIds = request.getProducts().keySet();
        List<CreateOrderProductModel> products = getProductsByProductIdsQuery.execute(productIds);

        return ResultBuilder
                .create(request, Void.class)
                .check(products.size() == productIds.size())
                    .withException(new NotFoundException(INVALID_PRODUCTS_ERROR_KEY, getInvalidProductIds(products, productIds)))
                .check(createOrder(products, request))
                    .withError(UNKNOWN_ERROR_KEY, UNKNOWN_ERROR_MESSAGE)
                .build();
    }

    private static String getInvalidProductIds(List<CreateOrderProductModel> products, Set<Integer> productIds) {

        List<Integer> foundProductIds = products.stream()
                .map(CreateOrderProductModel::getId)
                .collect(Collectors.toList());

        productIds.removeAll(foundProductIds);

        return Arrays.toString(productIds.toArray(new Integer[0]));
    }

    private static boolean createOrder(List<CreateOrderProductModel> products, CreateOrderRequest request) throws SQLException {
        Map<Integer, List<CreateOrderProductModel>> productsByRetailer = products.stream()
                .collect(Collectors.groupingBy(CreateOrderProductModel::getRetailerId));

        ISQLOperation<CreateOrderRequest, Integer> createOrderCommand = new CreateOrderCommand(productsByRetailer);

        return createOrderCommand.execute(request) != Statement.EXECUTE_FAILED;
    }

}
