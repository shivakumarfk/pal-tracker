package io.pivotal.pal.tracker;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository entryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository)  {

        this.entryRepository= timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
       // var headers = new HttpHeaders();
        return new ResponseEntity(entryRepository.create((timeEntryToCreate)), HttpStatus.CREATED);
       // return ResponseEntity.created().headers(headers).body(entryRepository.create(timeEntryToCreate));

    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity read(@PathVariable  long timeEntryId) {
        //var headers = new HttpHeaders();
        return new ResponseEntity(entryRepository.find(timeEntryId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(entryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable  long timeEntryId,@RequestBody TimeEntry expected) {
        return new ResponseEntity(entryRepository.update(timeEntryId, expected), HttpStatus.OK);
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        entryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
