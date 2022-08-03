package Admin.Attendance.BSW.FirstYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWFirstYearAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BBS.FirstYear.BBSFirstYearAttendanceAdapter;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BSWFirstYear_Attendance extends Fragment {

   FragmentBSWFirstYearAttendanceBinding attendanceBinding;
   BSWFirstYearAttendanceAdapter StudentAdapter;
   String Role;

    public BSWFirstYear_Attendance(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        attendanceBinding =  FragmentBSWFirstYearAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.bswFirstYear.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BSW").child("First_Year").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new BSWFirstYearAttendanceAdapter(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.bswFirstYear.addItemDecoration(dividerItemDecoration);
        attendanceBinding.bswFirstYear.setAdapter(StudentAdapter);
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