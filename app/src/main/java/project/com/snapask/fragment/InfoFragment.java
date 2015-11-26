package project.com.snapask.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import project.com.snapask.R;

/**
 * Created by Vison on 2015/11/25.
 */
public class InfoFragment extends Fragment {
    ListView mListView;
    InfoAdapter mAdapter;

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

        public InfoAdapter() {
            init();
        }

        private void init() {
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


            // add fake data
            switch (i) {
                case 0:
                    contentText.setText("kk801030@gmail.com");
                    break;
                case 1:
                    contentText.setText("0910 405 753");
                    break;
                case 2:
                    contentText.setText("NTU");
                    break;
                case 3:
                    contentText.setText("Math, Phy");
            }

            return view;
        }
    }
}
