package com.march.bundleservice.controller;

import com.march.bundleservice.Bundle;
import com.march.bundleservice.Phrase;
import com.march.bundleservice.data.BundleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/")
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

    @PostMapping("/{title}")
    public Bundle makeBundle(@RequestBody List<Phrase> selected, @PathVariable String title) {
        Bundle b = new Bundle();
        System.out.println("Ok, I got selected phrases as..." + selected);
        // 받아온 phrase의 content를 연결하여 Bundle의 contents로 세팅한다.
        b.setContents(selected.stream().map(Phrase::getContent).collect(Collectors.joining(" ")));
        b.setTitle(title);
        Bundle c =  bundleRepo.save(b);
        System.out.println("Now the bundle is ... " + c);
        return c;
    }

    @GetMapping("/{id}/{title}")
    public Bundle changeTitle(@PathVariable Long id, @PathVariable String title) {
        Bundle b = bundleRepo.findById(id).get();

        b.setTitle(title);
        return bundleRepo.save(b);
    }

    @DeleteMapping("/{id}")
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
