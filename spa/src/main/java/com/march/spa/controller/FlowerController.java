package com.march.spa.controller;

import com.march.spa.Flower;
import com.march.spa.Phrase;
import com.march.spa.Tree;
import com.march.spa.data.FlowerRepo;
import com.march.spa.data.PhraseRepo;
import com.march.spa.data.TreeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/march/flower", produces = "application/json")
@CrossOrigin (origins = "*")
public class FlowerController {
    private FlowerRepo flowerRepo;
    private TreeRepo treeRepo;
    private PhraseRepo phraseRepo;

    @Autowired
    public FlowerController(FlowerRepo flowerRepo, TreeRepo treeRepo, PhraseRepo phraseRepo) {
        this.flowerRepo = flowerRepo;
        this.treeRepo = treeRepo;
        this.phraseRepo = phraseRepo;
    }

    // 나무 별로 꽃 50개 저장 후 반환
    @GetMapping("/blossom")
    public List<Tree> blossom() {
        List<Tree> trees = (List<Tree>) treeRepo.findAll();
        List<Phrase> phrases = (List<Phrase>) phraseRepo.findAll();

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
