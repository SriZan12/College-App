package Admin.TimeTable.BCA.EigthSemTimeTable;

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
import com.example.relianceinternationalcollege.databinding.FragmentBCAEightSemTimeTableBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import Admin.TimeTable.BBS.ThirdYearTimeTable.BBSThirdYearTimeTableAdapter;
import Admin.TimeTable.TimeTableModel;


public class BCA_EightSem_TimeTable extends Fragment {

    FragmentBCAEightSemTimeTableBinding timeTableBinding;
    BCAEightSemTimeTableAdapter timeTableAdapter;
    String Role;

    public BCA_EightSem_TimeTable(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        timeTableBinding =  FragmentBCAEightSemTimeTableBinding.inflate(inflater, container, false);
        timeTableBinding.bcaEightSemTimeTable.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<TimeTableModel> options =
                new FirebaseRecyclerOptions.Builder<TimeTableModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TimeTable")
                                .child("BCATimeTable").child("Eight_Sem"), TimeTableModel.class)
                        .build();


        timeTableAdapter = new BCAEightSemTimeTableAdapter(options,Role);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        timeTableBinding.bcaEightSemTimeTable.addItemDecoration(dividerItemDecoration);
        timeTableBinding.bcaEightSemTimeTable.setAdapter(timeTableAdapter);

        if(Role != null && Role.equals("TeacherStudent")){
            timeTableBinding.bcaAddEightSemTimeTable.setVisibility(View.GONE);
        }

        timeTableBinding.bcaAddEightSemTimeTable.setOnClickListener(new View.OnClickListener() {
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
                                .child("BCATimeTable").child("Eight_Sem").push().setValue(timeTableModel)
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