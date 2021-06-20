package com.aosproject.imagemarket.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aosproject.imagemarket.Activity.ImageAddContentActivity;
import com.aosproject.imagemarket.Activity.ImageAddImageActivity;
import com.aosproject.imagemarket.Activity.ImageDetailActivity;
import com.aosproject.imagemarket.Adapter.ImageAdapterHJ;
import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    String urlAddr, urlAddr2 = null;
    FloatingActionButton fabUp, fabPlus = null;
    RecyclerView recyclerView = null;
    RecyclerView.LayoutManager layoutManager = null;
    ArrayList<ImageHJ> images = null;
    ImageAdapterHJ adapter = null;

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

            urlAddr = ShareVar.macIP + "jsp/mainImageSelect.jsp";
            Log.v("Message", urlAddr);

            NetworkTaskImageHJ networkTask = new NetworkTaskImageHJ(getActivity(), urlAddr, "mainSelect");
            Object obj = networkTask.execute().get();
            images = (ArrayList<ImageHJ>) obj;

            adapter = new ImageAdapterHJ(getActivity(), R.layout.main_custom_layout, images);
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new ImageAdapterHJ.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int pos) {
                    Intent intent = null;
                    intent = new Intent(getActivity(), ImageDetailActivity.class);
                    intent.putExtra("code", images.get(pos).getCode());
                    startActivity(intent);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ImageAddImageActivity.class);
            startActivity(intent);
        }
    };

    public void setFloatingActionButton(final View view) {
        fabUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
