package com.pro.lms;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PjLmsBackApplication {

	private static final Logger log = LoggerFactory.getLogger(PjLmsBackApplication.class);

	public static void main(String[] args) {
		dotenvLoad();
		SpringApplication.run(PjLmsBackApplication.class, args);
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("START PjLmsBackApplication");
		System.out.println(args);
		System.out.println("------------------------------------------------------------------------------------------------");
	}
	public static void dotenvLoad() {
		// dotenv를 로드, 파일이 없으면 무시하도록 설정
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing() // .env 파일이 없어도 무시
				.ignoreIfMalformed() // .env 파일 형식이 잘못되어도 무시
				.load();

		try {
			// mariaDB
			System.setProperty("MARIADB_HOST", dotenv.get("MARIADB_HOST"));
			System.setProperty("MARIADB_USER", dotenv.get("MARIADB_USER"));
			System.setProperty("MARIADB_PWD", dotenv.get("MARIADB_PWD"));

		} catch (Exception e) {
			try{
				System.setProperty("MARIADB_HOST", dotenv.get("MARIADB_HOST", System.getenv("MARIADB_HOST")));
				System.setProperty("MARIADB_USER", dotenv.get("MARIADB_USER", System.getenv("MARIADB_USER")));
				System.setProperty("MARIADB_PWD", dotenv.get("MARIADB_PWD", System.getenv("MARIADB_PWD")));

			} catch (Exception error){
				log.info("not System Variable Not found");
			}
			log.info("Env Variable Not found");
		}
	}
}
