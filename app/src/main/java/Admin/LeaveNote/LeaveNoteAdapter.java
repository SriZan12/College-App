package Admin.LeaveNote;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ShowLeavenoteBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LeaveNoteAdapter extends FirebaseRecyclerAdapter<LeaveNoteModel, LeaveNoteAdapter.LeaveNoteViewHolder> {


    public LeaveNoteAdapter(@NonNull FirebaseRecyclerOptions<LeaveNoteModel> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull LeaveNoteViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull LeaveNoteModel model) {
        holder.leaveNoteBinding.setLeaveNote(model);

        long date = model.getCurrentDate();

        if(DateUtils.isToday(date)){
            holder.leaveNoteBinding.todayDate.setText("Today : " + model.getDate());
        }else{
            holder.leaveNoteBinding.todayDate.setText("Previous Day : " + model.getDate());
        }

        holder.leaveNoteBinding.leaveNoteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.getWindow().setContentView(R.layout.leavenote_description);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                TextView shortDescription = dialog.findViewById(R.id.descriptionOfNote);
                shortDescription.setText(model.getShortDescription());
            }
        });

        holder.leaveNoteBinding.deleteLeaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("LeaveNotes")
                        .child(getRef(position).getKey()).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(v.getContext(), "Couldn't Delete", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }

    @NonNull
    @Override
    public LeaveNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ShowLeavenoteBinding showLeavenoteBinding = ShowLeavenoteBinding.inflate(inflater);
        return new LeaveNoteViewHolder(showLeavenoteBinding);
    }

    public static class LeaveNoteViewHolder extends RecyclerView.ViewHolder{
        final ShowLeavenoteBinding leaveNoteBinding;
        public LeaveNoteViewHolder(ShowLeavenoteBinding showLeavenoteBinding) {
            super(showLeavenoteBinding.getRoot());
            this.leaveNoteBinding = showLeavenoteBinding;
        }
    }

}
