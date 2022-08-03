package Admin.Courses;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivityCoursesList2Binding;
import com.example.relianceinternationalcollege.databinding.CourselistDesignBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CourseListAdapter extends FirebaseRecyclerAdapter<CourseModel, CourseListAdapter.CourseListViewHolder> {

    onClickListener clickListener;
    public CourseListAdapter(@NonNull FirebaseRecyclerOptions<CourseModel> options,onClickListener clickListener) {
        super(options);
        this.clickListener = clickListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull CourseListViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull CourseModel model) {
        holder.courseListDesignBinding.setCourse(model);
        holder.courseListDesignBinding.year.setSelected(true);
        holder.courseListDesignBinding.college.setSelected(true);

        holder.courseListDesignBinding.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                dialog.getWindow().setContentView(R.layout.activity_edit);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                EditText courseName = dialog.findViewById(R.id.EditCourseName);
                EditText EditYear = dialog.findViewById(R.id.EditYear);
                Button doneButton = dialog.findViewById(R.id.EditCourseDone);

                courseName.setText(model.getCourseName());
                EditYear.setText(model.getYear());

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            String CourseName = courseName.getText().toString();
                            String EditedYear = EditYear.getText().toString();

                            Map<String, Object> map = new HashMap<>();
                            map.put("CourseName", CourseName);
                            map.put("Year", EditedYear);

                        FirebaseDatabase.getInstance().getReference().child("Courses")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(v.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(v.getContext(), "Update Failed !", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });
                return false;
            }
        });
        
        holder.courseListDesignBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference()
                        .child("Courses")
                        .child(getRef(position).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(v.getContext(), "Deletion Failed !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        holder.courseListDesignBinding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });

    }

    @NonNull
    @Override
    public CourseListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CourselistDesignBinding courselistDesignBinding = CourselistDesignBinding.inflate(layoutInflater,parent,false);
        return new CourseListViewHolder(courselistDesignBinding);
    }



    public static class CourseListViewHolder extends RecyclerView.ViewHolder {
        CourselistDesignBinding courseListDesignBinding;
        public CourseListViewHolder(CourselistDesignBinding CourseListBinding) {
            super(CourseListBinding.getRoot());
            this.courseListDesignBinding = CourseListBinding;
        }
    }
}
