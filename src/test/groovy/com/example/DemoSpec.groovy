package com.example

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import jakarta.inject.Inject

@MicronautTest
class DemoSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    void 'test it works'() {
        expect:
        client.toBlocking().exchange("").status().code == 422
    }

}
