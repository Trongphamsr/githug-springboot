package vn.com.fpt.clt.SpringBootApiDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import vn.com.fpt.clt.CollectLevel2ScreenApplication;

@SpringBootApplication
public class SpringBootApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiDemoApplication.class, args);
		
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootApiDemoApplication.class);
	}

}

