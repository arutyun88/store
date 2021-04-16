package com.store.util;

import com.store.model.dto.ErrorMessage;
import com.store.util.StatusResult;

import javax.ws.rs.core.Response;

import static com.store.util.InfoResult.*;

public class ResponseService {

    public static Response getResponse(Response response) {
        if (response.getStatus() == OK) {
            return response;
        } else if (response.getStatus() == NOT_FOUND) {
            return Response.status(NOT_FOUND).entity(
                    ErrorMessage.builder()
                            .code(NOT_FOUND)
                            .message(NOT_FOUND_MESSAGE)
                            .build())
                    .build();
        } else return Response.status(FATAL_ERROR).entity(
                ErrorMessage.builder()
                        .code(FATAL_ERROR)
                        .message(FATAL_ERROR_MESSAGE)
                        .build())
                .build();
    }

    public static Response getErrorResponse(StatusResult statusResult) {
        if (StatusResult.FAILED_DOUBLE.equals(statusResult)) {
            return Response.status(FAILED_DOUBLE).entity(
                    ErrorMessage.builder()
                            .code(FAILED_DOUBLE)
                            .message(FAILED_DOUBLE_MESSAGE)
                            .build())
                    .build();
        } else if (StatusResult.FAILED_INCOMPATIBILITY.equals(statusResult)) {
            return Response.status(FAILED_INCOMPATIBILITY).entity(
                    ErrorMessage.builder()
                            .code(FAILED_INCOMPATIBILITY)
                            .message(FAILED_INCOMPATIBILITY_MESSAGE)
                            .build())
                    .build();
        } else if (StatusResult.FAILED_EXISTS.equals(statusResult)) {
            return Response.status(FAILED_EXISTS).entity(
                    ErrorMessage.builder()
                            .code(FAILED_EXISTS)
                            .message(FAILED_EXISTS_MESSAGE)
                            .build())
                    .build();
        } else {
            return Response.status(FATAL_ERROR).entity(
                    ErrorMessage.builder()
                            .code(FATAL_ERROR)
                            .message(FATAL_ERROR_MESSAGE)
                            .build())
                    .build();
        }
    }
}
