package com.march.phraseservice;

import com.march.phraseservice.data.PhraseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class PhraseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhraseServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(PhraseRepo phraseRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				// Load File from IO
				ClassPathResource resource = new ClassPathResource("/static/phrases.txt");

				try {
					Path path = Paths.get(resource.getURI());
					List<String> content = Files.readAllLines(path);
					content.forEach(s -> phraseRepo.save(new Phrase(s)));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
	}

}
