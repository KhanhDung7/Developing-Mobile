package com.example.nation;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class NationAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Nation> nationList;

    public NationAdapter(Context context, int layout, List<Nation> nationList) {
        this.context = context;
        this.layout = layout;
        this.nationList = nationList;
    }



    @Override
    public int getCount() {
        return nationList.size();
    }

    @Override
    public Object getItem(int i) {
        return nationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHoler {
        ImageView imgLogo;
        TextView txtName;

    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHoler holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);
            holder = new ViewHoler();

            // Anh Xa view
            holder.txtName = (TextView) view.findViewById(R.id.textviewName);
            holder.imgLogo = (ImageView) view.findViewById(R.id.imageviewLogo);

            view.setTag(holder);
        }else {
            holder = (ViewHoler) view.getTag();
        }



        // gan gia tri
        Nation nation = nationList.get(i);
        holder.txtName.setText(nation.getName());
        Picasso.get().load(nation.getUrlLogo()).into(holder.imgLogo);

        // gan animiation
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(animation);

        return view;
    }
}
