package com.github.ivansjr.investment_aggregators.client;

import com.github.ivansjr.investment_aggregators.client.dto.BrapiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "BrapiClient",
        url = "https://brapi.dev/api/"
)
public interface BrapiClient {

    @GetMapping(value = "quote/{stokeId}")
    BrapiResponseDTO getQuote(@RequestParam("token") String token,
                              @PathVariable("stokeId") String stokeId);
}
