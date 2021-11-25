package pl.edu.pg.eti.AuiPlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuiPlayerMicroApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(AuiPlayerMicroApplication.class, args);
        //SpringApplication.exit(context); // we just run indefinitely, whatever manages us is responsible for exit
    }

}
