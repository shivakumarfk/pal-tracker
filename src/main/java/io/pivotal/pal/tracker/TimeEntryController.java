package io.pivotal.pal.tracker;

import org.springframework.http.HttpHeaders;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository entryRepository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry)  {

        this.entryRepository= timeEntryRepository;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
       // var headers = new HttpHeaders();
        actionCounter.increment();
        timeEntrySummary.record(entryRepository.list().size());
        return new ResponseEntity(entryRepository.create((timeEntryToCreate)), HttpStatus.CREATED);
       // return ResponseEntity.created().headers(headers).body(entryRepository.create(timeEntryToCreate));

    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity read(@PathVariable  long timeEntryId) {
        //var headers = new HttpHeaders();
        TimeEntry isEntry = entryRepository.find(timeEntryId);
        if (isEntry != null){
            actionCounter.increment();
            return new ResponseEntity(isEntry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        return new ResponseEntity(entryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable  long timeEntryId,@RequestBody TimeEntry expected) {
        TimeEntry isEntry = entryRepository.update(timeEntryId, expected);
        if (isEntry != null){
            actionCounter.increment();
            return new ResponseEntity(isEntry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        entryRepository.delete(timeEntryId);
        actionCounter.increment();
        timeEntrySummary.record(entryRepository.list().size());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
