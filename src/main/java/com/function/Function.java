package com.function;

import com.domain.Authentication;
import com.domain.GetQueueMapping;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.ExecutionContext;

import java.util.List;
import java.util.Optional;

public class Function {

    private final GetQueueMapping getQueueMapping;
    private final Authentication authentication;

    public Function() {
        this.getQueueMapping = new GetQueueMapping();
        this.authentication = new Authentication();
    }

    @FunctionName("getQueueMappings")
    public HttpResponseMessage getQueueMappings(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
            ExecutionContext context) {

        String authHeader = request.getHeaders().get("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return request.createResponseBuilder(HttpStatus.UNAUTHORIZED).body("Unauthorized access").build();
        }

        String[] credentials = extractCredentials(authHeader);
        if (credentials == null || !authentication.authenticate(credentials[0], credentials[1])) {
            return request.createResponseBuilder(HttpStatus.UNAUTHORIZED).body("Invalid credentials").build();
        }

        Optional<String> publisherIdOpt = Optional.ofNullable(request.getQueryParameters().get("publisherId"));
        if (!publisherIdOpt.isPresent()) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Missing publisherId parameter").build();
        }

        long publisherId = Long.parseLong(publisherIdOpt.get());
        List<QueueMappingResponse> mappings = getQueueMapping.getMappingsByPublisherId(publisherId);
        return request.createResponseBuilder(HttpStatus.OK).body(mappings).build();
    }

    private String[] extractCredentials(String authHeader) {
        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
        final String[] values = credentials.split(":", 2);
        return values.length == 2 ? values : null;
    }
}
