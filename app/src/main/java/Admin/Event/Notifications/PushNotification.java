package Admin.Event.Notifications;

import Admin.Event.EventModel;

public class PushNotification {
   private EventModel data;
   private String to;

    public PushNotification(EventModel data, String to) {
        this.data = data;
        this.to = to;
    }

    public EventModel getData() {
        return data;
    }

    public void setData(EventModel data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
