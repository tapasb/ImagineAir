package tapas.example.com.imagineair;

/**
 * Created by Tapas Behera on 22/07/2014.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.ImageReference;
import android.view.Gravity;

public class ArrivalAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;

    public ArrivalAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    static final int[][] BG_IMAGES = new int[][] {
            {
                R.drawable.imagine_air_bg,
            },
    };

    /** A simple container for static data in each page */
    private static class Page {
        String flightNumber;
        String flightDetails;
        int cardGravity = Gravity.CENTER;
        boolean expansionEnabled = false;
        float expansionFactor = 1.0f;
        int expansionDirection = CardFragment.EXPAND_DOWN;

        public Page(String flightNum, String details) {
            this.flightNumber = flightNum;
            this.flightDetails = details;
        }
    }

    private final Page[][] PAGES = {
            {
                    new Page("Dallas/Fort Worth","Local Time : 11:00"),
                    new Page("IA123","Arrived from SFO"),
                    new Page("IA343","Late 10 min"),
                    new Page("IA564","Late 20 min"),
            },
            {
                    new Page("Denver","Local Time : 10:00"),
                    new Page("IA234","Late 10 min"),
                    new Page("IA708","Late 45 min"),
                    new Page("IA987","Arrived from DFW"),
            },
            {
                    new Page("San Fransisco","Local Time : 10:00"),
                    new Page("IA983","Late 15 min"),
                    new Page("IA456","Arrived from Denver"),
                    new Page("IA657","Late 20 min"),
            },

    };

    @Override
    public Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];
        String title = page.flightNumber;
        String text = page.flightDetails;
        CardFragment fragment = CardFragment.create(title, text, R.drawable.ic_launcher);
        // Advanced settings
        fragment.setCardGravity(page.cardGravity);
        fragment.setExpansionEnabled(page.expansionEnabled);
        fragment.setExpansionDirection(page.expansionDirection);
        fragment.setExpansionFactor(page.expansionFactor);
        return fragment;
    }

    @Override
    public ImageReference getBackground(int row, int column) {
        return ImageReference.forDrawable(BG_IMAGES[0][0]);
    }

    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    @Override
    public int getColumnCount(int rowNum) {
        return PAGES[rowNum].length;
    }
}

