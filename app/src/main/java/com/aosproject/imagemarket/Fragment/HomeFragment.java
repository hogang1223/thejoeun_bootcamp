package com.aosproject.imagemarket.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aosproject.imagemarket.Activity.ImageAddFormatActivity;
import com.aosproject.imagemarket.Activity.ImageAddNameActivity;
import com.aosproject.imagemarket.Activity.ImageDetailActivity;
import com.aosproject.imagemarket.Adapter.ImageAdapterhj;
import com.aosproject.imagemarket.Bean.Imagehj;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImagehj;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    String urlAddr = null;
    FloatingActionButton fabUp, fabPlus = null;
    RecyclerView recyclerView = null;
    RecyclerView.LayoutManager layoutManager = null;
    ArrayList<Imagehj> images = null;
    ImageAdapterhj adapter = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        connectGetDate();
    }

    private void connectGetDate(){
        try {

            fabUp = getView().findViewById(R.id.main_fab_up);
            fabPlus = getView().findViewById(R.id.main_fab_plus);
            recyclerView = getView().findViewById(R.id.main_recyclerView);

            layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            fabUp.setColorFilter(0xff845EC2);
            fabPlus.setColorFilter(0xff845EC2);

            fabPlus.setOnClickListener(onClickListener);
            setFloatingActionButton(recyclerView);

//            fabUp.setOnClickListener(onClickListener);
//            fabPlus.setOnClickListener(onClickListener);

            urlAddr = ShareVar.macIP + "jsp/mainImageSelect.jsp";
            Log.v("Message", urlAddr);

            NetworkTaskImagehj networkTask = new NetworkTaskImagehj(getActivity(), urlAddr, "mainSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<Imagehj>) obj;

            //adapter = new ImageAdapterhj(getActivity(), R.layout.main_custom_layout, images);
            adapter = new ImageAdapterhj(getActivity(), this.images);
            
            getData();
            
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new ImageAdapterhj.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int pos) {
                    Intent intent = null;
                    intent = new Intent(getActivity(), ImageDetailActivity.class);
                    intent.putExtra("code", images.get(pos).getCode());
                    intent.putExtra("filePath", images.get(pos).getFilepath());
                    startActivity(intent);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getData() {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ImageDetailActivity.class);
            startActivity(intent);
        }
    };

    public void setFloatingActionButton(final View view) {
        fabUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "up", Toast.LENGTH_SHORT).show();
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
