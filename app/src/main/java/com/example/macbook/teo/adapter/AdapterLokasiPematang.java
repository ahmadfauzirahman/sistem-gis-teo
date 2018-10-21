package com.example.macbook.teo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.macbook.teo.R;
import com.example.macbook.teo.activity.Laporan;
import com.example.macbook.teo.config.ServerConfig;
import com.example.macbook.teo.model.ModelLokasiPematang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterLokasiPematang extends RecyclerView.Adapter<AdapterLokasiPematang.AdapterLokasiViewHolder> implements Filterable {

    private List<ModelLokasiPematang> mFilteredList;
    private List<ModelLokasiPematang> mArrayList;
    private int rowLayout;
    private Context context;

    public AdapterLokasiPematang(List<ModelLokasiPematang> lokasiModels, int rowLayout, Context context) {
        this.mArrayList = lokasiModels;
        this.mFilteredList = lokasiModels;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterLokasiPematang.AdapterLokasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AdapterLokasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLokasiViewHolder adapterLokasiViewHolder, int i) {
        adapterLokasiViewHolder.pematang.setText(mFilteredList.get(i).getNama());
        adapterLokasiViewHolder.status.setText(mFilteredList.get(i).getKet());
        adapterLokasiViewHolder.daerah.setText(mFilteredList.get(i).getDaerah());
        adapterLokasiViewHolder.idlokasi.setText(mFilteredList.get(i).getIdLokasi());
        adapterLokasiViewHolder.lat.setText(mFilteredList.get(i).getLat());
        adapterLokasiViewHolder.lng.setText(mFilteredList.get(i).getLng());
        Picasso.get().load(ServerConfig.FOTO + mFilteredList.get(i).getFoto()).into(adapterLokasiViewHolder.gambar);

    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    mFilteredList = mArrayList;
                } else {
                    ArrayList<ModelLokasiPematang> filteredList = new ArrayList<>();

                    for (ModelLokasiPematang seminarProposal : mArrayList) {
                        if (seminarProposal.getNama().toLowerCase().contains(charString) || seminarProposal.getDaerah().toLowerCase().contains(charString)) {
                            filteredList.add(seminarProposal);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredList = (ArrayList<ModelLokasiPematang>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class AdapterLokasiViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lnlokasi;
        TextView pematang, lat, lng, daerah, status,idlokasi;
        ImageView gambar;

        public AdapterLokasiViewHolder(@NonNull final View itemView) {
            super(itemView);
            lnlokasi = (LinearLayout) itemView.findViewById(R.id.lnlokasi);
            pematang = (TextView) itemView.findViewById(R.id.pematang);
            idlokasi = (TextView) itemView.findViewById(R.id.idlokasi);
            lat = (TextView) itemView.findViewById(R.id.lat);
            lng = (TextView) itemView.findViewById(R.id.lng);
            daerah = (TextView) itemView.findViewById(R.id.daerah);
            status = (TextView) itemView.findViewById(R.id.status);
            gambar = (ImageView) itemView.findViewById(R.id.gambar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(),Laporan.class);
                    intent.putExtra("idlokasi",idlokasi.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
