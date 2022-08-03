package Admin.Event;

public class EventModel {
    private String EventTitle;
    private String EventSubTitle;
    private String EventDescription;

    public EventModel(String eventTitle, String eventSubTitle, String eventDescription) {
        EventTitle = eventTitle;
        EventSubTitle = eventSubTitle;
        EventDescription = eventDescription;
    }

    public EventModel() {
    }



    public String getEventTitle() {
        return EventTitle;
    }

    public void setEventTitle(String eventTitle) {
        EventTitle = eventTitle;
    }

    public String getEventSubTitle() {
        return EventSubTitle;
    }

    public void setEventSubTitle(String eventSubTitle) {
        EventSubTitle = eventSubTitle;
    }

    public String getEventDescription() {
        return EventDescription;
    }

    public void setEventDescription(String eventDescription) {
        EventDescription = eventDescription;
    }
}
