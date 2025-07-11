package com.multimarkethub.auth_service.client;

import com.multimarkethub.auth_service.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserClient {

    private static final Logger log = LoggerFactory.getLogger(UserClient.class);

    private final WebClient userServiceWebClient;

    public UserClient(WebClient userServiceWebClient) {
        this.userServiceWebClient = userServiceWebClient;
    }




    public LoginResponse getUserDeatilsByEmial(String email){
        log.info(email);
        return userServiceWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/userLogin")
                        .queryParam("email", email)
                        .build())
                .retrieve()
                .bodyToMono(LoginResponse.class)
                .block();
    }

}
