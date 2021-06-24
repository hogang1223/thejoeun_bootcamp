package com.aosproject.imagemarket.Adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.aosproject.imagemarket.Activity.BuyList;
import com.aosproject.imagemarket.Activity.DealCompeletedActivityHK;
import com.aosproject.imagemarket.Bean.BuyListBean;
import com.aosproject.imagemarket.NetworkTask.CartNetworkTaskHK;
import com.aosproject.imagemarket.NetworkTask.DownloadDeviceNetworkTaskHK;
import com.aosproject.imagemarket.NetworkTask.DownloadEmailNetworkTaskHK;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskBuyList;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.BuyListClickListener;
import com.aosproject.imagemarket.Util.CartItemLongClickListener;
import com.aosproject.imagemarket.Util.ShareVar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.aosproject.imagemarket.Util.ShareVar.macIP;

public class BuyListAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<BuyListBean> data = null;
    private LayoutInflater inflater = null;
    private BuyListClickListener clickListener;

    String urlAddr = null;
    int dealNo;
    int recommendInt;


    // Download image
    Dialog dialogDownload;
    TextView tvDownloadCount, tvBtnCancel, tvBtnRefund;
    LinearLayout layoutEmail, layoutPhone;

    Date today = new Date(); // 오늘날짜
    String downloadImageTitle;
    String downloadImageFilepath;
    String datedeal;
    Date dealDate;
    int downloadCount;
    int downloadDealNo;



    public BuyListAdapter(Context mContext, int layout, ArrayList<BuyListBean> data, BuyListClickListener clickListener) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.clickListener = clickListener;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getDealNo();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout, parent, false);

        TextView refund = convertView.findViewById(R.id.profile_tv_buylist_refund);
        TextView date = convertView.findViewById(R.id.profile_tv_buylist_date);
        ImageView img = convertView.findViewById(R.id.profile_tv_buylist_img);
        TextView seller = convertView.findViewById(R.id.profile_tv_buylist_seller);
        TextView title = convertView.findViewById(R.id.profile_tv_buylist_title);
        TextView price = convertView.findViewById(R.id.profile_tv_buylist_price);

        ImageView download = convertView.findViewById(R.id.profile_iv_buylist_download);
        ImageView recommend = convertView.findViewById(R.id.profile_tv_buylist_recommend);

        date.setText(data.get(position).getDealDate() + " - " + data.get(position).getBuyCode());
//        img.setImageResource();
        seller.setText(data.get(position).getMyname());
        title.setText(data.get(position).getTitle());
        price.setText(data.get(position).getPrice() + "원");

        if(data.get(position).getDownloadCount() >= 3) {
            download.setVisibility(View.INVISIBLE);
        }else {
            download.setVisibility(View.VISIBLE);
            download.setColorFilter(Color.parseColor("#845EC2"));
        }

        // dealCancelDate == (1111-11-11)
        if(!data.get(position).getDealCancelDate().equals("1111-11-11")){
            download.setVisibility(View.GONE);
            recommend.setVisibility(View.GONE);
            refund.setVisibility(View.VISIBLE);

        }

        recommendInt = data.get(position).getRecommend();
