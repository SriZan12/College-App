package Admin.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.relianceinternationalcollege.databinding.ActivityFullImageBinding;

public class FullImageActivity extends AppCompatActivity {

    ActivityFullImageBinding fullImageBinding;
    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullImageBinding = ActivityFullImageBinding.inflate(getLayoutInflater());
        setContentView(fullImageBinding.getRoot());

        String url = getIntent().getStringExtra("imageUrl");

        Glide.with(this)
                .load(Uri.parse(url))
                .into(fullImageBinding.fullImage);
    }
}