package project.com.snapask.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.w3c.dom.Text;

import project.com.snapask.R;
import project.com.snapask.fragment.InfoFragment;

public class ProfileActivity extends AppCompatActivity {
    RoundedImageView mUserPhoto;
    TextView mUserName;

    PagerSlidingTabStrip mTabs;
    ViewPager mViewPager;
    FragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        mUserPhoto = (RoundedImageView) findViewById(R.id.user_photo);
        mUserName = (TextView) findViewById(R.id.user_name);

            // temp
        String url = "https://pixabay.com/static/uploads/photo/2012/04/26/19/43/profile-42914_640.png";
        ImageLoader.getInstance().displayImage(url, mUserPhoto);

        // Init ViewPager
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        // Init tabs
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mTabs.setViewPager(mViewPager);


    }


    private class PagerAdapter extends FragmentPagerAdapter {
        // TODO: set view setter

        private String[] TITLES = {"Info", "Payment", "Setting"};

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                case 1:
                case 2:
                    return new InfoFragment();
            }
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }
}
