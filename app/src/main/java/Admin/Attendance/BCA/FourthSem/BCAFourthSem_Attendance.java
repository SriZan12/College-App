package Admin.Attendance.BCA.FourthSem;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentBCAFourthSemAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Courses.SubCourses.BBS.StudentModel;


public class BCAFourthSem_Attendance extends Fragment {

    FragmentBCAFourthSemAttendanceBinding attendanceBinding;
    FourthSemAdapterAttendance StudentAdapter;
    String Role;

    public BCAFourthSem_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        attendanceBinding =  FragmentBCAFourthSemAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.SemAttendance.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BCA").child("Fourth_Sem").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new FourthSemAdapterAttendance(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.SemAttendance.addItemDecoration(dividerItemDecoration);
        attendanceBinding.SemAttendance.setAdapter(StudentAdapter);
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