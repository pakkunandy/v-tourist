package com.group5.controller.PlacesOfCity;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.group5.controller.R;

import java.util.List;

/**
 * Created by Duy on 15-Dec-15.
 */
public class PlacesOfCityAdapter extends RecyclerView.Adapter<PlacesOfCityAdapter.ViewHolder> {

    //List<NatureItem> mItems;
    private Context mContext;

    public PlacesOfCityAdapter(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public PlacesOfCityAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.places_of_city_cardview, viewGroup, false);
        return new PlacesOfCityAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlacesOfCityAdapter.ViewHolder viewHolder, int position) {

        viewHolder.namePlace.setText("aaa");
        viewHolder.locationPlace.setText("aaa");
        viewHolder.decriptionPlace.setText("aaa");
        viewHolder.imageViewPlace.setImageResource(R.drawable.nhatho);
        viewHolder.mCardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageViewPlace;
        public TextView namePlace;
        public TextView locationPlace;
        public TextView decriptionPlace;
        public CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewPlace = (ImageView)itemView.findViewById(R.id.imageViewPlace);
            namePlace = (TextView)itemView.findViewById(R.id.namePlace);
            locationPlace = (TextView)itemView.findViewById(R.id.locationPlace);
            decriptionPlace = (TextView)itemView.findViewById(R.id.decriptionPlace);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, mCardView.getTag().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
