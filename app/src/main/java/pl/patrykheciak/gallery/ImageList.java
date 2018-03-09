package pl.patrykheciak.gallery;

/**
 * Created by Patryk on 02.03.2018.
 */

public class ImageList {
    private static int[] titles;

    public static int[] getImages() {
        return new int[]{
                R.mipmap.a1,
                R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.a1,
                R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.a1,
                R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.a1,
                R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.a1,
                R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.a1
        };
    }

    public static String[] getTitles() {
        return new String[]{
                "Pomaranczowa mgla",
                "Samochod",
                "Harrison Ford",
                "Pomaranczowa mgla",
                "Samochod",
                "Harrison Ford",
                "Pomaranczowa mgla",
                "Samochod",
                "Harrison Ford",
                "Pomaranczowa mgla",
                "Samochod",
                "Harrison Ford",
                "Pomaranczowa mgla",
                "Samochod",
                "Harrison Ford",
                "Pomaranczowa mgla"
        };
    }
}
