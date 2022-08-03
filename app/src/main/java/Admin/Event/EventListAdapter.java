package Admin.Event;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.EventListDesignBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EventListAdapter extends FirebaseRecyclerAdapter<EventModel, EventListAdapter.EventListViewHolder> {

    String Role;

    public EventListAdapter(@NonNull FirebaseRecyclerOptions<EventModel> options, String role) {
        super(options);
        this.Role = role;
    }

    @Override
    protected void onBindViewHolder(@NonNull EventListViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull EventModel model) {
        holder.designBinding.setEvent(model);
        holder.designBinding.title.setSelected(true);
        holder.designBinding.subTitle.setSelected(true);

        if(!Objects.equals(Role,"TeacherStudent")) {

            holder.designBinding.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.getWindow().setContentView(R.layout.event_dialog);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    EditText eventTitle = dialog.findViewById(R.id.eventTitle);
                    EditText eventSubTitle = dialog.findViewById(R.id.evenSubTitle);
                    EditText eventDescription = dialog.findViewById(R.id.eventDescription);

                    Button addEvent = dialog.findViewById(R.id.EventDone);

                    eventTitle.setText(model.getEventTitle());
                    eventSubTitle.setText(model.getEventSubTitle());
                    eventDescription.setText(model.getEventDescription());

                    addEvent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String title = eventTitle.getText().toString();
                            String subTitle = eventSubTitle.getText().toString();
                            String desc = eventDescription.getText().toString();

                            Map<String, Object> eventEditMap = new HashMap<>();
                            eventEditMap.put("EventTitle", title);
                            eventEditMap.put("EventSubTitle", subTitle);
                            eventEditMap.put("EventDescription", desc);

                            FirebaseDatabase.getInstance().getReference().child("Event")
                                    .child(getRef(position).getKey())
                                    .updateChildren(eventEditMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(v.getContext(), "Event Updated", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            } else {
                                                Toast.makeText(v.getContext(), "Event Failed !", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }
                                        }
                                    });
                        }
                    });
                    dialog.show();
                    return false;
                }
            });

            holder.designBinding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase.getInstance().getReference().child("Event")
                            .child(getRef(position).getKey())
                            .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(v.getContext(), "Event Deleted", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(v.getContext(), "Deletion Failed !", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
        }
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EventListDesignBinding eventListDesignBinding = EventListDesignBinding.inflate(inflater,parent,false);

        return new EventListViewHolder(eventListDesignBinding,Role);
    }

    public static class EventListViewHolder extends RecyclerView.ViewHolder{
        
        EventListDesignBinding designBinding;
        String Role;
        
        public EventListViewHolder(@NonNull EventListDesignBinding eventListDesignBinding, String role) {
            super(eventListDesignBinding.getRoot());
            this.designBinding = eventListDesignBinding;
            this.Role = role;

            if(Role.equals("TeacherStudent")){
                designBinding.delete.setVisibility(View.GONE);
            }
        }
    }
}
