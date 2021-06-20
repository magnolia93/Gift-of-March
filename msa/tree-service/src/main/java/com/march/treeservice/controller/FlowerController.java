package com.march.treeservice.controller;

import com.march.treeservice.Flower;
import com.march.treeservice.Phrase;
import com.march.treeservice.Tree;
import com.march.treeservice.data.FlowerRepo;
import com.march.treeservice.data.TreeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/flower")
public class FlowerController {

    private TreeRepo treeRepo;
    private FlowerRepo flowerRepo;
    private ServiceClient client;

    @Autowired
    public FlowerController(TreeRepo treeRepo, FlowerRepo flowerRepo, ServiceClient client) {
        this.treeRepo = treeRepo;
        this.flowerRepo = flowerRepo;
        this.client = client;
    }

    @GetMapping("/blossom")
    public List<Tree> blossom() {
        List<Tree> trees = (List<Tree>) treeRepo.findAll();
        List<Phrase> phrases = client.getPhraseList();
        System.out.println("phrases are ...: " + phrases);

        Random rand = new Random();

        for (Tree t: trees) {
            if (!t.getFlowerList().isEmpty()) continue;

            List<Flower> flowers = new ArrayList<>();
            for (int i=0; i<50; i++) {
                // 리팩토링 필요...
                Flower f = new Flower(phrases.get(rand.nextInt(phrases.size())).getContent());
                flowerRepo.save(f);
                flowers.add(f);
            }
            t.setFlowerList(flowers);
            treeRepo.save(t);
        }
        return trees;
    }
}
