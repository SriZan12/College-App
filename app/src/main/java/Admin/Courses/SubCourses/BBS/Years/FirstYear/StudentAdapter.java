package Admin.Courses.SubCourses.BBS.Years.FirstYear;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.StudentDetailsListBinding;
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

import Admin.Courses.SubCourses.BBS.StudentModel;

public class StudentAdapter extends FirebaseRecyclerAdapter<StudentModel, StudentAdapter.StudentListViewHolder> {

    String Role,Sex;

    public StudentAdapter(@NonNull FirebaseRecyclerOptions<StudentModel> options, String role) {
        super(options);
        this.Role = role;
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentListViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull StudentModel model) {
        holder.detailsListBinding.setStudent(model);

        if(!Objects.equals(Role,"TeacherStudent")) {


            holder.detailsListBinding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.getWindow().setContentView(R.layout.show_student_details);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();

                    TextView StudentName = dialog.findViewById(R.id.student_details_name);
                    TextView StudentCourse = dialog.findViewById(R.id.student_details_course);
                    TextView StudentAge = dialog.findViewById(R.id.student_details_Age);
                    TextView StudentAddress = dialog.findViewById(R.id.student_details_Address);
                    TextView StudentNumber = dialog.findViewById(R.id.student_details_Number);
                    TextView StudentSex = dialog.findViewById(R.id.student_details_Sex);

                    StudentName.setText(model.getStudentName());
                    StudentCourse.setText(model.getStudentCourse());
                    StudentAge.setText(model.getStudentAge());
                    StudentAddress.setText(model.getStudentAddress());
                    StudentSex.setText(model.getStudentSex());
                    StudentNumber.setText(model.getStudentNumber());

                }
            });

            holder.detailsListBinding.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.getWindow().setContentView(R.layout.student_list);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();

                    String sex;
                    EditText StudentName = dialog.findViewById(R.id.studentName);
                    EditText StudentCourse = dialog.findViewById(R.id.studentCourse);
                    EditText StudentAge = dialog.findViewById(R.id.studentAge);
                    EditText StudentAddress = dialog.findViewById(R.id.student_Address);
                    EditText StudentRollNo = dialog.findViewById(R.id.studentRollNo);
                    EditText StudentNumber = dialog.findViewById(R.id.studentNumber);
                    Button doneButton = dialog.findViewById(R.id.done);


                    StudentName.setText(model.getStudentName());
                    StudentCourse.setText(model.getStudentCourse());
                    StudentAge.setText(model.getStudentAge());
                    StudentAddress.setText(model.getStudentAddress());
                    StudentNumber.setText(model.getStudentNumber());

                    Spinner spinner = dialog.findViewById(R.id.studentSex);
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(), R.array.sex, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinner.setAdapter(adapter);


                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Sex = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    doneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Name = StudentName.getText().toString();
                            String Course = StudentCourse.getText().toString();
                            String Age = StudentAge.getText().toString();
                            String Address = StudentAddress.getText().toString();
                            String Number = StudentNumber.getText().toString();
                            String RollNo = StudentRollNo.getText().toString();

                            Map<String, Object> StudentDetails = new HashMap<>();
                            StudentDetails.put("StudentName", Name);
                            StudentDetails.put("StudentCourse", Course);
                            StudentDetails.put("StudentAge", Age);
                            StudentDetails.put("StudentSex", Sex);
                            StudentDetails.put("StudentAddress", Address);
                            StudentDetails.put("StudentRollNo", RollNo);
                            StudentDetails.put("StudentNumber", Number);

                            FirebaseDatabase.getInstance().getReference("StudentDetails").child("BBS")
                                    .child("First_Year")
                                    .child("StudentList")
                                    .child(getRef(position).getKey())
                                    .updateChildren(StudentDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(v.getContext(), "Student Updated !", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(v.getContext(), "Student Update Failed !", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });
                        }
                    });
                    return false;
                }
            });

            holder.detailsListBinding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase.getInstance().getReference("StudentDetails")
                            .child("BBS")
                            .child("First_Year")
                            .child("StudentList")
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

                }
            });
        }

    }

    @NonNull
    @Override
    public StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        StudentDetailsListBinding listBinding = StudentDetailsListBinding.inflate(layoutInflater);
        return new StudentListViewHolder(listBinding,Role);
    }

    public static class StudentListViewHolder extends RecyclerView.ViewHolder {
        StudentDetailsListBinding detailsListBinding;
        String Role;
        public StudentListViewHolder(StudentDetailsListBinding studentDetailsListBinding, String role) {
            super(studentDetailsListBinding.getRoot());
            this.Role = role;

            this.detailsListBinding = studentDetailsListBinding;

            if(Role.equals("TeacherStudent")){
                detailsListBinding.delete.setVisibility(View.GONE);
            }
        }
    }
}
