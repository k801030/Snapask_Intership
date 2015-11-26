package project.com.snapask.fragment;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import project.com.snapask.R;
import project.com.snapask.model.UserProfile;

/**
 * Created by Vison on 2015/11/25.
 */
public class InfoFragment extends Fragment {
    ListView mListView;
    InfoAdapter mAdapter;

    SharedPreferences mSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mListView = (ListView) view.findViewById(R.id.list);
        mAdapter = new InfoAdapter();
        mListView.setAdapter(mAdapter);



        return view;
    }


    private class InfoAdapter extends BaseAdapter {
        private String[] TITLES = {"email", "phone", "university", "subjects"};
        private int[] ICONS = {R.drawable.info_mail, R.drawable.info_phone, R.drawable.info_university, R.drawable.info_subject};
        private UserProfile profile;

        public InfoAdapter() {
            init();
        }

        private void init() {
            // retrieve
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            Gson gson = new Gson();
            String json = mSharedPreferences.getString("USER_PROFILE", "");
            profile = gson.fromJson(json, UserProfile.class);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(InfoFragment.this.getActivity()).inflate(R.layout.fragment_item_info, viewGroup, false);
            }

            ImageView iconImage = (ImageView) view.findViewById(R.id.icon);
            TextView contentText = (TextView) view.findViewById(R.id.content);
            TextView noteText = (TextView) view.findViewById(R.id.title);
            iconImage.setImageDrawable(getResources().getDrawable(ICONS[i]));
            noteText.setText(TITLES[i]);


            if (profile != null) {
                switch (i) {
                    case 0:
                        contentText.setText(profile.getEmail());
                        break;
                    case 1:
                        contentText.setText(profile.getPhone());
                        break;
                    case 2:
                        contentText.setText(profile.getSchoolName());
                        break;
                    case 3:
                        contentText.setText(profile.getSubjects()[0].getAbbr());
                        for (int j=1; j<profile.getSubjects().length; j++) {
                            contentText.append(", ");
                            contentText.append(profile.getSubjects()[i].getAbbr());
                        }

                }

            }
            return view;
        }
    }
}
