package p.ernest.swift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwiftApplication {

    public static void main(String[] args) {
        System.out.println(SwiftApplication.class.getClassLoader());
        SpringApplication.run(SwiftApplication.class, args);
    }

}
