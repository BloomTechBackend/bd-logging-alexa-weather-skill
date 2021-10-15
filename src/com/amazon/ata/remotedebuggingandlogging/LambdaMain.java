package com.amazon.ata.remotedebuggingandlogging;

// import com.amazon.coral.metrics.MetricsFactory;
import com.amazon.coral.*; // Frank
//import com.amazon.coral.metrics.NullMetricsFactory;
//import com.amazon.coral.service.lambda.ApiGatewayRequest;
//import com.amazon.coral.service.lambda.ApiGatewayResponse;
//import com.amazon.coral.service.lambda.LambdaEndpoint;
//import com.amazon.coral.service.lambda.LambdaEndpointConfig;
//import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.*;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.*;
//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.*;

import static com.amazon.coral.*; // service.lambda.*; // LambdaEndpointConfig.*; // .Protocol.rest;
import static com.amazon.coral.*; // service.lambda.*; // LambdaEndpointConfig.*; // SerializationFormat.AWS_JSON_11;

public class LambdaMain implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {
    private static final Logger LOG = LogManager.getLogger(LambdaMain.class);

    private LambdaEndpoint endpoint;

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest apiGatewayRequest, Context context) {
        LOG.info("Entered request path");
        if (endpoint == null) {
            // Check HowToUseCoralOnLambda for publishing Metrics to CloudWatch:
            // https://code.amazon.com/packages/HowToUseCoralOnLambda
            endpoint = new LambdaEndpoint(getConfig(new NullMetricsFactory()));
            LOG.info("Endpoint constructed");
        }

        return endpoint.handleRequest(apiGatewayRequest, context);
    }

    private static LambdaEndpointConfig getConfig(MetricsFactory metricsFactory) {
        return LambdaEndpointConfig.builder()
                .protocol(rest(AWS_JSON_11))
                .metricsFactory(metricsFactory)
                .build();
    }
}
