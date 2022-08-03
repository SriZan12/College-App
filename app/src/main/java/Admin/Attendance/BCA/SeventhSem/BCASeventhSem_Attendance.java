package Admin.Attendance.BCA.SeventhSem;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBCASeventhSemAttendanceBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Admin.Attendance.BCA.SecondSem.SecondSemAdapterAttendance;
import Admin.Courses.SubCourses.BBS.StudentModel;


public class BCASeventhSem_Attendance extends Fragment {

    FragmentBCASeventhSemAttendanceBinding attendanceBinding;
    SeventhSemAdapterAttendance StudentAdapter;
    String Role;

    public BCASeventhSem_Attendance(String role) {
        this.Role = role;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        attendanceBinding =  FragmentBCASeventhSemAttendanceBinding.inflate(inflater, container, false);
        attendanceBinding.SeventhSemAttendance.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails").
                                child("BCA").child("Seventh_Sem").child("StudentList"), StudentModel.class)
                        .build();

        StudentAdapter= new SeventhSemAdapterAttendance(options,getContext(),Role);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        attendanceBinding.SeventhSemAttendance.addItemDecoration(dividerItemDecoration);
        attendanceBinding.SeventhSemAttendance.setAdapter(StudentAdapter);
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