package com.pebrisulistiyo.app.service.order.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

@ConfigurationProperties(prefix = "book")
public record ClientProperties(
        @NotNull
        URI catalogServiceUri
) {}
