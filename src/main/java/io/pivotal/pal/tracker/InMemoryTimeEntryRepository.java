package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Long counter=1L;
    HashMap<Long,TimeEntry> repository = new HashMap<>();


    public TimeEntry create(TimeEntry entry) {

        if(0L == entry.getId()) {
            entry.setId(counter);
            repository.put(counter,entry);
            counter++;
            /*System.out.println("No ID : Counter value is "+counter);
            System.out.println("No ID : Adding  "+entry.getProjectId() + " To the repository");*/
        } else {
            repository.put(entry.getId(),entry);
            /*System.out.println("Counter value is "+counter);
            System.out.println("Adding  "+entry.getProjectId() + " To the repository");*/
        }

        return entry;
    }

    public TimeEntry find(long id) {
       return repository.get(id);
    }

    public List list() {
        List<TimeEntry> timeEntryList = repository.values().stream()
                .collect(Collectors.toList());
        return timeEntryList;
    }

    public TimeEntry update(long id, TimeEntry entry) {
        if (find(id) == null) return null;

        entry.setId(id);
        repository.replace(id,entry);

        return entry;
    }

    public void delete(long id) {
        if (!(find(id) == null)) repository.remove(id);
    }


}
