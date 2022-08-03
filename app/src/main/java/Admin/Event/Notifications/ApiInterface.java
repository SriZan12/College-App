package Admin.Event.Notifications;

import static Admin.Event.Notifications.Constants.CONTENT_TYPE;
import static Admin.Event.Notifications.Constants.SERVER_KEY;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Authorization: key="+SERVER_KEY, "Content-Type:"+CONTENT_TYPE})
    @POST("fcm/send")
    Call<PushNotification> SendNotification(@Body PushNotification notification);
}
