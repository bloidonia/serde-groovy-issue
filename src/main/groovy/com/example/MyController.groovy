package com.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class MyController {

    @Get
    String index() {
        throw new WhoopsException()
    }
}
