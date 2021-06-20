package com.march.spa.data;

import com.march.spa.Phrase;
import org.springframework.data.repository.CrudRepository;

public interface PhraseRepo extends CrudRepository<Phrase, Long> {
}
