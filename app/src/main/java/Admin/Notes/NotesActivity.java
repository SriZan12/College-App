package Admin.Notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivityNotesBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class NotesActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 11;
    private static final int PDF = 101;
    ActivityNotesBinding notesBinding;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    Uri PDF_File;
    Dialog dialog;
    EditText SubjectName,SubjectTeacher,Standard,NotesTitle;
    Button Upload;
    NotesAdapter notesAdapter;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesBinding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(notesBinding.getRoot());

        Role = getIntent().getStringExtra("role");


        dialog= new Dialog(NotesActivity.this);
        dialog.getWindow().setContentView(R.layout.notes_details);
        SubjectName = dialog.findViewById(R.id.subject);
        SubjectTeacher = dialog.findViewById(R.id.Teacher);
        Standard = dialog.findViewById(R.id.Standard);
        Upload = dialog.findViewById(R.id.uploadPDF);
        NotesTitle = dialog.findViewById(R.id.NotesTitle);

        if(Role != null && Role.equals("TeacherStudent")){
            notesBinding.NotesAdd.setVisibility(View.GONE);
        }


        notesBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        notesBinding.recycler.addItemDecoration(dividerItemDecoration);

        FirebaseRecyclerOptions<NotesModel> options = new FirebaseRecyclerOptions.Builder<NotesModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().
                        child("Notes"), NotesModel.class)
                .build();

        notesAdapter = new NotesAdapter(options,Role);
        notesBinding.recycler.setAdapter(notesAdapter);


        notesBinding.NotesAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                Upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckPermission();
                    }
                });

            }
        });

        notesBinding.searchNotes.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchNote(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchNote(newText);
                return false;
            }
        });
    }

    private void searchNote(String query) {
        FirebaseRecyclerOptions<NotesModel> options = new FirebaseRecyclerOptions.Builder<NotesModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().
                        child("Notes").orderByChild("lowerCaseNotesTitle").startAt(query.toLowerCase()).endAt(query+"\uff8ff"), NotesModel.class)
                .build();

        notesAdapter = new NotesAdapter(options, Role);
        notesAdapter.startListening();
        notesBinding.recycler.setAdapter(notesAdapter);
    }

    private void CheckPermission() {
        if(ContextCompat.checkSelfPermission(NotesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            pickPDF();
        }else{
            RequestPermission();
        }
    }

    private void RequestPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(NotesActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(NotesActivity.this)
                    .setMessage("Browse the PDF")
                    .setTitle("Uploader")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(NotesActivity.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(NotesActivity.this,new
                    String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                pickPDF();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void pickPDF(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent, "Open with"), PDF);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PDF && resultCode == RESULT_OK){
            assert data != null;
            PDF_File = data.getData();
            uploadPDFToFirebase(PDF_File);
        }
    }

    private void uploadPDFToFirebase(Uri File) {

        ProgressDialog progressDialog = new ProgressDialog(NotesActivity.this);
        progressDialog.setTitle("Reliance International College");
        progressDialog.show();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference().child("AllNotesPDF")
                .child(user + ".pdf");

        storageReference.putFile(File)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String subject = SubjectName.getText().toString();
                                String Teacher = SubjectTeacher.getText().toString();
                                String standard = Standard.getText().toString();
                                String Title = NotesTitle.getText().toString();
                                String lowercaseTitle = NotesTitle.getText().toString().toLowerCase();

                                NotesModel notesModel = new NotesModel(subject,Title,lowercaseTitle,Teacher,standard,uri.toString());

                                databaseReference = FirebaseDatabase.getInstance().getReference().child("Notes");
                                databaseReference.child(databaseReference.push().getKey()).setValue(notesModel);

                                dialog.dismiss();
                                SubjectName.setText("");
                                SubjectTeacher.setText("");
                                Standard.setText("");
                                NotesTitle.setText("");
                                progressDialog.dismiss();
                            }

                        });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        float percent = (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        progressDialog.setMessage("Uploading : " + (int) percent + "%");
                    }
                });

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onStart() {
        super.onStart();
        notesAdapter.notifyDataSetChanged();
        notesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        notesAdapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}