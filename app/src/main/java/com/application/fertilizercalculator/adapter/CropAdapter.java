package com.application.fertilizercalculator.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.fertilizercalculator.R;
import com.application.fertilizercalculator.model.Crop;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.MyCropHolderView> {

     List<Crop> list;
     Context context;
     OnProvideCrop listener;

    public CropAdapter(List<Crop> list, Context context , CropAdapter.OnProvideCrop listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
        Collections.shuffle(list);
    }

    public interface OnProvideCrop{
         void selectedCrop(Crop crop);
    }



    @NonNull
    @Override
    public CropAdapter.MyCropHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_crop, parent, false);
        return new MyCropHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CropAdapter.MyCropHolderView cropHolder, int position) {

        Crop crop = list.get(position);
//        String cropName = crop.getName() + "\n ("+ crop.getProductType()+")";
        cropHolder.tvCropName.setText(crop.getName());
        // set network image.
        if (crop.getPhoto() != null && !crop.getPhoto().equals("")){
            Picasso.with(context).load(crop.getPhoto()).into(cropHolder.ivCropImage);
        }


        if (crop.getSelected()){

            cropHolder.itemCardView.setBackground(context.getDrawable(R.drawable.bg_focused));

        }else{
//            cropHolder.itemCardView.setBackground(context.getDrawable(R.drawable.card_bg));
            cropHolder.itemCardView.setBackgroundColor(Color.WHITE);
        }

    }


    public void checkSelected(Crop selectedCrop){
        for (Crop item:list) {
            if (selectedCrop.getId().equals(item.getId())){
                item.setSelected(true);
            }else {
                item.setSelected(false);
            }
        }

//        notifyDataSetChanged();



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


     class MyCropHolderView extends RecyclerView.ViewHolder {

        private TextView tvCropName;
        private ImageView ivCropImage;
        private LinearLayout itemCardView;

        public MyCropHolderView(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            tvCropName = itemView.findViewById(R.id.cropName);
            ivCropImage = itemView.findViewById(R.id.cropImage);
            itemCardView = itemView.findViewById(R.id.itemLinearLayout);

            itemView.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int cropPosition = getAdapterPosition();
                    listener.selectedCrop(list.get(cropPosition));
                    checkSelected(list.get(cropPosition));
                    notifyDataSetChanged();



                }
            });

        }
    }


}
