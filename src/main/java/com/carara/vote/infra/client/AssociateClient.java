package com.carara.vote.infra.client;

import com.carara.vote.infra.client.response.AssociateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "associate", url = "http://localhost:8080/associates")
@FeignClient(value = "${feign.config.associates.name}", url = "${feign.config.associates.url}")
public interface AssociateClient {

    @GetMapping(value = "/{id}")
    ResponseEntity<AssociateResponse> checkIfAssociateExists(@PathVariable("id") Long id);
;

}
