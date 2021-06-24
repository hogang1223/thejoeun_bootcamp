package com.aosproject.imagemarket.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.aosproject.imagemarket.R;

public class CartItemTouchHelperCallback extends ItemTouchHelper.Callback {
//    private boolean cartItemStatus = true;
    private CartItemTouchHelperListener listener;
    private boolean viewBeingCleared;

    public CartItemTouchHelperCallback(CartItemTouchHelperListener listener) {
        this.listener = listener;
    }

//    public CartItemTouchHelperCallback(boolean cartItemStatus) {
//        this.cartItemStatus = cartItemStatus;
//    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        int drag_flags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipe_flags = ItemTouchHelper.START;

        return makeMovementFlags(0, swipe_flags);
    }


    @Override
    public boolean isLongPressDragEnabled() { return true; }

//    @Override
//    public boolean isItemViewSwipeEnabled() { return cartItemStatus; }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onItemSwipe(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        Paint p = new Paint();
        Bitmap icon;
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;

            p.setColor(Color.parseColor("#FF845EC2"));
            RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
            c.drawRect(background,p);
            icon = BitmapFactory.decodeResource(itemView.getResources(), R.drawable.delete);
            RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
            c.drawBitmap(icon,null,icon_dest,p);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

}
