package com.group5.controller.PlacesOfCity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.group5.controller.DetailActivity;
import com.group5.controller.GlobalVariable;
import com.group5.controller.R;
import com.group5.model.Place;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duy on 15-Dec-15.
 */
public class PlacesOfCityAdapter extends RecyclerView.Adapter<PlacesOfCityAdapter.ViewHolder> {

    //List<NatureItem> mItems;
    private Context mContext;
    private ArrayList<Place> arrayListPlaces;

    public PlacesOfCityAdapter(Context context, ArrayList<Place> arrayListPlaces) {
        super();
        this.mContext = context;
        this.arrayListPlaces = arrayListPlaces;
    }

    @Override
    public PlacesOfCityAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.places_of_city_cardview, viewGroup, false);
        return new PlacesOfCityAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlacesOfCityAdapter.ViewHolder viewHolder, int position) {

        viewHolder.namePlace.setText(arrayListPlaces.get(position).getPlaceName());
        viewHolder.locationPlace.setText(arrayListPlaces.get(position).getAddress());
        viewHolder.decriptionPlace.setText(arrayListPlaces.get(position).getPlaceDescription());
        //viewHolder.imageViewPlace.setImageResource(R.drawable.nhatho);
        ImageLoader.getInstance().displayImage(arrayListPlaces.get(position).firstImageURL, viewHolder.imageViewPlace);
        viewHolder.mCardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return arrayListPlaces.size();
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
                    //Toast.makeText(mContext, mCardView.getTag().toString(), Toast.LENGTH_LONG).show();
                    int position = (int) mCardView.getTag();

                    GlobalVariable.idGlobalPlaceCurrent = arrayListPlaces.get(position).getPlaceId();
                    GlobalVariable.longtitute = arrayListPlaces.get(position).getLongitude();
                    GlobalVariable.latitute = arrayListPlaces.get(position).getLatitude();
                    GlobalVariable.name = arrayListPlaces.get(position).getPlaceName();
                    GlobalVariable.firstImageUrl = arrayListPlaces.get(position).firstImageURL;
                    Intent intent = new Intent(mContext.getApplicationContext(), DetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.getApplicationContext().startActivity(intent);
                }
            });
        }
    }
}
