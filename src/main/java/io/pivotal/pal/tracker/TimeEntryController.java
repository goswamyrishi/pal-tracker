package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }
    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

         return new ResponseEntity(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody TimeEntry expected) {
        TimeEntry updated = timeEntryRepository.update(id, expected);
        if(updated != null )
            return new ResponseEntity(updated, HttpStatus.OK);
        else
            return new ResponseEntity(updated, HttpStatus.NOT_FOUND);
    }
   @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
       return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

   @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
       TimeEntry read = timeEntryRepository.find(id);
       if (read != null)
       {    return new ResponseEntity(read, HttpStatus.OK);}
       else
       {
           return new ResponseEntity(read, HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {


            timeEntryRepository.delete(id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);

    }
}
