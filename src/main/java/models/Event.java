package models;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Event {

    private List<User> participants;
    private Calendar startTime;
    private Calendar endTime;
    private String title;

    public Event(List<User> participants, Calendar startTime, Calendar endTime, String title){
        this.participants = participants;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(participants, event.participants) &&
                Objects.equals(startTime, event.startTime) &&
                Objects.equals(endTime, event.endTime) &&
                Objects.equals(title, event.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(participants, startTime, endTime, title);
    }
}
