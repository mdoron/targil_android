package com.example.doron.frag;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SelectFragment extends Fragment{
    String[] food = {"Wheat", "Oranges", "Corn", "Grass", "Skewers"};
    MainActivity act;

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        act = (MainActivity) activity;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View inflateView = inflater.inflate(
                R.layout.fragment_select, container, false);

        super.onCreate(savedInstanceState);

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(act, food);

        ListView list = (ListView) inflateView.findViewById(R.id.listView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Button b1 = ((Button) inflater.inflate(
                        R.layout.fragment_form, container, false).findViewById(R.id.select_food));
                act.select_name = food[position];
                ((Button) b1.findViewById(R.id.select_food)).setText(act.select_name);
                System.out.println("b1 = "+b1.getText());
                if(getString(R.string.conf).equals("regular")) {
                    act.replaceTo(act.form, R.id.fragment_place);
                }
            }
        });

        return inflateView;
    }
}
