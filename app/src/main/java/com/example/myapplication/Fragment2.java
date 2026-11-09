
package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    // Interface for communication with MainActivity
    public interface OnFragmentInteractionListener {
        void onNameSubmitted(String name);
    }

    private OnFragmentInteractionListener mListener;

    private Fragment2Binding fragment2Binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        fragment2Binding = Fragment2Binding.inflate(
                inflater, container, false);

        return fragment2Binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(
                    context.toString() +
                            " must implement OnFragmentInteractionListener");
        }
    }

    public String getEnteredName() {
        return fragment2Binding.editText.getText()
                .toString().trim();
    }

    public void submitName() {
        String name = getEnteredName();

        if (mListener != null) {
            mListener.onNameSubmitted(name);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment2Binding = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
