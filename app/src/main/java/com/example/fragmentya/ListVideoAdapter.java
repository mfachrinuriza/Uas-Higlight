package com.example.fragmentya;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


class ListVideoAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListVideoAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
    }
    public int getCount() {
        return data.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ListVideoViewHolder holder = null;
        if (convertView == null) {
            holder = new ListVideoViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.list_video, parent, false);
            holder.vgalleryImage = (ImageView) convertView.findViewById(R.id.vgalleryImage);
            holder.vtitle = (TextView) convertView.findViewById(R.id.vtitle);
            holder.vdescription = (TextView) convertView.findViewById(R.id.vdescription);
            holder.vpublished = (TextView) convertView.findViewById(R.id.vpublished);
            convertView.setTag(holder);
        } else {
            holder = (ListVideoViewHolder) convertView.getTag();
        }
        holder.vgalleryImage.setId(position);
        holder.vtitle.setId(position);
        holder.vdescription.setId(position);
        holder.vpublished.setId(position);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        try{
            holder.vtitle.setText(song.get(Hightlight.KEY_TITLE));
            holder.vdescription.setText(song.get(Hightlight.KEY_DESCRIPTION));
            holder.vpublished.setText(song.get(Hightlight.KEY_PUBLISHEDAT));

            if(song.get(Hightlight.KEY_THUMBNAILS).toString().length() < 5)
            {
                holder.vgalleryImage.setVisibility(View.GONE);
            }else{
                Picasso.with(activity)
                        .load(song.get(Hightlight.KEY_THUMBNAILS))
                        .resize(800, 500)
                        .centerCrop()
                        .into(holder.vgalleryImage);
            }
        }catch(Exception e) {}
        return convertView;
    }
}

class ListVideoViewHolder {
    ImageView vgalleryImage;
    TextView vtitle, vdescription, vpublished;
}