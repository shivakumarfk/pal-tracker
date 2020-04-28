package io.pivotal.pal.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.pivotal.pal.tracker.TimeEntryRepository;

@SpringBootApplication
public class PalTrackerApplication {

    private TimeEntryRepository timeEntryRepository;

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    public TimeEntryRepository timeEntryRepository () {
        return new InMemoryTimeEntryRepository();
    }

 //   @Bean
   // public TimeEntryRepository getTimeEntry(){
     //   return timeEntryRepository;
    //}


}
