package com.jiaflu.bookshopadmin.support;


import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class MockServer {

    public static void main(String[] args) {
        configureFor("127.0.0.1", 9080);

        stubFor(get(urlEqualTo("/book"))
                .willReturn(okJson("{'name':'tom'}")));
    }
}
