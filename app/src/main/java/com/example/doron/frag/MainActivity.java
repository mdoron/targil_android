package com.example.doron.frag;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.EditText;


public class MainActivity extends Activity {
    Fragment select;
    Fragment form;
    public static boolean isFinalized = false;
    public final static String EXTRA_MESSAGE = "com.example.doron.frag.DisplayMessageActivity.MESSAGE";
    public String select_name = "Select";
    private String msn;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(msn, select_name);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            select_name = savedInstanceState.getString(msn);
        } else {
            // Probably initialize members with default values for a new instance
            select_name = "Select";
        }

        if(getString(R.string.conf).equals("regular")) {
            select = new SelectFragment();
            form = new FormFragment();
            replaceTo(form, R.id.fragment_place);

            Fragment fp2 = getFragmentManager().findFragmentById(R.id.fragment_place2);
            if (fp2 != null) {
                replaceTo(select, R.id.fragment_place2);
            } else {
                System.out.println("BIG PROBLEM");
            }
        }
    }


    public void replaceTo(Fragment fr, int id) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void ordering() {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText et = (EditText) findViewById(R.id.sheeps_text);
        String message = "Your order has been sent!\nYou ordered " + et.getText().toString() + " sheeps.";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public boolean isLegal(int value, boolean flag) {
        return (value > 0 && flag);
    }
}
