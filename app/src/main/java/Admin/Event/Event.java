package Admin.Event;

import static Admin.Event.Notifications.Constants.TOPIC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivityEventBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

import Admin.Event.Notifications.ApiUtilities;
import Admin.Event.Notifications.NotificationData;
import Admin.Event.Notifications.PushNotification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Event extends AppCompatActivity {
    ActivityEventBinding eventBinding;
    EventListAdapter eventListAdapter;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBinding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(eventBinding.getRoot());

        Role = getIntent().getStringExtra("role");

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);

        if(Role != null && Role.equals("TeacherStudent")){
            eventBinding.AddEvent.setVisibility(View.GONE);
        }

        eventBinding.AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Event.this);
                dialog.getWindow().setContentView(R.layout.event_dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                EditText eventTitle = dialog.findViewById(R.id.eventTitle);
                EditText eventSubTitle = dialog.findViewById(R.id.evenSubTitle);
                EditText eventDescription = dialog.findViewById(R.id.eventDescription);

                Button EventDone = dialog.findViewById(R.id.EventDone);

                EventDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Title = eventTitle.getText().toString();
                        String SubTitle = eventSubTitle.getText().toString();
                        String description = eventDescription.getText().toString();

                        Map<String,Object> eventMap = new HashMap<>();
                        eventMap.put("EventTitle",Title);
                        eventMap.put("EventSubTitle",SubTitle);
                        eventMap.put("EventDescription",description);


                        FirebaseDatabase.getInstance().getReference().child("Event")
                                .push()
                                .setValue(eventMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            dialog.dismiss();
                                            PushNotification pushNotification;
                                            pushNotification = new PushNotification(new EventModel(Title,SubTitle,description),TOPIC);
                                            sendNotification(pushNotification);

                                        }else{
                                            Toast.makeText(Event.this, "Event Failed !", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }
                                });
                    }
                });

                dialog.show();
            }
        });

        eventBinding.eventRecycler.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        eventBinding.eventRecycler.addItemDecoration(dividerItemDecoration);


        FirebaseRecyclerOptions<EventModel> options =
                new FirebaseRecyclerOptions.Builder<EventModel>().
                        setQuery(FirebaseDatabase.getInstance().getReference().child("Event"), EventModel.class)
                        .build();

        eventListAdapter = new EventListAdapter(options,Role);
        eventBinding.eventRecycler.setAdapter(eventListAdapter);
    }

    private void sendNotification(PushNotification pushNotification) {
        ApiUtilities.getApiInterface().SendNotification(pushNotification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Event.this, "Event added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Event.this, "Event Failed !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {
                Toast.makeText(Event.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        eventListAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventListAdapter.stopListening();
    }
}