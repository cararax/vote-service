package com.carara.vote.infra.client;

import com.carara.vote.infra.client.response.AgendaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "${feign.config.agenda.name}", url = "${feign.config.agenda.url}")
public interface AgendaClient {
    @GetMapping(value = "/{id}")
    ResponseEntity<AgendaResponse> checkIfAgendaExists(@PathVariable("id") Long id);
}
