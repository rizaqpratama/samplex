package com.t_kost.kost_tegal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.t_kost.kost_tegal.Adapter.ListKostAdapter;
import com.t_kost.kost_tegal.Library.Constant;
import com.t_kost.kost_tegal.Library.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailKost extends AppCompatActivity {

    ProgressBar pB;
    ListView list;
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
    String id = "", search = "";
    EditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_detail_kost);
        final String tipe=getIntent().getExtras().getString("tipex");
        id = getIntent().getExtras().getString("id");

//       ListView lvList=(ListView)findViewById(R.id.lvList);
        TextView tvJdlKost=(TextView)findViewById(R.id.tvJdlKost);
        TextView tvHeader=(TextView)findViewById(R.id.tvHeader);
        edtSearch = (EditText)findViewById(R.id.etSearch);
        pB = (ProgressBar)findViewById(R.id.pB);
        list = (ListView)findViewById(R.id.listView);

        if(isNetworkConnected()){
            pB.setVisibility(View.VISIBLE);
            listKost();
        }else {
            Toast.makeText(getApplicationContext(),"Internet tidak tersedia!", Toast.LENGTH_SHORT).show();
        }

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    Toast.makeText(getApplicationContext(),edtSearch.getText().toString(),Toast.LENGTH_SHORT).show();
                    //On search keyboard press
                    if(isNetworkConnected()){
                        pB.setVisibility(View.VISIBLE);
                        search = edtSearch.getText().toString();
                        listKost();
                    }else {
                        Toast.makeText(getApplicationContext(),"Internet tidak tersedia!", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        tvJdlKost.setText(String.format("LIST  %s", tipe));
        tvHeader.setText(tipe);
        ImageButton btBack=(ImageButton) findViewById(R.id.btBack);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailKost.this,DaftarKost.class));
                finish();
            }
        });
//        final ArrayList <String> listKost=new ArrayList<>();
//        listKost.clear();
//        assert tipe != null;
//        if(tipe.equalsIgnoreCase("margadana")){
//            listKost.add("A1");
//            listKost.add("A2");
//            listKost.add("A3");
//            listKost.add("A4");
//            listKost.add("A5");
//        }else  if(tipe.equalsIgnoreCase("tegal barat")){
//            listKost.add("B1");
//            listKost.add("B2");
//            listKost.add("B3");
//            listKost.add("B4");
//            listKost.add("B5");
//        }else if(tipe.equalsIgnoreCase("tegal timur")){
//            listKost.add("C1");
//            listKost.add("C2");
//            listKost.add("C3");
//            listKost.add("C4");
//            listKost.add("C5");
//        }else {
//                listKost.add("D1");
//                listKost.add("D2");
//                listKost.add("D3");
//                 listKost.add("D4");
//                 listKost.add("D5");
//
//        }


