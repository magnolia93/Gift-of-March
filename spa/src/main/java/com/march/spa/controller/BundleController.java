package com.march.spa.controller;

import com.march.spa.Bundle;
import com.march.spa.Phrase;
import com.march.spa.data.BundleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/march/bundle", produces = "application/json")
@CrossOrigin(origins = "*")
public class BundleController {

    private BundleRepo bundleRepo;

    @Autowired
    public BundleController(BundleRepo bundleRepo) {
        this.bundleRepo = bundleRepo;
    }

    @GetMapping
    public List<Bundle> getBundles() {
        return (List<Bundle>) bundleRepo.findAll();
    }

    @PostMapping(path = "/{title}", consumes = "application/json")
    public Bundle makeBundle(@RequestBody List<Phrase> selected, @PathVariable String title) {
        Bundle b = new Bundle();
        // 받아온 phrase의 content를 연결하여 Bundle의 contents로 세팅한다.
        b.setContents(selected.stream().map(Phrase::getContent).collect(Collectors.joining(" ")));
        b.setTitle(title);
        return bundleRepo.save(b);
    }

    @PutMapping(path = "/{id}/{title}")
    public Bundle changeTitle(@PathVariable Long id, @PathVariable String title) {
        System.out.println("get ID: " + id +  ", get title: " + title);
        Bundle b = bundleRepo.findById(id).get();

        b.setTitle((title));
        return bundleRepo.save(b);
    }

    @DeleteMapping(path = "/{id}")
    public Long deleteBundle(@PathVariable Long id) {
        try {
            bundleRepo.deleteById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return Long.valueOf(-1);
        }

        return id;
    }
}
