package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;

import java.util.List;

public interface TimeEntryRepository {


    public TimeEntry create(TimeEntry any);

    public TimeEntry find(Long timeEntryId);

    public List<TimeEntry> list();

    public TimeEntry update(Long timeEntryId, TimeEntry expected);

    public void delete(Long timeEntryId);
}
