package com.example.waleed.firebasestartup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Waleed on 27/01/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_recyclerview_item, parent, false );
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        check if code doesn't work-----------*******************--------------->
        ListItem listItem  = listItems.get(position);

        holder.relativeLayout_HeadingOne.setText(listItem.getHeader());
        holder.getRelativeLayout_HeadingOneDescription.setText(listItem.getDesc());





    }

    @Override
    public int getItemCount() {
        return  listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView  relativeLayout_HeadingOne;
        public TextView getRelativeLayout_HeadingOneDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            relativeLayout_HeadingOne = (TextView) itemView.findViewById(R.id.relativeLayout_HeadingOne);
            getRelativeLayout_HeadingOneDescription = (TextView) itemView.findViewById(R.id.relativeLayout_HeadingOneDescription);
        }
    }
}
