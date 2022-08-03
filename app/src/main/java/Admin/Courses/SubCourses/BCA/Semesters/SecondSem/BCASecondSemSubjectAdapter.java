package Admin.Courses.SubCourses.BCA.Semesters.SecondSem;

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
import com.example.relianceinternationalcollege.databinding.SubjectDetailsBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Admin.Courses.SubCourses.BBS.SubjectModel;
import Admin.Courses.SubCourses.BCA.Semesters.FirstSem.BCAFirstSemSubjectAdapter;

public class BCASecondSemSubjectAdapter extends FirebaseRecyclerAdapter<SubjectModel, BCASecondSemSubjectAdapter.BCASecondSemSubjectViewHolder> {

    String Role;

    public BCASecondSemSubjectAdapter(@NonNull FirebaseRecyclerOptions<SubjectModel> options, String role) {
        super(options);
        this.Role = role;
    }

    @Override
    protected void onBindViewHolder(@NonNull BCASecondSemSubjectViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull SubjectModel model) {
        holder.DetailsBinding.setSubject(model);

        if(!Objects.equals(Role,"TeacherStudent")) {

            holder.DetailsBinding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.getWindow().setContentView(R.layout.subject_add);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();

                    EditText SubjectName = dialog.findViewById(R.id.subjectName);
                    EditText SubjectTeacher = dialog.findViewById(R.id.subjectTeacher);
                    EditText SubjectCode = dialog.findViewById(R.id.SubjectCode);
                    Button done = dialog.findViewById(R.id.subjectDone);

                    SubjectName.setText(model.getSubjectName());
                    SubjectCode.setText(model.getSubjectCode());
                    SubjectTeacher.setText(model.getTeacher());

                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Name = SubjectName.getText().toString();
                            String Teacher = SubjectTeacher.getText().toString();
                            String code = SubjectCode.getText().toString();

                            Map<String, Object> subDetails = new HashMap<>();
                            subDetails.put("SubjectName", Name);
                            subDetails.put("Teacher", Teacher);
                            subDetails.put("SubjectCode", code);

                            FirebaseDatabase.getInstance().getReference().child("subDetails").
                                    child("BCA").child("Second_Sem").child("SubjectList")
                                    .child(getRef(position).getKey())
                                    .updateChildren(subDetails)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(v.getContext(), "Subject Updated", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(v.getContext(), "Subject Update Failed !", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });
                        }
                    });
                }
            });

            holder.DetailsBinding.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    FirebaseDatabase.getInstance().getReference("subDetails").
                            child("BCA").child("Second_Sem").child("SubjectList")
                            .child(getRef(position).getKey())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(v.getContext(), "Deletion Failed !", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    return false;
                }
            });
        }
    }

    @NonNull
    @Override
    public BCASecondSemSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SubjectDetailsBinding DetailsBinding = SubjectDetailsBinding.inflate(layoutInflater);
        return new BCASecondSemSubjectViewHolder(DetailsBinding);
    }

    public static class BCASecondSemSubjectViewHolder extends RecyclerView.ViewHolder {
        SubjectDetailsBinding DetailsBinding;
        public BCASecondSemSubjectViewHolder(SubjectDetailsBinding subjectDetailsBinding) {
            super(subjectDetailsBinding.getRoot());

            this.DetailsBinding = subjectDetailsBinding;
        }
    }
}
