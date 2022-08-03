package Admin.Notes;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ReadNotesBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NotesAdapter extends FirebaseRecyclerAdapter<NotesModel, NotesAdapter.AllNotesViewHolder> {

    String Role;

    public NotesAdapter(@NonNull FirebaseRecyclerOptions<NotesModel> options, String role) {
        super(options);
        this.Role = role;
    }

    @Override
    protected void onBindViewHolder(@NonNull AllNotesViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull NotesModel model) {
        holder.readNotesBinding.setNotes(model);

        holder.readNotesBinding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getPdfUrl()));
                intent.setDataAndType(Uri.parse(model.getPdfUrl()), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.readNotesBinding.cardView.getContext().startActivity(Intent.createChooser(intent,"open the file"));
            }
        });

        if(!Objects.equals(Role,"TeacherStudent")) {

            holder.readNotesBinding.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    EditText SubjectName, SubjectTeacher, Standard, NotesTitle;
                    Button Upload;
                    Dialog dialog = new Dialog(v.getContext());
                    dialog.getWindow().setContentView(R.layout.notes_details);
                    SubjectName = dialog.findViewById(R.id.subject);
                    SubjectTeacher = dialog.findViewById(R.id.Teacher);
                    Standard = dialog.findViewById(R.id.Standard);
                    Upload = dialog.findViewById(R.id.uploadPDF);
                    NotesTitle = dialog.findViewById(R.id.NotesTitle);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();

                    SubjectName.setText(model.getSubjectName());
                    SubjectTeacher.setText(model.getSubjectTeacher());
                    NotesTitle.setText(model.getNotesTitle());
                    Standard.setText(model.getStandard());

                    Upload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String subject = SubjectName.getText().toString();
                            String Teacher = SubjectTeacher.getText().toString();
                            String standard = Standard.getText().toString();
                            String Title = NotesTitle.getText().toString();
                            String lowercaseTitle = NotesTitle.getText().toString().toLowerCase();

                            Map<String, Object> notesMap = new HashMap<>();
                            notesMap.put("subjectName", subject);
                            notesMap.put("notesTitle", Title);
                            notesMap.put("subjectTeacher", Teacher);
                            notesMap.put("standard", standard);
                            notesMap.put("lowerCaseNotesTitle", lowercaseTitle);


                            FirebaseDatabase.getInstance().getReference().child("Notes")
                                    .child(getRef(position).getKey())
                                    .updateChildren(notesMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(v.getContext(), "Notes Updated !", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(v.getContext(), "Update Failed !", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });
                        }
                    });

                    return false;
                }
            });

            holder.readNotesBinding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase.getInstance().getReference().child("Notes")
                            .child(getRef(position).getKey()).removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(v.getContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(v.getContext(), "Deletion Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }
    }


    @NonNull
    @Override
    public AllNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ReadNotesBinding notesBinding = ReadNotesBinding.inflate(inflater);
        return new AllNotesViewHolder(notesBinding,Role);
    }


    public static class AllNotesViewHolder extends RecyclerView.ViewHolder{

        ReadNotesBinding readNotesBinding;
        String Role;

        public AllNotesViewHolder(ReadNotesBinding binding, String role) {
            super(binding.getRoot());
            this.readNotesBinding = binding;
            this.Role = role;

            if(Role.equals("TeacherStudent")) {
                readNotesBinding.delete.setVisibility(View.GONE);
            }
        }
    }


}
