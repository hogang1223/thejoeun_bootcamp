package com.aoslec.recyclerview_networkjson_student;


import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerStudentAdapter extends RecyclerView.Adapter<RecyclerStudentAdapter.ViewHolder> {

    public ArrayList<Student> data = null;

    public RecyclerStudentAdapter(ArrayList<Student> data) {
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name, dept, phone;
        public WebView webView;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_card_name);
            dept = itemView.findViewById(R.id.tv_card_dept);
            phone = itemView.findViewById(R.id.tv_card_phone);
            webView = itemView.findViewById(R.id.wv_card_img);

            // Web Setting
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true); // JavaScript 사용 가능
            webSettings.setBuiltInZoomControls(true); // 확대 축소 가능
            webSettings.setDisplayZoomControls(false); // 돋보기 없애기

            webView.setWebViewClient(webViewClient);

        }
    }


    @Override
    public RecyclerStudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerStudentAdapter.ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.dept.setText(data.get(position).getDept());
        holder.phone.setText(data.get(position).getPhone());
        holder.webView.loadData(htmlData(data.get(position).getImg()), "text/html", "UTF-8");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    private String htmlData(String img){

        String image = "<html><head>"+
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />"+
                "<head><body>"+
                "<img src=\"http://192.168.2.3:8080/test/" + img + "\" width =\"auto\" height=\"100%\">" +
                "</body></html>";
        return image;
    }
    WebViewClient webViewClient = new WebViewClient(){

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    };

}

