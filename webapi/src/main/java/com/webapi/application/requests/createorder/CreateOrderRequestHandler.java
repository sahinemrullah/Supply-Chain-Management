package com.webapi.application.requests.createorder;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.abstractions.IResult;
import com.webapi.persistence.abstractions.ISQLOperation;
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
        return ResultBuilder
                .create(request, Void.class)
                .check(createOrder(request))
                    .withError(UNKNOWN_ERROR_KEY, UNKNOWN_ERROR_MESSAGE)
                .build();
    }

    private static boolean createOrder(CreateOrderRequest request) throws SQLException {
        ISQLOperation<Set<Integer>, List<CreateOrderProductModel>> getProductsByProductIdsQuery = new GetProductsByProductIdsQuery();
        Set<Integer> productIds = request.getProducts().keySet();
        List<CreateOrderProductModel> products = getProductsByProductIdsQuery.execute(productIds);

        if (products.size() != productIds.size()) {
            throw new NotFoundException(INVALID_PRODUCTS_ERROR_KEY, getInvalidProductIds(products, productIds));
        }

        Map<Integer, List<CreateOrderProductModel>> productsByRetailer = products.stream()
                .collect(Collectors.groupingBy(CreateOrderProductModel::getSupplierId));

        ISQLOperation<CreateOrderRequest, Integer> createOrderCommand = new CreateOrderCommand(productsByRetailer);

        return createOrderCommand.execute(request) != Statement.EXECUTE_FAILED;
    }

    private static String getInvalidProductIds(List<CreateOrderProductModel> products, Set<Integer> productIds) {

        List<Integer> foundProductIds = products.stream()
                .map(CreateOrderProductModel::getId)
                .collect(Collectors.toList());

        productIds.removeAll(foundProductIds);

        return Arrays.toString(productIds.toArray(new Integer[0]));
    }

}
