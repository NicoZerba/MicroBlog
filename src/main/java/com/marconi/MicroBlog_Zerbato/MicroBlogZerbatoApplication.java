package com.marconi.MicroBlog_Zerbato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages={"com.marconi.entity"})
public class MicroBlogZerbatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroBlogZerbatoApplication.class, args);
	}

}
