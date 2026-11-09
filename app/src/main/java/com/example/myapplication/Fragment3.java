
package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    public interface OnFragmentInteractionListener {
        void onConfirmationChanged(boolean isChecked);
    }

    private OnFragmentInteractionListener mListener;

    private static final String ARG_NAME = "name";
    private String userName;

    public Fragment3() {
        // Required empty public constructor
    }

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();

        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(
                    "MainActivity must implement the interface");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            userName = getArguments().getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_3, container, false);

        TextView textViewName =
                view.findViewById(R.id.textViewName);

        CheckBox checkBoxConfirm =
                view.findViewById(R.id.checkBoxConfirm);

        Button buttonFinish =
                view.findViewById(R.id.buttonFinish);

        textViewName.setText("Welcome, " + userName);

        buttonFinish.setEnabled(false);
        buttonFinish.setText("Continue");

        checkBoxConfirm.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    if (mListener != null) {
                        mListener.onConfirmationChanged(isChecked);
                    }
                });

        return view;
    }

    public void updateButton(boolean isChecked) {
        View view = getView();

        if (view != null) {
            Button buttonFinish =
                    view.findViewById(R.id.buttonFinish);

            buttonFinish.setEnabled(isChecked);

            if (isChecked) {
                buttonFinish.setText("Finish");
            } else {
                buttonFinish.setText("Continue");
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
