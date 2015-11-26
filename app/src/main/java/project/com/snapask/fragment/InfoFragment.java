package project.com.snapask.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import project.com.snapask.R;
import project.com.snapask.model.UserProfile;

/**
 * Created by Vison on 2015/11/25.
 */
public class InfoFragment extends Fragment {
    ListView mListView;
    InfoAdapter mAdapter;

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mListView = (ListView) view.findViewById(R.id.list);
        mAdapter = new InfoAdapter();
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showInputDialog(i);
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mAdapter.getContent(0) != mSharedPreferences.getString("email", "")) {
            Log.d("CHANGED", "email has changed");
            mEditor.putString("email", mAdapter.getContent(0));
        }
        if (mAdapter.getContent(1) != mSharedPreferences.getString("phone", "")) {
            Log.d("CHANGED", "phone has changed");
            mEditor.putString("phone", mAdapter.getContent(1));
        }
        if (mAdapter.getContent(2) != mSharedPreferences.getString("university", "")) {
            Log.d("CHANGED", "university has changed");
            mEditor.putString("university", mAdapter.getContent(2));
        }
        if (mAdapter.getContent(3) != mSharedPreferences.getString("subjects", "")) {
            Log.d("CHANGED", "subjects has changed");
            mEditor.putString("subjects", mAdapter.getContent(3));
        }
        mEditor.commit();

    }

    private class InfoAdapter extends BaseAdapter {
        private String[] TITLES = {"email", "phone", "university", "subjects"};
        private int[] ICONS = {R.drawable.info_mail, R.drawable.info_phone, R.drawable.info_university, R.drawable.info_subject};
        private String[] contents = new String[4];

        public InfoAdapter() {
            init();
        }

        private void init() {
            // retrieve
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            mEditor = mSharedPreferences.edit();

            contents[0] = mSharedPreferences.getString("email", "");
            contents[1] = mSharedPreferences.getString("phone", "");
            contents[2] = mSharedPreferences.getString("university", "");
            contents[3] = mSharedPreferences.getString("subjects", "");
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
            contentText.setText(contents[i]);
            return view;
        }

        public String getContent(int i) {
            return contents[i];
        }

        public String getTitle(int i) {
            return TITLES[i];
        }

        public void setContent(int i, String content) {
            contents[i] = content;
        }
    }

    private void showInputDialog(final int i) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptView = inflater.inflate(R.layout.fragment_info_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getActivity());
        alertDialogBuilder.setView(promptView);
        TextView title = (TextView) promptView.findViewById(R.id.title);
        final TextView editText = (TextView) promptView.findViewById(R.id.editText);
        title.setText("Set " + mAdapter.getTitle(i));
        editText.setText(mAdapter.getContent(i));


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mAdapter.setContent(i, editText.getText().toString());
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }
}
