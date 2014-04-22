package ro.dialogdata.jug;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Application {
	
	@RequestMapping("/")
	public String HelloJug(){
		return "Hello JUG!";
	}
	
	public static void main(String args[]){
		new SpringApplicationBuilder(Application.class).run(args);
	}

}
