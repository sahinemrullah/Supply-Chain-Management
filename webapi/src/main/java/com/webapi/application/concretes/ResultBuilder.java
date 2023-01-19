package com.webapi.application.concretes;

import com.webapi.application.abstractions.IRequest;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.abstractions.ISQLOperation;
import com.webapi.application.abstractions.IValidator;
import com.webapi.application.models.AccessToken;
import com.webapi.application.models.PaginatedListModel;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

public class ResultBuilder<T extends IRequest, U extends Object> {

    private final IResult<U> result;
    private final T request;

    private ResultBuilder(T request) {
        result = new Result<>();
        this.request = request;
    }

    public static <T extends IRequest, U extends Object> ResultBuilder<T, U> create(T request, Class<U> classOfU) {
        return new ResultBuilder<>(request);
    }

    public static <T extends IRequest, U extends Object> ResultBuilder<T, PaginatedListModel<U>> createPaginated(T request, Class<U> classOfU) {
        return new ResultBuilder<>(request);
    }

    public ResultBuilder withItem(ISQLOperation<T, U> operation) throws SQLException {
        U item = operation.execute(request);
        if (item != null) {
            result.setItem(item);
        }
        return this;
    }

    public ResultBuilder withItem(U obj) {
        result.setItem(obj);
        return this;
    }

    public ConditionBuilder check(boolean condition) {
        return new ConditionBuilder(this, condition);
    }

    public ConditionBuilder check(Supplier<Boolean> predicate) {
        return check(predicate.get());
    }

    public <T> ConditionBuilder check(T param, ISQLOperation<T, Boolean> operation) throws SQLException {
        return check(operation.execute(param));
    }

    public ConditionBuilder check(ISQLOperation<T, Boolean> operation) throws SQLException {
        return check(request, operation);
    }

    public IResult<U> build() {
        result.throwIfNotSucceeded();

        return result;
    }

    void addError(String key, String message) {
        result.addError(key, message);
    }

    public ResultBuilder validate(List<IValidator> validators) {
        for (IValidator validator : validators) {
            validator.validate(result);
        }
        
        return this;
    }
    
    public ResultBuilder withValidator(IValidator validator) {
        validator.validate(result);
        
        return this;
    }
}