//        dealNo = data.get(position).getDealNo();
        download.setTag(data.get(position).getDealNo());
        recommend.setTag(data.get(position).getRecommend());


        if(recommendInt == 1) {
            recommend.setImageResource(R.drawable.ic_baseline_thumb_up_off_alt_24);
            recommend.setColorFilter(Color.parseColor("#845EC2"));
        }else {
            recommend.setImageResource(R.drawable.ic_baseline_thumb_up_alt_24);
            recommend.setColorFilter(Color.parseColor("#845EC2"));
        }


        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = connectInsertData((Integer)recommend.getTag(), (Integer)download.getTag());
                    if(result.equals("1") && (Integer)recommend.getTag() == 1 ) {
                        Toast.makeText(mContext, "추천을 취소했습니다.", Toast.LENGTH_SHORT).show();
                        clickListener.onBuyListClickListener(true);
                    }else if(result.equals("1") && (Integer)recommend.getTag() == 0){
                        clickListener.onBuyListClickListener(true);
                    }
                    else{
                        Toast.makeText(mContext, "추천 취소를 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        // download image
        dialogDownload = new Dialog(convertView.getContext());       // Dialog 초기화
        dialogDownload.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dialogDownload.setContentView(R.layout.download_dialog_layout);

        WindowManager.LayoutParams lpDown = new WindowManager.LayoutParams();
        lpDown.copyFrom(dialogDownload.getWindow().getAttributes());
        lpDown.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lpDown.height = WindowManager.LayoutParams.WRAP_CONTENT;
        Window window = dialogDownload.getWindow();
        window.setAttributes(lpDown);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, mContext.MODE_PRIVATE);
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},  mContext.MODE_PRIVATE);

                showDownloadDialog((Integer)download.getTag());
            }
        });
        return convertView;
    }