//        CustomList dataAdapter = new CustomList(this, listKost);
//        lvList.setAdapter(dataAdapter);
//        dataAdapter.notifyDataSetChanged();
//        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                for (int i = 0; i < listKost.size(); i++) {
//                    if (position == i) {
//                        String listkost = listKost.get(position);
//                        Intent pindahkan=new Intent(DetailKost.this,KostRinci.class);
//                        pindahkan.putExtra("tipe",listkost);
//                        pindahkan.putExtra("tipex",tipe);
//                        startActivity(pindahkan);
//                        finish();
//                        break;
//
//                    }
//                }
//            }
//        });

    }

    public void listKost(){
        pB.setVisibility(View.VISIBLE);
        new PosPenyetortAsync().execute(
                Constant.FUNGSI_LISTKOST,
                id,//idKecamatan
                search//Keyword pencarian
        );
    }

    class PosPenyetortAsync extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        private final String LOGIN_URL = Constant.BASE_URL;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();
                params.put("function", args[0]);
                params.put("idkecamatan", args[1]);
                params.put("search", args[2]);
                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(JSONObject json) {
            if (json != null) {
//                Toast.makeText(getApplicationContext(),json.toString(),Toast.LENGTH_LONG).show();
                try {
                    listnama_kost.clear();
                    listnama_pemilik.clear();
                    listno_hp.clear();
                    listalamat.clear();
                    listrt.clear();
                    listrw.clear();
                    listlong.clear();
                    listlatt.clear();
                    listtarif.clear();
                    listketerangan.clear();
                    listfoto.clear();
                    listnama_kecamatan.clear();
                    listnama_keluraha.clear();
                    JSONArray jsonArray = json.getJSONArray("hasil");
                    if(jsonArray.length()==0){
                        Toast.makeText(getApplicationContext(),"Nama tidak ditemukan!",Toast.LENGTH_SHORT).show();
                    }else{
                        for (int i=0; i<jsonArray.length(); i++) {
                            JSONObject isiArray = jsonArray.getJSONObject(i);
                            String nama_kost = isiArray.getString("nama_kost");
                            String nama_pemilik = isiArray.getString("nama_pemilik");
                            String no_hp = isiArray.getString("no_hp");
                            String alamat = isiArray.getString("alamat");
                            String rt = isiArray.getString("rt");
                            String rw = isiArray.getString("rw");
                            String longg = isiArray.getString("long");
                            String latt = isiArray.getString("latt");
                            String tarif = isiArray.getString("tarif");
                            String keterangan = isiArray.getString("keterangan");
                            String foto = isiArray.getString("foto");
                            String nama_kecamatan = isiArray.getString("nama_kecamatan");
                            String nama_kelurahan = isiArray.getString("nama_kelurahan");
                            listnama_kost.add(nama_kost);
                            listnama_pemilik.add(nama_pemilik);
                            listno_hp.add(no_hp);
                            listalamat.add(alamat);
                            listrt.add(rt);
                            listrw.add(rw);
                            listlong.add(longg);
                            listlatt.add(latt);
                            listtarif.add(tarif);
                            listketerangan.add(keterangan);
                            listfoto.add(foto);
                            listnama_kecamatan.add(nama_kecamatan);
                            listnama_keluraha.add(nama_kelurahan);
                        }
                    }

                    getAllData();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
                }
            }
            pB.setVisibility(View.INVISIBLE);
        }

    };

    public void getAllData(){
        ListKostAdapter adapter = new ListKostAdapter(this, listnama_kost,
                listnama_pemilik,
                listno_hp,
                listalamat,
                listrt,
                listrw,
                listlong,
                listlatt,
                listtarif,
                listketerangan,
                listfoto,
                listnama_kecamatan,
                listnama_keluraha);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent pindahkan=new Intent(DetailKost.this,KostRinci.class);
                pindahkan.putExtra("id",id);
                pindahkan.putExtra("nama_kost",listnama_kost.get(position));
                pindahkan.putExtra("nama_pemilik",listnama_pemilik.get(position));
                pindahkan.putExtra("no_hp",listno_hp.get(position));
                pindahkan.putExtra("alamat",listalamat.get(position));
                pindahkan.putExtra("rt",listrt.get(position));
                pindahkan.putExtra("rw",listrw.get(position));
                pindahkan.putExtra("long",listlong.get(position));
                pindahkan.putExtra("latt",listlatt.get(position));
                pindahkan.putExtra("tarif",listtarif.get(position));
                pindahkan.putExtra("keterangan",listketerangan.get(position));
                pindahkan.putExtra("foto",listfoto.get(position));
                pindahkan.putExtra("nama_kecamatan",listnama_kecamatan.get(position));
                pindahkan.putExtra("nama_keluraha",listnama_keluraha.get(position));
                startActivity(pindahkan);
                finish();
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return true;
            }
        });
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public class CustomList extends ArrayAdapter<String> {

        private final Activity context;
        private final ArrayList<String> listKost;


        public CustomList(Activity context, ArrayList<String> listKost) {
            super(context, R.layout.rowuntukdaftarkost,listKost);
            this.context = context;
            this.listKost = listKost;


        }

        public View getView(final int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.rowuntukdaftarkost, null, true);
            TextView tvDaftarKost=(TextView)rowView.findViewById(R.id.tvDaftarKost);
            tvDaftarKost.setText(listKost.get(position));
            return rowView;
        }

    }

    @Override
    public void onBackPressed() {

    }
}
