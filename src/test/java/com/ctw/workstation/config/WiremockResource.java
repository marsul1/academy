package com.ctw.workstation.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class WiremockResource implements QuarkusTestResourceLifecycleManager {
    static WireMockServer wireMockServer ;
    static WireMock wireMock ;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().dynamicPort());
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());
        wireMock = new WireMock(wireMockServer.port());

        return Map.of("external-api.url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {

    }
}
