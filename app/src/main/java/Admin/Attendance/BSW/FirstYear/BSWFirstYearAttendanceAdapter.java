package Admin.Attendance.BSW.FirstYear;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.AttendanceListDesignBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Admin.Attendance.BBS.FirstYear.BBSFirstYearAttendanceAdapter;
import Admin.Courses.SubCourses.BBS.StudentModel;

public class BSWFirstYearAttendanceAdapter extends FirebaseRecyclerAdapter<StudentModel, BSWFirstYearAttendanceAdapter.BSWFirstYearAttendanceViewHolder> {
    Context context;
    DatabaseReference databaseReference;
    String Attendance,Role;

    public BSWFirstYearAttendanceAdapter(@NonNull FirebaseRecyclerOptions<StudentModel> options, Context context, String role) {
        super(options);
        this.context = context;
        this.Role = role;

    }

    @Override
    protected void onBindViewHolder(@NonNull BSWFirstYearAttendanceViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull StudentModel model) {
        holder.ListDesignBinding.setStudent(model);

        holder.ListDesignBinding.cardView.setOnClickListener(new View.OnClickListener() {
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

        try {
            databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceStatus").child("BSW").child("BSWFirstYear");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (int i = 0; i < getSnapshots().size(); i++) {
                        Attendance = String.valueOf(snapshot.child(getRef(position).getKey()).getValue());

                        try {
                            holder.ListDesignBinding.switch1.setChecked(Attendance.equals("1"));
                            holder.ListDesignBinding.notify();
                        }catch (Exception e){
                            e.getMessage();
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){
            e.getMessage();
        }

        holder.ListDesignBinding.switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.ListDesignBinding.switch1.isChecked()){
                    databaseReference.child(getRef(position).getKey()).setValue("1");

                }else{
                    databaseReference.child(getRef(position).getKey()).setValue("0");
                }
            }
        });


    }


    @NonNull
    @Override
    public BSWFirstYearAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AttendanceListDesignBinding attendanceListDesignBinding = AttendanceListDesignBinding.inflate(layoutInflater);
        return new BSWFirstYearAttendanceViewHolder(attendanceListDesignBinding,Role);
    }

    public static class BSWFirstYearAttendanceViewHolder extends RecyclerView.ViewHolder {

        AttendanceListDesignBinding ListDesignBinding;
        String Role;

        public BSWFirstYearAttendanceViewHolder(AttendanceListDesignBinding attendanceListDesignBinding, String role) {
            super(attendanceListDesignBinding.getRoot());
            this.ListDesignBinding = attendanceListDesignBinding;
            this.Role =role;

            if(Role.equals("TeacherStudent")){
                ListDesignBinding.switch1.setVisibility(View.GONE);
            }

        }
    }
}
