package com.aosproject.imagemarket.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aosproject.imagemarket.Activity.ImageAddContentActivity;
import com.aosproject.imagemarket.Activity.ImageAddImageActivity;
import com.aosproject.imagemarket.Activity.ImageAddLocationActivity;
import com.aosproject.imagemarket.Activity.ImageDetailActivity;
import com.aosproject.imagemarket.Adapter.ImageAdapterHJ;
import com.aosproject.imagemarket.Bean.ImageHJ;
import com.aosproject.imagemarket.Bean.UserHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskImageHJ;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskUserHJ;
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
    ArrayList<UserHJ> users = null;
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
            new AlertDialog.Builder(getActivity())
                    .setMessage("이미지를 판매 등록을 진행하시겠습니까?")
                    .setCancelable(false)
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            urlAddr2 = ShareVar.macIP + "jsp/userAccountConfirm.jsp?email=" + ShareVar.loginEmail;

                            try {
                                NetworkTaskUserHJ networkTask = new NetworkTaskUserHJ(getActivity(), urlAddr2, "accountSelect");
                                Object obj = networkTask.execute().get();
                                users = (ArrayList<UserHJ>) obj;
                                if(users.get(0).getAccount_bank().equals("none")){
                                    new AlertDialog.Builder(getActivity())
                                            .setMessage("계좌 정보가 없습니다.\n계좌 정보 등록 후, 판매 등록을 진행해주세요!")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // *************************** 도희님 개인정보 페이지 연결 ****************************
                                                    Intent intent = new Intent(getActivity(), ImageAddLocationActivity.class);                                                    // *************************** 도희님 개인정보 페이지 연결 ****************************
                                                    // *************************** 도희님 개인정보 페이지 연결 ****************************
                                                    startActivity(intent);
                                                }
                                            })
                                            .setNegativeButton("Cancel", null)
                                            .show();
                                }else {
                                    Intent intent = new Intent(getActivity(), ImageAddImageActivity.class);
                                    startActivity(intent);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    })
                    .show();

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
