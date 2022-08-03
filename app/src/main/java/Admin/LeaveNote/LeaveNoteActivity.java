package Admin.LeaveNote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivityLeaveNoteBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Admin.AdminActivity;


public class LeaveNoteActivity extends AppCompatActivity {

    private static final String TAG = "This";
    ActivityLeaveNoteBinding leaveNoteBinding;
    LeaveNoteAdapter leaveNoteAdapter;
    Date daty = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leaveNoteBinding = ActivityLeaveNoteBinding.inflate(getLayoutInflater());
        setContentView(leaveNoteBinding.getRoot());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        leaveNoteBinding.leaveNoteRecycler.setLayoutManager(mLayoutManager);

        FirebaseRecyclerOptions<LeaveNoteModel> options =
                new FirebaseRecyclerOptions.Builder<LeaveNoteModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("LeaveNotes"), LeaveNoteModel.class)
                        .build();

        leaveNoteAdapter = new LeaveNoteAdapter(options);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        leaveNoteBinding.leaveNoteRecycler.addItemDecoration(dividerItemDecoration);
        leaveNoteBinding.leaveNoteRecycler.setAdapter(leaveNoteAdapter);

        leaveNoteBinding.addLeaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(LeaveNoteActivity.this);
                dialog.getWindow().setContentView(R.layout.add_leavenote);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                EditText Reason = dialog.findViewById(R.id.Reason);
                EditText Student_Name = dialog.findViewById(R.id.student_Name);
                EditText Class = dialog.findViewById(R.id.Class);
                EditText contactInfo = dialog.findViewById(R.id.contactNumber);
                EditText shortDescription = dialog.findViewById(R.id.descriptionOfNote);
                Button doneButton = dialog.findViewById(R.id.noteDone);

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String reason = Reason.getText().toString();
                        String Name = Student_Name.getText().toString();
                        String StudentClass = Class.getText().toString();
                        String contactNumber = contactInfo.getText().toString();
                        String description = shortDescription.getText().toString();

                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE,yyyy-MM-dd HH:mm a");
                        Date date = new Date();

                        String leaveNoteDate = simpleDateFormat.format(date);
                        long currentDate = date.getTime();

                        LeaveNoteModel leaveNoteModel = new LeaveNoteModel(reason,Name,StudentClass,contactNumber,description,leaveNoteDate,currentDate);

                        FirebaseDatabase.getInstance().getReference().child("LeaveNotes").push().setValue(leaveNoteModel)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(LeaveNoteActivity.this, "Note Added", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LeaveNoteActivity.this, "Couldn't Add Note !", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });

                    }
                });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        leaveNoteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        leaveNoteAdapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LeaveNoteActivity.this, AdminActivity.class));
    }
}