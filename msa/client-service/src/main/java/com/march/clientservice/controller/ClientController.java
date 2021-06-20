package com.march.clientservice.controller;

import com.march.clientservice.Bundle;
import com.march.clientservice.Phrase;
import com.march.clientservice.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping(path = "/march", produces = "application/json")
@CrossOrigin(origins = "*")
public class ClientController {

    private ServiceClient client;

    @Autowired
    public ClientController(ServiceClient client) {
        this.client = client;
    }

    @GetMapping("/tree/{num}")
    public Long createTree(@PathVariable Long num) {
        System.out.println("I got this: " + num);
        return client.plantTrees(num);
    }

    @GetMapping("/flower/blossom")
    public List<Tree> blossom() {
        return client.getTrees();
    }

    @GetMapping("/bundle")
    public List<Bundle> getAllBundles() {
        return client.getBundles();
    }
    @PostMapping(value = "/bundle/{title}", consumes = "application/json")
    public Bundle makeBundle(@RequestBody List<Phrase> selected, @PathVariable String title) {
        return client.postBundle(title, selected);
    }

    @PutMapping("/bundle/{id}/{title}")
    public Bundle changeTitle(@PathVariable Long id, @PathVariable String title) {
        return client.getTitle(title, id);
    }

    @DeleteMapping("/bundle/{id}")
    public Long deleteBundle(@PathVariable Long id) {
        return client.deleteBundle(id);
    }
}
