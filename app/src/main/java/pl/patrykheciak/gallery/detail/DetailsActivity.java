package pl.patrykheciak.gallery.detail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import pl.patrykheciak.gallery.Constants;
import pl.patrykheciak.gallery.ImageList;
import pl.patrykheciak.gallery.MediaSharedElementCallback;
import pl.patrykheciak.gallery.R;

import static pl.patrykheciak.gallery.Constants.EXTRA_RETURNING_POSITION;

public class DetailsActivity extends AppCompatActivity {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    MediaSharedElementCallback sharedElementCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        viewPager = findViewById(R.id.container);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        int imgNumber = getIntent().getIntExtra(Constants.EXTRA_IMAGE_NUMBER, -1);
        viewPager.setCurrentItem(imgNumber);

        sharedElementCallback = new MediaSharedElementCallback();
        setEnterSharedElementCallback(sharedElementCallback);
        setExitSharedElementCallback(sharedElementCallback);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DetailsFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return ImageList.getImages().length;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            sharedElementCallback.setSharedElementViews(container);
        }
    }


    @Override
    public void finishAfterTransition() {
        setResult();
        super.finishAfterTransition();
    }

    private void setResult() {
        int position = viewPager.getCurrentItem();
        Intent data = new Intent();
        data.putExtra(EXTRA_RETURNING_POSITION, position);
        setResult(RESULT_OK, data);
    }

    public static int getPosition(int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            return data.getIntExtra(EXTRA_RETURNING_POSITION, 0);
        else
            return RecyclerView.NO_POSITION;
    }
}
