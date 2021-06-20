package com.march.treeservice.controller;

import com.march.treeservice.Flower;
import com.march.treeservice.Phrase;
import com.march.treeservice.Tree;
import com.march.treeservice.data.FlowerRepo;
import com.march.treeservice.data.TreeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/tree")
public class TreeController {

    private TreeRepo treeRepo;
    private ServiceClient client;

    @Autowired
    public TreeController(TreeRepo treeRepo, FlowerRepo flowerRepo, ServiceClient client) {
        this.treeRepo = treeRepo;
        this.client = client;
    }

    @GetMapping("/{num}")
    public Long createTree(@PathVariable Long num) {
        for (long i=0; i<num; i++) {
            treeRepo.save(new Tree());
        }
        return num;
    }


}
