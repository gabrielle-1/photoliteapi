package io.github.gabrielle1.photoliteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PhotoliteapiApplication {

	/*
		Testing upload image:
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired PhotoRepository repository) {
		return args -> {
			Photo photo = Photo
					.builder()
					.extension(PhotoExtension.JPG)
					.name("photo")
					.tags("teste")
					.size(1000L)
					.build();

			repository.save(photo);
		};
	}
	 */

	public static void main(String[] args) {
		SpringApplication.run(PhotoliteapiApplication.class, args);
	}

}
