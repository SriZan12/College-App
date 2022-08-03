package Admin.Gallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivityGalleryBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


public class Gallery extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 2;
    private static final int PICK_IMAGE = 201 ;
    ActivityGalleryBinding galleryBinding;
    ArrayList<String> imagesUriArrayList;
    ArrayList<String> imagesUrL;
    StorageReference storageReference;
    GalleryModel galleryModel;
    GalleryAdapter galleryAdapter;
    String filename;
    Uri fileUri;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        galleryBinding = ActivityGalleryBinding.inflate(getLayoutInflater());
        setContentView(galleryBinding.getRoot());

        imagesUriArrayList = new ArrayList<>();
        imagesUrL = new ArrayList<>();
        storageReference = FirebaseStorage.getInstance().getReference();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        galleryBinding.galleyRecycler.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<GalleryModel> options =
                new FirebaseRecyclerOptions.Builder<GalleryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child("Captions"), GalleryModel.class)
                        .build();

        galleryAdapter = new GalleryAdapter(options,Gallery.this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);

        galleryBinding.galleyRecycler.addItemDecoration(dividerItemDecoration);
        galleryBinding.galleyRecycler.setAdapter(galleryAdapter);
        galleryAdapter.notifyDataSetChanged();

        galleryBinding.addGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Gallery.this);
                dialog.getWindow().setContentView(R.layout.addpost_gallery);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                ImageView imageView = dialog.findViewById(R.id.firstImage);
                EditText caption = dialog.findViewById(R.id.caption);
                Button postButton = dialog.findViewById(R.id.post);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckPermission();
                    }
                });

                postButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String CaptionText = caption.getText().toString();

                        galleryModel = new GalleryModel(CaptionText,imagesUrL);

                        FirebaseDatabase.getInstance().getReference()
                                .child("Captions").push()
                                .setValue(galleryModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Gallery.this, "Posted", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                    }
                });
            }
        });
    }


    private void CheckPermission() {
        if(ContextCompat.checkSelfPermission(Gallery.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            pickImage();
        }else{
            RequestPermission();
        }
    }

    private void RequestPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(Gallery.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(Gallery.this)
                    .setMessage("Browse the Image")
                    .setTitle("Uploader")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Gallery.this,
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
            ActivityCompat.requestPermissions(Gallery.this,new
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

    @SuppressLint("IntentReset")
    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }

    @SuppressLint("NotifyDataSetChanged")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ProgressDialog progressDialog = new ProgressDialog(Gallery.this);
        progressDialog.setTitle("Reliance International College");
        progressDialog.show();
        imagesUrL.clear();


        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            if(data.getClipData() != null){
                for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                    fileUri = data.getClipData().getItemAt(i).getUri();
                    filename = getFilename(fileUri);
                    imagesUriArrayList.add(filename);

                    StorageReference uploader = storageReference.child("/CollegeGallery")
                            .child(filename);

                    uploader.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Gallery.this, "Images Added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            float percent = (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploading : " + (int) percent + "%");
                        }
                    });
                    imagesUrL.add(fileUri.toString());
                }

                galleryAdapter.notifyDataSetChanged();
            }
        }
    }


    //    To get The Image file name to Store Perfectly in Firebase
    @SuppressLint("Range")
    public String getFilename(Uri filepath)
    {
        String result = null;
        if (filepath.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = filepath.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @Override
    public void onStart() {
        super.onStart();
        galleryAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        galleryAdapter.stopListening();
    }
}