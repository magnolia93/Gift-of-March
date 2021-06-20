package com.march.clientservice.controller;

import com.march.clientservice.Bundle;
import com.march.clientservice.Phrase;
import com.march.clientservice.Tree;
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

    public Long plantTrees(Long num) {
        Long planted = rest.getForObject("http://tree-service/tree/" + num, Long.class);
        return num;
    }

    public List<Tree> getTrees() {
        // 클래스에 타입 설정 가능한가?
        return rest.getForObject("http://tree-service/flower/blossom", List.class);
    }

    public List<Bundle> getBundles() {
        ResponseEntity<Bundle[]> response =  rest.getForEntity("http://bundle-service/", Bundle[].class);
        return Arrays.asList(response.getBody());
    }

    public Bundle postBundle(String title, List<Phrase> selected) {
        //restTemplate Post의 경우 get과 달리 리스트 그냥 보내는게 가능하다.
        // 어차피 json으로 변환시키기만 하면 되므로 type이 중요하지 않기 때문이다.
        System.out.println("Ok selected phrases are..." + selected);
        Bundle b =  rest.postForObject("http://bundle-service/" + title,
                selected, Bundle.class);

        System.out.println("The response Bundle is ... " + b);
        return b;
    }

    public Bundle getTitle(String title, Long id) {
        return rest.getForObject("http://bundle-service/"+id+"/"+title, Bundle.class);
    }

    public Long deleteBundle(Long id) {
        try {
            rest.delete("http://bundle-service/"+id);
        }
        catch(Exception e) {
            e.printStackTrace();
            return Long.valueOf(-1);
        }

        return id;
    }
}
