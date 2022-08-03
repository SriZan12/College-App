package Admin.Attendance.BBS.FirstYear;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.AttendanceListDesignBinding;
import com.example.relianceinternationalcollege.databinding.FragmentBBSFirstYearAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Admin.Attendance.AttendanceModel;
import Admin.Attendance.BCA.EightSem.EightSemAdapterAttendance;
import Admin.Courses.SubCourses.BBS.StudentModel;

public class BBSFirstYear_Attendance extends Fragment {

    FragmentBBSFirstYearAttendanceBinding attendanceBinding;
    BBSFirstYearAttendanceAdapter StudentAdapter;
    String Role;

    public BBSFirstYear_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        attendanceBinding =  FragmentBBSFirstYearAttendanceBinding.inflate(inflater, container, false);

        attendanceBinding.bbsFirstYearRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BBS").child("First_Year").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new BBSFirstYearAttendanceAdapter(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.bbsFirstYearRecycler.addItemDecoration(dividerItemDecoration);
        attendanceBinding.bbsFirstYearRecycler.setAdapter(StudentAdapter);
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