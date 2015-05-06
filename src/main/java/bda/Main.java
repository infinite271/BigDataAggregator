package bda;

import bda.application.Aggregator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class Main implements CommandLineRunner {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);

    @Autowired
    private Aggregator aggregator;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        switch (args.length) {
            case 1:
                log.info("Starting to Aggregate By Partner");
                aggregator.aggregateByPartner();
                break;
            case 2:
                log.info("Starting to Aggregate Of Partner");
                aggregator.aggregateOfPartner();
                break;
            default:
                log.error("You must provide at least 3 arguments");
        }
    }
}
