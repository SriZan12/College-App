package Teacher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivityTeacherBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import Admin.AdminActivity;
import Admin.Attendance.Attendance;
import Admin.Courses.CoursesListActivity;
import Admin.Event.Event;
import Admin.Fees.Fees;
import Admin.Gallery.Gallery;
import Admin.LeaveNote.LeaveNoteActivity;
import Admin.Library.LibraryActivity;
import Admin.Notes.NotesActivity;
import Admin.TimeTable.TimeTableActivity;
import Student.StudentActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherActivity extends AppCompatActivity {

    private static final String TAG = "This";
    ActivityTeacherBinding teacherBinding;
    private static final int PICK_IMAGE = 100 ;
    private final int STORAGE_PERMISSION_CODE = 10;
    CircleImageView EditProfilePic;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String Count;
    Uri filepath;
    DatabaseReference UserProfileData;
    Intent intent;
    String role = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teacherBinding = ActivityTeacherBinding.inflate(getLayoutInflater());
        setContentView(teacherBinding.getRoot());

        teacherBinding.text2.setText("Teacher");
        intent = new Intent();
        role = getIntent().getStringExtra("role");
        Log.d(TAG, "onCreate: " + role);

        firebaseAuth = FirebaseAuth.getInstance();
        UserProfileData = FirebaseDatabase.getInstance().getReference().child("UserProfile");

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            if (firebaseUser.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(firebaseUser.getPhotoUrl())
                        .into(teacherBinding.TeacherProfilePic);
            }
            if (firebaseUser.getDisplayName() != null) {
                teacherBinding.name.setText(firebaseUser.getDisplayName());
            }
        }


        teacherBinding.courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this,CoursesListActivity.class);
                intent.putExtra("role",role);
                startActivity(intent);
            }
        });

        teacherBinding.notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this, NotesActivity.class);
                intent.putExtra("role","Teacher");
                startActivity(intent);
            }
        });

        teacherBinding.Event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this, Event.class);
                intent.putExtra("role",role);
                startActivity(intent);
            }
        });

        teacherBinding.fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this,Fees.class);
                intent.putExtra("role",role);
                startActivity(intent);

            }
        });

        teacherBinding.library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this, LibraryActivity.class);
                intent.putExtra("role",role);
                startActivity(intent);
            }
        });

        teacherBinding.leaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherActivity.this, LeaveNoteActivity.class));
            }
        });

        teacherBinding.timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this, TimeTableActivity.class);
                intent.putExtra("role",role);
                startActivity(intent);
            }
        });

        teacherBinding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherActivity.this, Gallery.class));
            }
        });

        teacherBinding.attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TeacherActivity.this, Attendance.class);
                intent.putExtra("role","Teacher");
                startActivity(intent);
            }
        });



        try {
            FirebaseDatabase.getInstance().getReference().child("LeaveNotes").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    long LeaveNoteCount = snapshot.getChildrenCount();
                    Count = String.valueOf(LeaveNoteCount);

                    if(Count.equals("0") || Count.isEmpty()) {
                        teacherBinding.totalLeave.setVisibility(View.GONE);
                    }else{
                        teacherBinding.totalLeave.setVisibility(View.VISIBLE);
                        teacherBinding.totalLeave.setText(Count);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){
            e.getMessage();
        }



        teacherBinding.TeacherProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(TeacherActivity.this);
                dialog.getWindow().setContentView(R.layout.edit_profile);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                EditText profileName = dialog.findViewById(R.id.profileName);
                Button doneButton = dialog.findViewById(R.id.profileDone);
                EditProfilePic = dialog.findViewById(R.id.edit_profile);

                if(firebaseUser != null){
                    if(firebaseUser.getPhotoUrl() != null) {
                        Glide.with(dialog.getContext())
                                .load(firebaseUser.getPhotoUrl())
                                .into(EditProfilePic);
                    }
                    if(firebaseUser.getDisplayName() != null){
                        profileName.setText(firebaseUser.getDisplayName());
                    }
                }
                EditProfilePic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckPermission();
                    }
                });

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teacherBinding.TeacherProfilePic.setImageURI(filepath);
                        String Name = profileName.getText().toString();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UpdateNameTOFirebase(Name,user);
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(Name)
                                .build();

                        assert user != null;
                        user.updateProfile(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                teacherBinding.name.setText(Name);
                                Toast.makeText(TeacherActivity.this, "Profile Updated !", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                dialog.dismiss();
                            }
                        });
                    }
                });

                dialog.show();
            }
        });



    }

    private void UpdateNameTOFirebase(String Name, FirebaseUser user) {

        Map<String,Object> profileMap = new HashMap<>();
        profileMap.put("ProfileName",Name);

        FirebaseDatabase.getInstance().getReference().child("ProfileName").child(firebaseAuth.getCurrentUser().getDisplayName())
                .updateChildren(profileMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(TeacherActivity.this, "Name Updated !!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void CheckPermission() {
        if(ContextCompat.checkSelfPermission(TeacherActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            pickImage();
        }else{
            RequestPermission();
        }
    }

    private void RequestPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(TeacherActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(TeacherActivity.this)
                    .setMessage("Browse the Image")
                    .setTitle("Uploader")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(TeacherActivity.this,
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
            ActivityCompat.requestPermissions(TeacherActivity.this,new
                    String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                pickImage();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode== RESULT_OK){
            assert data != null;
            filepath = data.getData();

            try{
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                EditProfilePic.setImageBitmap(bitmap);
                UploadToFirebase(bitmap);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void UploadToFirebase(Bitmap bitmap) {
        progressDialog = new ProgressDialog(TeacherActivity.this);
        progressDialog.setTitle("Reliance International College");
        progressDialog.show();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        String Uid = FirebaseAuth.getInstance().getUid();

        StorageReference reference = FirebaseStorage.getInstance().getReference()
                .child("ProfileImages")
                .child(Uid + ".Jpeg");

        reference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                getDownloadImageUrl(reference);
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent = (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                progressDialog.setMessage("Updating : " + (int) percent + "%");
            }
        });
    }

    private void getDownloadImageUrl(StorageReference reference) {

        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                setUserProfile(uri);
            }
        });

    }

    private void setUserProfile(Uri uri) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        assert user != null;
        user.updateProfile(request).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(TeacherActivity.this, "Profile Updated !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}