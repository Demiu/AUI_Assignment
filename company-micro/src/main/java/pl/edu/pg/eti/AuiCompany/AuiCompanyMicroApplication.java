package pl.edu.pg.eti.AuiCompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuiCompanyMicroApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(AuiCompanyMicroApplication.class, args);
        //SpringApplication.exit(context); // we just run indefinitely, whatever manages us is responsible for exit
    }

}
