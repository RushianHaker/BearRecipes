package com.service.bearrecipes.config.openapi;

import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    static {
        SpringDocUtils.getConfig()
                    .replaceWithSchema(byte[].class, new Schema<byte[]>().type("string").format("base64"));
    }
}
