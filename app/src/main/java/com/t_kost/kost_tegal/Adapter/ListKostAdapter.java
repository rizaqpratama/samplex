package com.t_kost.kost_tegal.Adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.t_kost.kost_tegal.Library.Constant;
import com.t_kost.kost_tegal.R;

import java.util.ArrayList;
import java.util.List;

public class ListKostAdapter extends ArrayAdapter<String> {
    private final Activity context;
    List<String> listnama_kost = new ArrayList<String>();
    List<String> listnama_pemilik = new ArrayList<String>();
    List<String> listno_hp = new ArrayList<String>();
    List<String> listalamat = new ArrayList<String>();
    List<String> listrt = new ArrayList<String>();
    List<String> listrw = new ArrayList<String>();
    List<String> listlong = new ArrayList<String>();
    List<String> listlatt = new ArrayList<String>();
    List<String> listtarif = new ArrayList<String>();
    List<String> listketerangan = new ArrayList<String>();
    List<String> listfoto = new ArrayList<String>();
    List<String> listnama_kecamatan = new ArrayList<String>();
    List<String> listnama_keluraha = new ArrayList<String>();
    public ListKostAdapter(Activity context,
                           List<String> listnama_kost,
                           List<String> listnama_pemilik,
                           List<String> listno_hp,
                           List<String> listalamat,
                           List<String> listrt,
                           List<String> listrw,
                           List<String> listlong,
                           List<String> listlatt,
                           List<String> listtarif,
                           List<String> listketerangan,
                           List<String> listfoto,
                           List<String> listnama_kecamatan,
                           List<String> listnama_keluraha) {
        super(context, R.layout.list_kost, listnama_kost);

        this.context = context;
        this.listnama_kost = listnama_kost;
        this.listnama_pemilik = listnama_pemilik;
        this.listno_hp = listno_hp;
        this.listalamat = listalamat;
        this.listrt = listrt;
        this.listrw = listrw;
        this.listlong = listlong;
        this.listlatt = listlatt;
        this.listtarif = listtarif;
        this.listketerangan = listketerangan;
        this.listfoto = listfoto;
        this.listnama_kecamatan = listnama_kecamatan;
        this.listnama_keluraha = listnama_keluraha;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_kost, null, true);
        TextView txtNama = (TextView) rowView.findViewById(R.id.tvNamaKos);
        TextView txtTarif = (TextView) rowView.findViewById(R.id.tvTarif);
        TextView txtAlamat = (TextView) rowView.findViewById(R.id.tvAlamat);
        ImageView img = (ImageView) rowView.findViewById(R.id.img);
        txtNama.setText(listnama_kost.get(position));
        txtTarif.setText("Rp."+listtarif.get(position));
        txtAlamat.setText(listalamat.get(position) +" "+ listrt.get(position) +" "+ listrw.get(position)+" "+listnama_keluraha.get(position)+" "+listnama_kecamatan.get(position));
        if(!listfoto.get(position).equals(null)){
            if(!listfoto.get(position).equals("null")){
                String link_gambar = Constant.IMAGE_URL+listfoto.get(position);
                Picasso.with(getContext())
                        .load(link_gambar)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .into(img);
            }
        }

        return rowView;
    }

}