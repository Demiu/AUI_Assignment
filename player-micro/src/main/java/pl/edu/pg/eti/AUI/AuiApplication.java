package pl.edu.pg.eti.AUI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuiApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(AuiApplication.class, args);
        //SpringApplication.exit(context); // we just run indefinitely, whatever manages us is responsible for exit
    }

}
