package com.march.spa;

import com.march.spa.data.PhraseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(PhraseRepo phraseRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				phraseRepo.save(new Phrase("Magnolia"));
				phraseRepo.save(new Phrase("Ginkgo"));
				phraseRepo.save(new Phrase("Lotus"));
				phraseRepo.save(new Phrase("March"));
				phraseRepo.save(new Phrase("Happiness"));
				phraseRepo.save(new Phrase("Welcome"));
				phraseRepo.save(new Phrase("Hello"));
				phraseRepo.save(new Phrase("world"));
				phraseRepo.save(new Phrase("wonderful"));
				phraseRepo.save(new Phrase("Love"));
				phraseRepo.save(new Phrase("Hope"));
				phraseRepo.save(new Phrase("start"));
				phraseRepo.save(new Phrase("Life"));
				phraseRepo.save(new Phrase("Beauty"));
				phraseRepo.save(new Phrase("Dream"));
				phraseRepo.save(new Phrase("True"));
				phraseRepo.save(new Phrase("Guitar"));
				phraseRepo.save(new Phrase("Music"));
			}
		};
	}

}
