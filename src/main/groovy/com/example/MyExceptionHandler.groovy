package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor
import jakarta.inject.Singleton

import static io.micronaut.http.HttpResponse.unprocessableEntity

@Produces
@Singleton
class MyExceptionHandler implements ExceptionHandler<WhoopsException, HttpResponse<?>>{

    private final ErrorResponseProcessor<?> errorResponseProcessor

    MyExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
        this.errorResponseProcessor = errorResponseProcessor
    }

    @Override
    HttpResponse<?> handle(HttpRequest request, WhoopsException exception) {
        ErrorContext errorContext = ErrorContext.builder(request)
                .cause(exception)
                .errorMessage(exception.getMessage())
                .build()
        errorResponseProcessor.processResponse(errorContext, unprocessableEntity())
    }
}
