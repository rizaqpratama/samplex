package com.t_kost.kost_tegal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.t_kost.kost_tegal.Library.Constant;

public class KostRinci extends AppCompatActivity {
    private Button btMap;
    private ImageButton btBack;
    private TextView tvHeader;
    String nama_kost = "";
    String nama_pemilik = " Pemilik";
    String no_hp = "";
    String alamat = "";
    String rt = "";
    String rw = "";
    String longg = "";
    String latt = "";
    String tarif = "";
    String keterangan = "";
    String foto = "";
    String nama_kecamatan = "";
    String nama_keluraha = "";
    String id = "";
    TextView tvNamaKos, tvPemilik, tvHp, tvAlamat, tvTarif, tvKeterangan;
    ImageView ivGambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kost_rinci);
        tvNamaKos = (TextView)findViewById(R.id.tvNamaKos);
        tvPemilik = (TextView)findViewById(R.id.tvPemilik);
        tvHp = (TextView)findViewById(R.id.tvNoHp);
        tvAlamat = (TextView)findViewById(R.id.tvAlamat);
        tvTarif = (TextView)findViewById(R.id.tvTarif);
        tvKeterangan = (TextView)findViewById(R.id.tvKeterangan);
        ivGambar = (ImageView) findViewById(R.id.ivGambar);

        final String tipenya=getIntent().getExtras().getString("tipe");
        final String tipx=getIntent().getExtras().getString("tipex");
        id = getIntent().getExtras().getString("id");
        nama_kost = getIntent().getExtras().getString("nama_kost");
        nama_pemilik = getIntent().getExtras().getString("nama_pemilik");
        no_hp = getIntent().getExtras().getString("no_hp");
        alamat = getIntent().getExtras().getString("alamat");
        rt = getIntent().getExtras().getString("rt");
        rw = getIntent().getExtras().getString("rw");
        longg = getIntent().getExtras().getString("long");
        latt = getIntent().getExtras().getString("latt");
        tarif = getIntent().getExtras().getString("tarif");
        keterangan = getIntent().getExtras().getString("keterangan");
        foto = getIntent().getExtras().getString("foto");
        nama_kecamatan = getIntent().getExtras().getString("nama_kecamatan");
        nama_keluraha = getIntent().getExtras().getString("nama_keluraha");
        btMap=(Button)findViewById(R.id.btMap);
        btBack=(ImageButton)findViewById(R.id.btBack);
        tvHeader=(TextView)findViewById(R.id.tvHeader);
        tvHeader.setText(tipenya);

        tvNamaKos.setText(nama_kost);
        tvPemilik.setText(nama_pemilik);
        tvHp.setText(no_hp);
        tvAlamat.setText(alamat+" "+rt+" "+rw+" "+nama_keluraha+" "+nama_kecamatan);
        tvTarif.setText(tarif);
        tvKeterangan.setText(keterangan);

        if(!foto.equals("")){
            if(!foto.equals(null)){
                if(!foto.equals("null")){
                    String link_gambar = Constant.IMAGE_URL+foto;
                    Picasso.with(getApplicationContext())
                            .load(link_gambar)
                            .memoryPolicy(MemoryPolicy.NO_CACHE)
                            .networkPolicy(NetworkPolicy.NO_CACHE)
                            .into(ivGambar);
                }
            }
        }

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahkan=new Intent(KostRinci.this,DaftarKost.class);
                pindahkan.putExtra("tipex",tipx);
                pindahkan.putExtra("id",id);
                startActivity(pindahkan);
                finish();
            }
        });
        btMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahkan=new Intent(KostRinci.this,MapActivity.class);
                pindahkan.putExtra("dari","rinci");
                pindahkan.putExtra("tipe",tipenya);
                startActivity(pindahkan);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {

    }
}
