package hello;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hello.dto.Organization;
import hello.services.OrganizationService;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private OrganizationService organizationService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        List<Thread> threads = new ArrayList<>();
        threads.add(transaction("1", "Canada"));
        threads.add(transaction("2", "Russia"));

        threads.add(transaction("3", "USA"));
        threads.add(transaction("4", "USA"));

        threads.add(transaction("5", "Belarus"));
        threads.add(transaction("6", "Belarus"));

        for (Thread t : threads) {
            t.join();
        }

        log.info(" ----------- RESULT ORGANIZATIONS: ");
        for (Organization org : organizationService.list()) {
            log.info("    " + org);
        }
    }

    private Thread transaction(String code, String name) {
        Thread thread = new Thread(() -> {
            try {
                log.info("CREATING: [{}, {}]", code, name);
                organizationService.create(code, name);
//                log.info("DONE    : [{}, {}]", code, name);
            } catch (Exception e) {
                log.info("FAILED  : [{}, {}] - {}", code, name, e.getMessage());
            }
        });
        thread.start();
        return thread;
    }
}
