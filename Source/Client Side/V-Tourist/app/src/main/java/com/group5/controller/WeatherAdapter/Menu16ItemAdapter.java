package com.group5.controller.WeatherAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group5.controller.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by HarleTracker on 9/27/2015.
 */
public class Menu16ItemAdapter extends RecyclerView.Adapter<Menu16ItemAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    List<C16Information> data = Collections.emptyList();
    private Context context;
    //private ClickListener clickListener;
    public Menu16ItemAdapter(Context context, List<C16Information> data){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.custom_row_weather, viewGroup ,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        C16Information current = data.get(i);
        viewHolder.cusDate.setText(current.date);
        viewHolder.cusMain.setText(current.mainWeather);
        viewHolder.cusMainDesc.setText(current.mainWeatherDesc);
        viewHolder.cusHumi.setText(current.humi +"%  ");
        viewHolder.cusPres.setText(current.press +"hPa  ");
        viewHolder.cusWindS.setText(String.valueOf(current.windSpeed)+"m/s  ");
        setImage(viewHolder.icon, current.iconID);
        viewHolder.cusTemp.setText(current.temp + "\u2103");
    }

    private void setImage(ImageView image, String id) {
        switch (id) {
            case "01d":
            case "01n":
                image.setImageResource(R.drawable.ic_w1);
                break;
            case "02d":
            case "02n":
                image.setImageResource(R.drawable.ic_w2);
                break;
            case "03d":
            case "03n":
                image.setImageResource(R.drawable.ic_w3);
                break;
            case "04d":
            case "04n":
                image.setImageResource(R.drawable.ic_w4);
                break;
            case "09d":
            case "09n":
                image.setImageResource(R.drawable.ic_w9);
                break;
            case "10d":
            case "10n":
                image.setImageResource(R.drawable.ic_w10);
                break;
            case "11d":
            case "11n":
                image.setImageResource(R.drawable.ic_w11);
                break;
            case "13d":
            case "13n":
                image.setImageResource(R.drawable.ic_w13);
                break;
            default: {
                image.setImageResource(R.drawable.ic_w1);
                break;
            }
        }
    }
    //Demo Delete
    /*public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }*/
    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cusDate;
        TextView cusMain;
        TextView cusMainDesc;
        TextView cusHumi;
        TextView cusPres;
        TextView cusWindS;
        ImageView icon;
        TextView cusTemp;

        public MyViewHolder(View itemView) {
            super(itemView);
            cusDate = (TextView)itemView.findViewById(R.id.txtCusDate);
            cusMain = (TextView)itemView.findViewById(R.id.txtCusMain);
            cusMainDesc = (TextView)itemView.findViewById(R.id.txtCusMainDesc);
            cusHumi = (TextView)itemView.findViewById(R.id.txtCusHumidity);
            cusPres = (TextView)itemView.findViewById(R.id.txtCusPressure);
            cusWindS = (TextView)itemView.findViewById(R.id.txtCusWindSpeed);
            icon = (ImageView)itemView.findViewById(R.id.imgCusWeather);
            cusTemp = (TextView)itemView.findViewById(R.id.txtCusTemp);
            //itemView.setOnClickListener(this);
        }


        /*@Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, SubActivity.class));
            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }
        }*/
    }

    /*public interface ClickListener{
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }*/
}
