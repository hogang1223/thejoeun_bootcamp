package com.aosproject.imagemarket.Util;

import androidx.recyclerview.widget.RecyclerView;

public interface CartItemTouchHelperListener {

    boolean onItemMove(int from_position, int to_position);
    void onItemSwipe(int position);
}
