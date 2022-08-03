package Admin.Attendance.BCA.FirstSem;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBCAFirstSemAttendanceBinding;
import com.example.relianceinternationalcollege.databinding.FragmentBCAFirstSemStudentBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BCA.FifthSem.FifthSemAdapterAttendance;
import Admin.Courses.SubCourses.BBS.StudentModel;
import Admin.Courses.SubCourses.BCA.Semesters.FirstSem.BCAFirstSemStudent;


public class BCAFirstSem_Attendance extends Fragment {

    FragmentBCAFirstSemAttendanceBinding attendanceBinding;
    FirstSemAdapterAttendance StudentAdapter;
    String Role;

    public BCAFirstSem_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        attendanceBinding =  FragmentBCAFirstSemAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.FirstSemAttendance.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BCA").child("First_Sem").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new FirstSemAdapterAttendance(options,getContext(),Role);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.FirstSemAttendance.addItemDecoration(dividerItemDecoration);
        attendanceBinding.FirstSemAttendance.setAdapter(StudentAdapter);
        StudentAdapter.notifyDataSetChanged();
        return attendanceBinding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
        StudentAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        StudentAdapter.stopListening();
    }
}