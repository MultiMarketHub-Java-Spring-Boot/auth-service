package com.multimarkethub.auth_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


@Bean
public WebClient userServiceWebClient(@Value("${user.service.base-url}") String userServiceBaseUrl){
    return WebClient.builder().baseUrl(userServiceBaseUrl).build();
}

}
