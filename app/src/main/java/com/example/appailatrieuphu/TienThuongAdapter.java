package com.example.appailatrieuphu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TienThuongAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TienThuong> lstTienThuong;
    int viTriCauHoi=1;

    public TienThuongAdapter(Context context, int layout, List<TienThuong> lstTienThuong) {
        this.context = context;
        this.layout = layout;
        this.lstTienThuong = lstTienThuong;
    }

    @Override
    public int getCount() {
        return lstTienThuong.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder{
        ImageView mocXacNhan;
        TextView mocCauHoi, mocTienThuong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder holder;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layout, null);
            holder= new viewHolder();
            holder.mocCauHoi= (TextView) convertView.findViewById(R.id.listCauHoi);
            holder.mocTienThuong= (TextView) convertView.findViewById(R.id.tienThuong);
            holder.mocXacNhan= (ImageView) convertView.findViewById(R.id.mocCauHoi);
            convertView.setTag(holder);
        }else{
            holder= (viewHolder) convertView.getTag();
        }

        TienThuong tienThuong= lstTienThuong.get(position);

        holder.mocCauHoi.setText(tienThuong.getCauHoi());
        if(position%5==0){
            holder.mocCauHoi.setTextColor(Color.parseColor("#FF0000"));
        }
        holder.mocTienThuong.setText(tienThuong.getTienThuong());
        if(position%5==0){
            holder.mocTienThuong.setTextColor(Color.parseColor("#FF0000"));
        }
        holder.mocXacNhan.setImageResource(tienThuong.getDauMoc());
        holder.mocXacNhan.setVisibility(View.INVISIBLE);

        if(position == (15-viTriCauHoi)){
            holder.mocCauHoi.setBackgroundColor(Color.GREEN);
            holder.mocTienThuong.setBackgroundColor(Color.GREEN);
            holder.mocXacNhan.setBackgroundColor(Color.GREEN);
        }


        return convertView;
    }

    public void setViTriCauHoi(int soCauHoi){
        this.viTriCauHoi= soCauHoi;
        notifyDataSetChanged();
    }


}
