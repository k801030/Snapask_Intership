package project.com.snapask.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.w3c.dom.Text;

import project.com.snapask.R;
import project.com.snapask.api.ApiService;
import project.com.snapask.api.GetProfileCallback;
import project.com.snapask.fragment.InfoFragment;
import project.com.snapask.fragment.PaymentFragment;
import project.com.snapask.fragment.SettingsFragment;
import project.com.snapask.model.UserProfile;

public class ProfileActivity extends AppCompatActivity {
    RoundedImageView mUserPhoto;
    TextView mUserName;

    PagerSlidingTabStrip mTabs;
    ViewPager mViewPager;
    FragmentPagerAdapter mPagerAdapter;

    ApiService mApiService;

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mApiService = new ApiService(this);
        mApiService.getUserProfile(new GetProfileCallback() {
            @Override
            public void onSuccess(UserProfile profile) {
                mUserName.setText(profile.getName());
                ImageLoader.getInstance().displayImage(profile.getPhoto(), mUserPhoto);

                // save data
                mEditor.putString("email", profile.getEmail());
                mEditor.putString("phone", profile.getPhone());
                mEditor.putString("university", profile.getSchoolName());
                String subject = profile.getSubjects()[0].getAbbr();
                for (int j=1; j<profile.getSubjects().length; j++) {
                    subject += ", ";
                    subject += profile.getSubjects()[j].getAbbr();
                }
                mEditor.putString("subjects", subject);
                mEditor.commit();
            }

            @Override
            public void onError(Exception e) {

            }
        });


        mUserPhoto = (RoundedImageView) findViewById(R.id.user_photo);
        mUserName = (TextView) findViewById(R.id.user_name);

        // Init ViewPager
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        // Init tabs
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mTabs.setViewPager(mViewPager);


    }


    private class PagerAdapter extends FragmentPagerAdapter {

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
                    return new InfoFragment();
                case 1:
                    return new PaymentFragment();
                case 2:
                    return new SettingsFragment();
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
