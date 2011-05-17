package com.pinkumbrella.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.pinkumbrella.model.Prediction;
import com.pinkumbrella.waterhole.R;

public class PredictionListAdapter extends ArrayAdapter {

		Context c = null;
        private List<Prediction> items;

        public PredictionListAdapter(Context context, int textViewResourceId, List<Prediction> items) {
        	
                super(context, textViewResourceId, items);
                this.items = items;
                this.c = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	
                View v = convertView;
            
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.prediction_row, null);
                }
               
                return v;
        }

}
