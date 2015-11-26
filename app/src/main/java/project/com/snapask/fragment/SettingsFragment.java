package project.com.snapask.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import project.com.snapask.R;

/**
 * Created by Vison on 2015/11/26.
 */
public class SettingsFragment extends Fragment {
    ListView mListView;
    SettingsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mListView = (ListView) view.findViewById(R.id.list);
        mAdapter = new SettingsAdapter();
        mListView.setAdapter(mAdapter);

        return view;
    }

    private class SettingsAdapter extends BaseAdapter {
        private String[] TITLES = {"Notification", "Language"};
        private String[] SUB_TITLES = {"On", "English"};

        public SettingsAdapter() {
            init();
        }

        private void init() {
            // TODO: init base user profile
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
                view = LayoutInflater.from(SettingsFragment.this.getActivity()).inflate(R.layout.fragment_item_settings, viewGroup, false);
            }
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView subTitle = (TextView) view.findViewById(R.id.subtitle);
            title.setText(TITLES[i]);
            subTitle.setText(SUB_TITLES[i]);
            // ImageView iconImage = (ImageView) view.findViewById(R.id.icon);
            // iconImage.setImageDrawable(getResources().getDrawable(ICONS[i]));

            return view;
        }
    }
}
