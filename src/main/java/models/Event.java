package models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Event {

    private List<User> participants;
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY HH:mm")
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY HH:mm")
    private Date endTime;


    public Event(List<User> participants, Date startTime, Date endTime, String title){
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
