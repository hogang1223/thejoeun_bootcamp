package com.aoslec.tablayouttest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Tab1Fragment extends Fragment {

    public Tab1Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

}