//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.profile_iv_buylist_download:
//                    //효경언니 여기 추가하세요~~
//
//
//
//                    break;
////                case R.id.profile_tv_buylist_recommend:
////                    String result = connectInsertData();
////                    if(result.equals("1")) {
////                        Toast.makeText(mContext, "추천을 취소했습니다.", Toast.LENGTH_SHORT).show();
//////                        Intent intent = new Intent(mContext, BuyList.class);
////                    }else {
////                        Toast.makeText(mContext, "추천 취소를 실패하였습니다.", Toast.LENGTH_SHORT).show();
////                    }
//            }
//        }
//    };

    private String connectInsertData(int recommendInt, int dealNo) {

        int update;
        if(recommendInt == 1) {
            update = 0;
        }else {
            update = 1;
        }
        urlAddr = macIP + "jsp/profile_buylist_recommend.jsp?dealNo=" + dealNo + "&update=" + update;
        String result = null;

        try {
            NetworkTaskBuyList networkTask = new NetworkTaskBuyList(mContext, urlAddr, "recommend");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    // download image
    // my order list에서 download btn click 시 사용할 것
    public void showDownloadDialog(int dealNo){

        for(int i=0; i<data.size(); i++){
            if(data.get(i).getDealNo() == dealNo){
                downloadDealNo = data.get(i).getDealNo();
                downloadImageTitle = data.get(i).getTitle();
                downloadImageFilepath = data.get(i).getFilepath();
                datedeal = data.get(i).getDealDate();
                downloadCount = data.get(i).getDownloadCount();
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            dealDate = sdf.parse(datedeal);
        }catch (Exception e){
            e.printStackTrace();
        }


        dialogDownload.show();

        tvDownloadCount = dialogDownload.findViewById(R.id.dialog_download_tv_count);
        tvBtnRefund = dialogDownload.findViewById(R.id.dialog_download_tv_btn_refund);
        tvBtnCancel = dialogDownload.findViewById(R.id.dialog_download_tv_btn_cancel);
        layoutEmail = dialogDownload.findViewById(R.id.dialog_download_layout_email);
        layoutPhone = dialogDownload.findViewById(R.id.dialog_download_layout_phone);

        tvBtnCancel.setOnClickListener(dialogOnClickListener);
        tvBtnRefund.setOnClickListener(dialogOnClickListener);
        layoutEmail.setOnClickListener(dialogOnClickListener);
        layoutPhone.setOnClickListener(dialogOnClickListener);

        int afterDeal = today.getDate() - dealDate.getDate();

        // download count != 0, || now() > dealDate+7 tvRefund setVisibility GONE
        if(downloadCount>0 || afterDeal > 7){
            tvBtnRefund.setVisibility(View.GONE);
        }
        tvDownloadCount.setText(Integer.toString(3-downloadCount));

    }

    View.OnClickListener dialogOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.dialog_download_tv_btn_cancel:
                    dialogDownload.dismiss();
                    break;

                case R.id.dialog_download_tv_btn_refund:
                    new AlertDialog.Builder(mContext)
                            .setMessage("결제 취소 하시겠습니까?")
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String result = connectDelete(downloadDealNo);
                                    if(result.equals("1")){
                                        Toast.makeText(mContext, "결제가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                        clickListener.onBuyListClickListener(true);
                                        dialogDownload.dismiss();
                                    }else{
                                        Toast.makeText(mContext, "결제 취소에 실패했습니다. 관리자에게 문의해주세요", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .show();
                    break;
                case R.id.dialog_download_layout_email:
//                    pbDownload.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, "이메일로 전송 중 입니다. 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show();
                    String result = connectEmailDownload();
                    if(result.equals("1")){
                        Toast.makeText(mContext, downloadImageTitle + "가 이메일로 전송 되었습니다.", Toast.LENGTH_SHORT).show();
                        connectUpdateDownloadCount(downloadDealNo);
                    }else{
                        Toast.makeText(mContext, "다운로드에 실패하였습니다. 관리자에게 문의해주세요.", Toast.LENGTH_SHORT).show();
                    }
//                    pbDownload.setVisibility(View.INVISIBLE);
                    dialogDownload.dismiss();
                    break;
                case R.id.dialog_download_layout_phone:
                    Toast.makeText(mContext, "다운로드 중 입니다. 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show();
                    result = connectDevicelDownload();
                    if(result.equals("1")){
                        Toast.makeText(mContext, downloadImageTitle + "가 다운로드 되었습니다.", Toast.LENGTH_SHORT).show();
                        connectUpdateDownloadCount(downloadDealNo);
                    }else{
                        Toast.makeText(mContext, "다운로드에 실패하였습니다. 관리자에게 문의해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    dialogDownload.dismiss();
                    break;
            }
        }
    };


    // refund(deal Cancle)
    private String connectDelete(int dealNo){
        String result = null;
        String urlAddr = ShareVar.macIP + "jsp/deal_delete_item_HK.jsp?dealNo=" + dealNo;
        try {
            CartNetworkTaskHK networkTask = new CartNetworkTaskHK(mContext, urlAddr, "delete");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // sending email
    private String connectEmailDownload(){
        String result = null;
        String urlAddr = ShareVar.macIP + "jsp/deal_download_send_email.jsp?imageFilepath=" + downloadImageFilepath + "&imageTitle=" + downloadImageTitle + "&loginEmail=hyogangster1@gmail.com";
        try {
            DownloadEmailNetworkTaskHK networkTask = new DownloadEmailNetworkTaskHK(mContext, urlAddr);
            Object obj = networkTask.execute().get();
            result = (String) obj;

        }catch (Exception e){
            e.printStackTrace();
            result = "0";
        }
        return result;
    }

    // download to device
    private String connectDevicelDownload(){
        String result = null;
        String urlAddr = ShareVar.macIP + "image/" + downloadImageFilepath;
        try {
            DownloadDeviceNetworkTaskHK networkTask = new DownloadDeviceNetworkTaskHK(mContext, urlAddr);
            Bitmap bitmap = networkTask.execute().get();


            FileOutputStream outputStream = null;

            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File(sdCard.getAbsolutePath() + "/Pictures");
            Log.v("connectDeviceDownload", directory.toString());
            directory.mkdir();
            String filename = String.format("%d.jpg", System.currentTimeMillis());
            File outFile = new File(directory, filename);

            try {
                outputStream = new FileOutputStream(outFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();

                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intent.setData(Uri.fromFile(outFile));
                mContext.sendBroadcast(intent);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = "1";

        }catch (Exception e){
            e.printStackTrace();
            result = "0";
        }
        return result;
    }

    // update downloadCount
    private String connectUpdateDownloadCount(int dealNo){
        String result = null;
        String urlAddr = ShareVar.macIP + "jsp/deal_update_downloadcount_HK.jsp?dealNo=" + dealNo;
        try {
            CartNetworkTaskHK networkTask = new CartNetworkTaskHK(mContext, urlAddr, "update");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}