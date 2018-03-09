package pl.patrykheciak.gallery.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import pl.patrykheciak.gallery.Constants;
import pl.patrykheciak.gallery.ImageList;
import pl.patrykheciak.gallery.MediaSharedElementCallback;
import pl.patrykheciak.gallery.R;
import pl.patrykheciak.gallery.detail.DetailsActivity;

public class GalleryActivity extends AppCompatActivity implements MyRecyclerViewAdapter.OnItemClickListener {

    public static final int COLUMNS = 2;
    private RecyclerView recycler;
    private MyRecyclerViewAdapter adapter;
    public int[] imgIdList = ImageList.getImages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recycler = findViewById(R.id.gallery_recycler);

        adapter = new MyRecyclerViewAdapter(this, imgIdList);
        recycler.setAdapter(adapter);

        recycler.setLayoutManager(new GridLayoutManager(this, COLUMNS));
        new LinearSnapHelper().attachToRecyclerView(recycler);
    }

    @Override
    public void onItemClick(View view, int imageNumber) {

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.EXTRA_IMAGE_NUMBER, imageNumber);

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, view, "obrazek");
        ActivityCompat.startActivityForResult(this, intent, 123, options.toBundle());
    }

    public void onActivityReenter(int resultCode, Intent data) {

        final int position = DetailsActivity.getPosition(resultCode, data);

        MyRecyclerViewAdapter.ViewHolder vh =
                (MyRecyclerViewAdapter.ViewHolder) recycler.findViewHolderForAdapterPosition(position);

        if (position != RecyclerView.NO_POSITION) {
            fireTransition(position);
        }
    }

    private void fireTransition(final int position) {

        final MediaSharedElementCallback sharedElementCallback = new MediaSharedElementCallback();
        setExitSharedElementCallback(sharedElementCallback);
        supportPostponeEnterTransition();
        recycler.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recycler.getViewTreeObserver().removeOnPreDrawListener(this);
                RecyclerView.ViewHolder holder = recycler.findViewHolderForAdapterPosition(position);
                if (holder instanceof MyRecyclerViewAdapter.ViewHolder) {
                    MyRecyclerViewAdapter.ViewHolder mediaViewHolder =
                            (MyRecyclerViewAdapter.ViewHolder) holder;
                    sharedElementCallback.setSharedElementViews(mediaViewHolder.imgView);
                }
                recycler.scrollToPosition(position);
                supportStartPostponedEnterTransition();
                return true;
            }
        });
    }
}
