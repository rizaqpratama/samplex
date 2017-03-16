package com.t_kost.kost_tegal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class DaftarKost extends AppCompatActivity {
    private GridView gridView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kost);
        ImageButton btBack=(ImageButton)findViewById(R.id.btBack);



        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DaftarKost.this,Home.class));
                finish();
            }
        });
        gridView=(GridView)findViewById(R.id.gdView);
        final ArrayList<String> kosan = new ArrayList<>();
        kosan.add("Margadana");
        kosan.add("Tegal Barat");
        kosan.add("Tegal Selatan");
        kosan.add("Tegal Timur");
        final ArrayList<String> idkosan = new ArrayList<>();
        idkosan.add("6");//Sesuaikan dengan idkecamatan di database
        idkosan.add("3");//Sesuaikan dengan idkecamatan di database
        idkosan.add("4");//Sesuaikan dengan idkecamatan di database
        idkosan.add("7");//Sesuaikan dengan idkecamatan di database
        CustomList dataAdapter = new CustomList(this, kosan);
        gridView.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < kosan.size(); i++) {
                    if (position == i) {
                        String kosannya = kosan.get(position);
                        Intent pindahkan=new Intent(DaftarKost.this,DetailKost.class);
                        pindahkan.putExtra("tipex",kosannya);
                        pindahkan.putExtra("id",idkosan.get(position));
                        startActivity(pindahkan);
                        finish();
                            break;

                    }
                }
            }
        });
    }



    public class CustomList extends ArrayAdapter<String> {

        private final Activity context;
        private final ArrayList<String> kosan;


        public CustomList(Activity context, ArrayList<String> kosan) {
            super(context, R.layout.rowuntukdaftarkost, kosan);
            this.context = context;
            this.kosan = kosan;


        }

        public View getView(final int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.rowuntukdaftarkost, null, true);
            TextView tvDaftarKost=(TextView)rowView.findViewById(R.id.tvDaftarKost);
            tvDaftarKost.setText(kosan.get(position));
            return rowView;
        }

    }

    @Override
    public void onBackPressed() {

    }


}
