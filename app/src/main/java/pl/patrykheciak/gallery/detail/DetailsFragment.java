package pl.patrykheciak.gallery.detail;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import pl.patrykheciak.gallery.ImageList;
import pl.patrykheciak.gallery.R;

public class DetailsFragment extends Fragment {

    private static final String ARG_PICTURE_NUMER = "ARG_PICTURE_NUMBER";

    public DetailsFragment() {}

    public static DetailsFragment newInstance(int pictureNumber) {

        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PICTURE_NUMER, pictureNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_details, container, false);

        ImageView imageView = v.findViewById(R.id.image_full);
        int index = getArguments().getInt(ARG_PICTURE_NUMER);
        imageView.setImageResource(ImageList.getImages()[index]);
        TextView textView = v.findViewById(R.id.textView);
        textView.setText(ImageList.getTitles()[index]);

        return v;
    }
}
