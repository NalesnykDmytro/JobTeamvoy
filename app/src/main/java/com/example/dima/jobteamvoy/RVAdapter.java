package com.example.dima.jobteamvoy;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    String numberLikeStr;
    String numberUnlikeStr;

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView nameUser;
        ImageView photo;
        TextView numberUnlike;
        TextView numberLike;
        TextView date;
        CheckBox unlike;
        CheckBox like;

        PersonViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            nameUser = (TextView)itemView.findViewById(R.id.name);
            photo = (ImageView) itemView.findViewById(R.id.photo);
            numberLike = (TextView) itemView.findViewById(R.id.number_like);
            numberUnlike = (TextView) itemView.findViewById(R.id.number_unlike);
            date = (TextView) itemView.findViewById(R.id.date);
            like = (CheckBox) itemView.findViewById(R.id.like);
            unlike = (CheckBox) itemView.findViewById(R.id.unlike);

        }
    }

    List<PhotoInfo> photoInfo;
    RVAdapter(List<PhotoInfo> photoInfo){
        this.photoInfo = photoInfo;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_list_photo, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, final int position) {

        personViewHolder.nameUser.setText(photoInfo.get(position).name);
        personViewHolder.photo.setImageResource(photoInfo.get(position).photoId);
        numberLikeStr = String.valueOf(photoInfo.get(position).like);
        personViewHolder.numberLike.setText(numberLikeStr);
        numberUnlikeStr = String.valueOf(photoInfo.get(position).unlike);
        personViewHolder.numberUnlike.setText(numberUnlikeStr);
        String format = "dd.MM.yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        personViewHolder.date.setText(simpleDateFormat.format(photoInfo.get(position).date));

        personViewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(personViewHolder.like.isChecked()){
                    photoInfo.get(position).like = photoInfo.get(position).like - 1;
                    personViewHolder.like.setBackgroundResource(R.mipmap.like);

                } else {
                    photoInfo.get(position).like = photoInfo.get(position).like + 1;
                    personViewHolder.like.setBackgroundResource(R.mipmap.like_checked);

                    if (personViewHolder.unlike.isChecked()){

                    }else {
                        personViewHolder.unlike.setChecked(true);
                        photoInfo.get(position).unlike = photoInfo.get(position).unlike - 1;
                        personViewHolder.unlike.setBackgroundResource(R.mipmap.unlike);
                        numberUnlikeStr = String.valueOf(photoInfo.get(position).unlike);
                        personViewHolder.numberUnlike.setText(numberUnlikeStr);
                    }

                }
                numberLikeStr = String.valueOf(photoInfo.get(position).like);
                personViewHolder.numberLike.setText(numberLikeStr);
            }
        });

        personViewHolder.unlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (personViewHolder.unlike.isChecked()){
                    photoInfo.get(position).unlike = photoInfo.get(position).unlike - 1;
                    personViewHolder.unlike.setBackgroundResource(R.mipmap.unlike);
                } else {
                    photoInfo.get(position).unlike = photoInfo.get(position).unlike + 1;
                    personViewHolder.unlike.setBackgroundResource(R.mipmap.unlike_checked);

                    if (personViewHolder.like.isChecked()){

                    }else {
                        personViewHolder.like.setChecked(true);
                        photoInfo.get(position).like = photoInfo.get(position).like - 1;
                        personViewHolder.like.setBackgroundResource(R.mipmap.like);
                        numberLikeStr = String.valueOf(photoInfo.get(position).like);
                        personViewHolder.numberLike.setText(numberLikeStr);
                    }
                }
                numberUnlikeStr = String.valueOf(photoInfo.get(position).unlike);
                personViewHolder.numberUnlike.setText(numberUnlikeStr);
            }
        });

    }

    @Override
    public int getItemCount() {
        return photoInfo.size();
    }

}
