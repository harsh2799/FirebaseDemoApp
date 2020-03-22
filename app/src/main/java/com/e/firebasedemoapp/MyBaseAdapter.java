package com.e.firebasedemoapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class MyBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<PeopleModel> dataModelArrayList;

    public MyBaseAdapter(Context context, ArrayList<PeopleModel> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.raw_list_view,null);

        TextView tvData = (TextView)convertView.findViewById(R.id.tv_raw_list);

//        UserModel dataModel = (UserModel)getItem(position);
        tvData.setText(dataModelArrayList.get(position).getPeople_fn()+ " " +  dataModelArrayList.get(position).getPeople_ln());


convertView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String id = dataModelArrayList.get(position).getPeople_id();
        String fn = dataModelArrayList.get(position).getPeople_fn();
        String ln = dataModelArrayList.get(position).getPeople_ln();

        Intent  i = new Intent(context,UpdateActivity.class);
        i.putExtra("ID",id);
        i.putExtra("FN",fn);
        i.putExtra("LN",ln);

        context.startActivity(i);
    }
});
        return convertView;

    }
}
