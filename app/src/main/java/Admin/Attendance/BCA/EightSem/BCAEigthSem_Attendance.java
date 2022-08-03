package Admin.Attendance.BCA.EightSem;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentBCAEigthSemAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Courses.SubCourses.BBS.StudentModel;
import Admin.Courses.SubCourses.BCA.Semesters.EigthSem.BCAEigthSemStudentAdapter;


public class BCAEigthSem_Attendance extends Fragment {

    FragmentBCAEigthSemAttendanceBinding attendanceBinding;
    EightSemAdapterAttendance StudentAdapter;
    String Role;

    public BCAEigthSem_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        attendanceBinding =  FragmentBCAEigthSemAttendanceBinding.inflate(inflater, container, false);

        attendanceBinding.EightSemAttendance.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BCA").child("Eight_Sem").child("StudentList"), StudentModel.class)
                        .build();

         StudentAdapter= new EightSemAdapterAttendance(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.EightSemAttendance.addItemDecoration(dividerItemDecoration);
        attendanceBinding.EightSemAttendance.setAdapter(StudentAdapter);
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