package org.example.webservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "auth-service", url = "${edge-service.url:http://localhost:8000}")
public class AuthClient {
}
