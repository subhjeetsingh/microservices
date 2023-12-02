package com.subh.user.service.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager manager;


    @Override
    public void apply(RequestTemplate requestTemplate) {

        // "my-internal-custom-client" we declared in yml file, "internal" we declared in OKTA Scope or claim
        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-custom-client")
                .principal("internal").build()).getAccessToken().getTokenValue();

        requestTemplate.header("Authorization", "Bearer "+token);
    }
}
