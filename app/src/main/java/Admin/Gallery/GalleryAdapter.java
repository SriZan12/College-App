package Admin.Gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.relianceinternationalcollege.databinding.GallerypostDesignBinding;
import com.example.relianceinternationalcollege.databinding.ImagesliderViewPagerBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.transition.Hold;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;

import Admin.Gallery.Slider.ImagesSliderAdapter;

public class GalleryAdapter extends FirebaseRecyclerAdapter<GalleryModel, GalleryAdapter.GalleryViewHolder> {

    private static final String TAG = "This";
    Context context;
    List<String> photos = new ArrayList<String>();
    ImagesSliderAdapter sliderAdapter;
    DatabaseReference databaseReference;
    String caption;
    private Handler sliderHandler = new Handler();


    public GalleryAdapter(@NonNull FirebaseRecyclerOptions<GalleryModel> options, Context thisContext) {
        super(options);
        this.context = thisContext;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ImagesliderViewPagerBinding imagesliderViewPagerBinding = ImagesliderViewPagerBinding.inflate(inflater, parent,false);
        return new GalleryViewHolder(imagesliderViewPagerBinding);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onBindViewHolder(@NonNull GalleryViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull GalleryModel model) {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Captions").child(getRef(position).getKey());

        try {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    photos = new ArrayList<>();

//                    Runnable for autoSlide
                    Runnable sliderAutoSwipe = new Runnable() {
                        @Override
                        public void run() {
                            holder.imagesliderViewPagerBinding.imageSliderViewPager.
                                    setCurrentItem(holder.imagesliderViewPagerBinding.
                                            imageSliderViewPager.getCurrentItem() + 1);
                        }
                    };

                    DataSnapshot textsnap = snapshot.child("captions");
                    caption = textsnap.getValue().toString();
                        DataSnapshot imageSnaps = snapshot.child("imagesUrl");
                        for(DataSnapshot images : imageSnaps.getChildren()){
                            photos.add(Objects.requireNonNull(images.getValue()).toString());

                            sliderAdapter = new ImagesSliderAdapter(photos,caption);
                            holder.imagesliderViewPagerBinding.imageSliderViewPager.setAdapter(sliderAdapter);

                            holder.imagesliderViewPagerBinding.imageSliderViewPager.setClipToPadding(false);
                            holder.imagesliderViewPagerBinding.imageSliderViewPager.setClipChildren(false);
                            holder.imagesliderViewPagerBinding.imageSliderViewPager.setOffscreenPageLimit(3);
                            holder.imagesliderViewPagerBinding.imageSliderViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

                            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                            compositePageTransformer.addTransformer(new MarginPageTransformer(40));
                            compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                                @Override
                                public void transformPage(@NonNull View page, float position) {
                                    float r = 1 - Math.abs(position);
                                    page.setScaleY(0.85f + r * 0.15f);
                                }
                            });

                            holder.imagesliderViewPagerBinding.imageSliderViewPager.
                                    setPageTransformer(compositePageTransformer);

                            holder.imagesliderViewPagerBinding.imageSliderViewPager.
                                    registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                                @Override
                                public void onPageSelected(int position) {
                                    super.onPageSelected(position);
                                    sliderHandler.removeCallbacks(sliderAutoSwipe);
                                    sliderHandler.postDelayed(sliderAutoSwipe,3000);
                                }
                            });

                            sliderAdapter.notifyDataSetChanged();
                        }


                    Log.d(TAG, "onDataChange: " + caption);

                        Log.d(TAG, "onDataChange: " + Arrays.toString(photos.toArray()));
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }catch (Exception e){
            e.getMessage();
        }

    }



    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImagesliderViewPagerBinding imagesliderViewPagerBinding;
        public GalleryViewHolder(ImagesliderViewPagerBinding imagesliderViewPagerBinding) {
            super(imagesliderViewPagerBinding.getRoot());
            this.imagesliderViewPagerBinding = imagesliderViewPagerBinding;
        }
    }
}
