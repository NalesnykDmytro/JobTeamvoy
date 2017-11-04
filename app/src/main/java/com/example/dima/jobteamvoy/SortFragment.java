package com.example.dima.jobteamvoy;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SortFragment extends android.support.v4.app.Fragment {

    private static final String ARG_PARAM1 = "number";

    private RecyclerView listPhoto;
    private List<PhotoInfo> photoInfo;
    LinearLayoutManager llm;
    int number = 1;
    Date date1, date2, date3, date4, date5, date6, date7;

    public SortFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (getArguments() != null) {
            number = getArguments().getInt(ARG_PARAM1);
        }
        llm = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sort, container, false);

        listPhoto = (RecyclerView) v.findViewById(R.id.list_photo);

        listPhoto.setLayoutManager(llm);
        listPhoto.setHasFixedSize(true);

        initializeData();

        RVAdapter adapter = new RVAdapter(photoInfo);
        listPhoto.setAdapter(adapter);

        return v;
    }

    private void initializeData() {
        String format = "dd.MM.yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            date1 = simpleDateFormat.parse("12.09.2017 19:01");
            date2 = simpleDateFormat.parse("21.08.2017 16:27");
            date3 = simpleDateFormat.parse("09.10.2017 09:27");
            date4 = simpleDateFormat.parse("16.08.2016 16:26");
            date5 = simpleDateFormat.parse("14.01.2015 21:29");
            date6 = simpleDateFormat.parse("25.05.2017 09:25");
            date7 = simpleDateFormat.parse("09.03.2016 14:15");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        photoInfo = new ArrayList<>();
        photoInfo.add(new PhotoInfo("Dima Nalesnyk", 250, 16, date1, R.mipmap.nature));
        photoInfo.add(new PhotoInfo("Oleg Andrys", 25, 67, date2, R.mipmap.nature2));
        photoInfo.add(new PhotoInfo("Vasyl Fran", 63, 2, date3, R.mipmap.nature3));
        photoInfo.add(new PhotoInfo("Olena Grad", 79, 16, date4, R.mipmap.nature4));
        photoInfo.add(new PhotoInfo("Anna Flity", 456, 67, date5, R.mipmap.nature5));
        photoInfo.add(new PhotoInfo("Volodymyr Driy", 35, 4, date6, R.mipmap.nature6));
        photoInfo.add(new PhotoInfo("Daryna Makar", 543, 32, date7, R.mipmap.nature7));


        if (number == 1) {

            Collections.sort(photoInfo, new Comparator<PhotoInfo>() {
                @Override
                public int compare(PhotoInfo photoInfo, PhotoInfo t1) {
                    return photoInfo.date.compareTo(t1.date);
                }
            });
            Collections.reverse(photoInfo);

        } else if (number == 2) {

            Collections.sort(photoInfo, new Comparator<PhotoInfo>() {
                @Override
                public int compare(PhotoInfo photoInfo, PhotoInfo t1) {
                    return photoInfo.date.compareTo(t1.date);
                }
            });

        } else if (number == 3) {

            Collections.sort(photoInfo, new Comparator<PhotoInfo>() {
                @Override
                public int compare(PhotoInfo photoInfo, PhotoInfo t1) {
                    return photoInfo.like - (t1.like);
                }
            });
            Collections.reverse(photoInfo);

        } else if (number == 4) {

            Collections.shuffle(photoInfo);

        }


    }

}
