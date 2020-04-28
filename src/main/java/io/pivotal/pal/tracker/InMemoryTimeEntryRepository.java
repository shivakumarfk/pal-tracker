package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Long ID = 1L;
    Map<Long, TimeEntry> projects = new HashMap<Long, TimeEntry>();


    public TimeEntry create(TimeEntry timeEntry){
        System.out.println("Time Entry: " + timeEntry.toString());
        Long id = ID++;
        TimeEntry new_time = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours() );

       /* new_time.setId(id);
        new_time.setUserId(timeEntry.getUserId());
        new_time.setHours(timeEntry.getHours());
        new_time.setDate(timeEntry.getDate());
        new_time.setProjectId(timeEntry.getProjectId());*/

        projects.put(id, new_time);
        System.out.println("new_time: "+ new_time.toString());
        return new_time;
    }

    public TimeEntry find(Long id) {
        TimeEntry project = projects.get(id);

        return project;
    }

    public List<TimeEntry> list() {
        List<TimeEntry> project_list = new ArrayList<>(projects.values());
        return project_list;
    }


    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if ( find(id)==null) return null;
      //  TimeEntry project = projects.put(id, timeEntry);

        TimeEntry  new_time = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours() );

        projects.put(id, new_time);
        return new_time;
    }

    public void delete(Long id) {
        projects.remove(id);
    }
}
