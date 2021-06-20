package com.march.spa.controller;

import com.march.spa.Tree;
import com.march.spa.data.TreeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/march/tree", produces = "application/json")
@CrossOrigin(origins = "*")
public class TreeController {

    private TreeRepo treeRepo;

    @Autowired
    public TreeController(TreeRepo treeRepo) {
        this.treeRepo = treeRepo;
    }

    @GetMapping("/{num}")
    public Long createTree(@PathVariable Long num) {
        for (long i=0; i<num; i++) {
            treeRepo.save(new Tree());
        }

        return num;
    }
}
