package com.march.treeservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.march.treeservice.Phrase;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ServiceClient {
    private RestTemplate rest;

    public ServiceClient(@LoadBalanced RestTemplate rest) {
        this.rest = rest;
    }

    public List<Phrase> getPhraseList() {
        // restTemplate으로 List<Object>를 받아와야 할 때는 getForObejct로 받아올 수 없다.
        // (컴파일러가 generic type 정보를 적는 것을 허용하지 않음)
        // 따라서 getForEntity로 Object[]를 body로 가지는 responseEntity 객체를 받아오는 식으로 해결해야 한다.
        // ref: https://www.baeldung.com/spring-rest-template-list
        ResponseEntity<Phrase[]> response = rest.getForEntity(
                "http://phrase-service/",
                Phrase[].class);
        return Arrays.asList(response.getBody());
    }
}
