package com.march.phraseservice.data;

import com.march.phraseservice.Phrase;
import org.springframework.data.repository.CrudRepository;

public interface PhraseRepo extends CrudRepository<Phrase, Long> {
}
