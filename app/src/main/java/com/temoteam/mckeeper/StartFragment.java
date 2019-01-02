package com.temoteam.mckeeper;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    private int hours;

    private int money11;
    private int money26;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Button addShift = getView().findViewById(R.id.addShift);
        addShift.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity) getActivity()).onNavigationItemSelected(2);
                    }
                }
        );

        Date date = new Date();
        String nowMonth = date.toString().split(" ")[1];

        hours = ((MainActivity) getActivity()).getAllHours(nowMonth);
        money11 = ((MainActivity) getActivity()).getAllMoney(nowMonth, 11);
        money26 = ((MainActivity) getActivity()).getAllMoney(nowMonth, 26);


        TextView textHours = getView().findViewById(R.id.textHours);
        TextView textMoneyAv = getView().findViewById(R.id.textMoneyAv);
        TextView textMoneyZp = getView().findViewById(R.id.textMoneyZp);
        int resID = getResources().getIdentifier(nowMonth, "integer", getActivity().getPackageName());
        //getActivity().getResources(R.integer.Jan);

        textHours.setText("В " + nowMonth + " вы отработали:" + hours / 60 + " часов и " + hours % 60 + " минут. До переработок еще " + (getResources().getInteger(resID) - hours / 60) + "часов.");
        textMoneyAv.setText("В аванс придет: " + money26);
        textMoneyZp.setText("В зарплату придет: " + money11);
    }


}