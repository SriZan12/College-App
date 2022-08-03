package Admin.Gallery.Slider;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.relianceinternationalcollege.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Admin.Gallery.GalleryModel;

public class ImagesSliderAdapter extends RecyclerView.Adapter<ImagesSliderAdapter.ImageViewHolder> {

    List<String> photos = new ArrayList<>();
    String Captions;

    public ImagesSliderAdapter(List<String> photos, String caption) {
        this.photos = photos;
        this.Captions = caption;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder( LayoutInflater.from(parent.getContext())
                .inflate(R.layout.allimages_layout,parent,false));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        try {
                Glide.with(holder.imageView.getContext())
                        .load(Uri.parse(photos.get(position)))
                        .into(holder.imageView);

                holder.textView.setText(Captions);

        }catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.allImages);
            textView = itemView.findViewById(R.id.capts);

        }
    }
}
