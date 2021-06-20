package com.march.phraseservice.controller;

import com.march.phraseservice.Phrase;
import com.march.phraseservice.data.PhraseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PhraseController {
    private PhraseRepo phraseRepo;

    @Autowired
    public PhraseController(PhraseRepo phraseRepo) {
        this.phraseRepo = phraseRepo;
    }

    @GetMapping
    public List<Phrase> getPhraseList() {
        return (List<Phrase>) phraseRepo.findAll();
    }
}
