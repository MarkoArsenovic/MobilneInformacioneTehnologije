package com.androidvezbe.ftn.memory.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidvezbe.ftn.memory.Model.Memory;
import com.androidvezbe.ftn.memory.R;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;


public class MemoriesRecyclerAdapter extends RecyclerView.Adapter<MemoriesRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Memory> memoryList;

    public MemoriesRecyclerAdapter(Context context, List<Memory> memoryList) {
        this.context = context;
        this.memoryList = memoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_row, parent, false);


        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Memory memory = memoryList.get(position);
        String imageUrl = null;

        holder.title.setText(memory.getTitle());
        holder.desc.setText(memory.getDesc());


        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(memory.getTimestamp())).getTime());



        holder.timestamp.setText(formattedDate);

        imageUrl = memory.getImage();


        Picasso.with(context)
                .load(imageUrl)
                .into(holder.image);



    }

    @Override
    public int getItemCount() {
        return memoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public TextView timestamp;
        public ImageView image;
        String userid;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;

            title = (TextView) view.findViewById(R.id.postTitleList);
            desc = (TextView) view.findViewById(R.id.postTextList);
            image = (ImageView) view.findViewById(R.id.postImageList);
            timestamp = (TextView) view.findViewById(R.id.timestampList);

            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // we can go to the next activity...

                }
            });

        }
    }
}
