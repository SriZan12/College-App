package Admin.TimeTable.BCA.FirstSemTimeTable;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBCAFirstSemTimeTableBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import Admin.TimeTable.BCA.FifthSemTimeTable.BCAFifthSemTimeTableAdapter;
import Admin.TimeTable.TimeTableModel;

public class BCA_FirstSem_TimeTable extends Fragment {

    FragmentBCAFirstSemTimeTableBinding timeTableBinding;
    BCAFirstSemTimeTableAdapter timeTableAdapter;
    String Role;

    public BCA_FirstSem_TimeTable(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        timeTableBinding =  FragmentBCAFirstSemTimeTableBinding.inflate(inflater, container, false);
        timeTableBinding.bcaFirstSemTimeTable.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<TimeTableModel> options =
                new FirebaseRecyclerOptions.Builder<TimeTableModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TimeTable")
                                .child("BCATimeTable").child("First_Sem"), TimeTableModel.class)
                        .build();


        timeTableAdapter = new BCAFirstSemTimeTableAdapter(options,Role);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        timeTableBinding.bcaFirstSemTimeTable.addItemDecoration(dividerItemDecoration);
        timeTableBinding.bcaFirstSemTimeTable.setAdapter(timeTableAdapter);

        if(Role != null && Role.equals("TeacherStudent")){
            timeTableBinding.bcaAddFirstSemTimeTable.setVisibility(View.GONE);
        }

        timeTableBinding.bcaAddFirstSemTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.getWindow().setContentView(R.layout.add_timetable);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                EditText SubjectName = dialog.findViewById(R.id.TimeSubjectName);
                EditText time = dialog.findViewById(R.id.time);
                EditText period = dialog.findViewById(R.id.Period);
                EditText teacher = dialog.findViewById(R.id.teacher);
                Button doneButton = dialog.findViewById(R.id.TimeTableDone);

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String subject = SubjectName.getText().toString();
                        String Time = time.getText().toString();
                        String Period = period.getText().toString();
                        String Teacher = teacher.getText().toString();

                        TimeTableModel timeTableModel = new TimeTableModel(subject,Time,Period,Teacher);

                        FirebaseDatabase.getInstance().getReference().child("TimeTable")
                                .child("BCATimeTable").child("First_Sem").push().setValue(timeTableModel)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getContext(), "Time Table Added !", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "Table Addition Failed !", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                    }
                });


            }
        });
        return timeTableBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        timeTableAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        timeTableAdapter.stopListening();
    }
}