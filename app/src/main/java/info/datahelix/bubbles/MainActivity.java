package info.datahelix.bubbles;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements BubbleFragment.OnBubbleButtonInteractionListener, ShopItemFragment.OnListFragmentInteractionListener {

    public static String TAG = "MainActivity";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private BubbleFragment bubbleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        bubbleFragment = BubbleFragment.newInstance(new int[]{}, new int[]{}, new int[]{}, new int[]{});
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bubbleFragment.getRandomCirclesView() != null) {
                            bubbleFragment.getRandomCirclesView().addCircles(bubbleFragment.bubblesToAdd);
                            bubbleFragment.setBubbleButtonText("Bubbles! "+ bubbleFragment.getRandomCirclesView().getNumCircles());
                        }
                    }
                });
            }
        }, Calendar.getInstance().getTime(), 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(ShopContent.ShopItem item) {
        int itemID = Integer.valueOf(item.id);
        RandomCirclesView randomCirclesView = bubbleFragment.getRandomCirclesView();
        if (itemID > randomCirclesView.getNumCircles()){
            Toast.makeText(this, "Not Enough Bubbles!", Toast.LENGTH_SHORT).show();
        }
        else {
            switch (itemID) {
                case 100:
                    randomCirclesView.removeCircles(100);
                    bubbleFragment.bubblesToAdd += 1;
                    break;
                case 1000:
                    randomCirclesView.removeCircles(1000);
                    bubbleFragment.bubblesToAdd += 10;
                    break;
                case 5000:
                    randomCirclesView.removeCircles(5000);
                    bubbleFragment.bubblesToAdd += 50;
                    break;
                case 10000:
                    randomCirclesView.removeCircles(10000);
                    bubbleFragment.bubblesToAdd += 100;
                    break;
                case 50000:
                    randomCirclesView.removeCircles(50000);
                    bubbleFragment.bubblesToAdd += 500;
                    break;
                case 100000:
                    randomCirclesView.removeCircles(100000);
                    bubbleFragment.bubblesToAdd += 1000;
                    break;
                case 500000:
                    randomCirclesView.removeCircles(500000);
                    bubbleFragment.bubblesToAdd += 5000;
                    break;
                case 1000000:
                    randomCirclesView.removeCircles(1000000);
                    bubbleFragment.bubblesToAdd += 10000;
                    break;
                case 5000000:
                    randomCirclesView.removeCircles(50000000);
                    bubbleFragment.bubblesToAdd += 50000;
                    break;
                case 10000000:
                    randomCirclesView.removeCircles(10000000);
                    bubbleFragment.bubblesToAdd += 100000;
                    break;
                default:
                    Log.e(TAG, "Unrecognized Store Item!");
            }
            bubbleFragment.setBubbleButtonText("Bubbles! " + bubbleFragment.bubblesToAdd);
        }
    }

    @Override
    public void onBubbleButtonInteraction() {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return bubbleFragment;
                case 1:
                    return ShopItemFragment.newInstance(1);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Bubbles!";
                case 1:
                    return "Shop!";
            }
            return null;
        }
    }
}
