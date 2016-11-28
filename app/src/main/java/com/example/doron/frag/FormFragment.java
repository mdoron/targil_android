package com.example.doron.frag;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class FormFragment extends Fragment {
    MainActivity act;
    public Integer bar;
    public boolean wf;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        act = (MainActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
//        MenuInflater inflater = act.getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
//        return true;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setEnabled(act.isFinalized);
//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.order:
                act.ordering();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        //Inflate the layout for this fragment
        super.onCreate(savedInstanceState);

        final View inflateView = inflater.inflate(
                R.layout.fragment_form, container, false);

        final EditText et = (EditText) inflateView.findViewById(R.id.sheeps_text);
        final SeekBar sb = (SeekBar) inflateView.findViewById(R.id.sheeps_bar);
        final CheckBox cb = (CheckBox) inflateView.findViewById(R.id.with_food);
        final Button but = (Button) inflateView.findViewById(R.id.make_order);
        final Menu menu = (Menu) inflateView.findViewById(R.id.order);
        final Button select = (Button) inflateView.findViewById(R.id.select_food);

        but.setEnabled(false);
        try {
            if (getString(R.string.conf).equals("regular")) {
                select.setText(act.select_name);
                select.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        act.replaceTo(act.select, R.id.fragment_place);
                    }
                });
            }
        } catch (Exception e) {
        }
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                try {
                    int input = (int) Integer.parseInt(et.getText().toString()); // XML assures numbers only
                    sb.setProgress(input);
                    act.isFinalized = act.isLegal(input, cb.isChecked());
                    but.setEnabled(act.isFinalized);
                } catch (Exception e) {
                    Toast.makeText(inflateView.getContext().getApplicationContext(), "Illegal number of sheeps", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            boolean isFinger = false;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (isFinger) {
                    et.setText(new Integer(i).toString());
                    act.isFinalized = act.isLegal(i, cb.isChecked());
                    but.setEnabled(act.isFinalized);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isFinger = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isFinger = false;
            }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                act.isFinalized = act.isLegal(sb.getProgress(), b);
                but.setEnabled(act.isFinalized);
            }
        });

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.ordering();
            }
        });

        return inflateView;
    }

}
