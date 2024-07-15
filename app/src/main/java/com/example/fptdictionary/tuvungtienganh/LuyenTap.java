package com.example.fptdictionary.tuvungtienganh;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.example.fptdictionary.adapter.GridAdapter;
import com.example.fptdictionary.data.DBTuVung;
import com.example.fptdictionary.model.ChuDe;
import com.example.fptdictionary.model.TuVung;

public class LuyenTap extends AppCompatActivity {

    private ImageButton btnBackDsTuVung;
    private Button btnBoQua, btnKiemTra, btnTuTiep, btnLamLai;
    private TextView txtTuVung, txtTenChuDe, txtInform, txtPhatAm;
    private EditText txtNghia;
    private Switch swNgauNhien;

    private GridView gvGoiY;
    private ArrayList<String> dsGoiY;
    private GridAdapter gridAdapter;

    private ChuDe chuDe;
    private String DATATU= null;
    private String NGHIA = null;
    private int COUNT = 0;
    private Random rand = new Random();

    private DBTuVung dbTuVung;
    private boolean flagNgayNhien = false;
    private ArrayList<TuVung> dsTuVung;
    private TuVung tuVungTemp;
    private int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_tap);

        Intent intent_old = getIntent();
        chuDe = (ChuDe) intent_old.getSerializableExtra("ChuDe");

        dbTuVung = new DBTuVung(this);
        addControls();
        addEvents();
    }

    private void addControls() {
        btnBackDsTuVung = findViewById(R.id.btnBackDsChuDe);
        btnBoQua = findViewById(R.id.btnBoQua);
        btnKiemTra = findViewById(R.id.btnKiemTra);
        btnTuTiep = findViewById(R.id.btnTuTiep);
        btnLamLai = findViewById(R.id.btnLamLai);
        swNgauNhien = findViewById(R.id.swNgauNhien);
        swNgauNhien.setChecked(false);
        txtPhatAm = findViewById(R.id.txtPhatAmL);

        btnTuTiep.setVisibility(View.GONE);
        btnLamLai.setVisibility(View.GONE);

        txtTuVung = findViewById(R.id.txtTuVung);
        txtTenChuDe = findViewById(R.id.txtTenChuDe);
        txtNghia = findViewById(R.id.txtNghia);
        txtInform = findViewById(R.id.txtInform);
        txtInform.setVisibility(View.INVISIBLE);

        dsTuVung = new ArrayList<TuVung>();
        layDanhSachTuVung();

        dsGoiY = new ArrayList<String>();
        gvGoiY = findViewById(R.id.gvGoiY);
        gridAdapter = new GridAdapter(
                LuyenTap.this,
                R.layout.item_grid_view,
                dsGoiY);
        gvGoiY.setAdapter(gridAdapter);
        txtTenChuDe.setText(chuDe.getTen());
    }

    private void layDanhSachTuVung() {
        //get ds tu vung
        dsTuVung.clear();
        if(chuDe.getTen() == null)
        {
            dsTuVung.addAll(dbTuVung.getAllTuVung());
        }
        else{
            dsTuVung.addAll(dbTuVung.getListTuVungByChuDe(chuDe.getId()) );
        }
        COUNT = dsTuVung.size();
    }

    private void thucHienLaiTuVung() {
        //TODO:
        dsGoiY.clear();
        // dữ liệu demo
        String []arr = DATATU.split("");
        Collections.addAll(dsGoiY, arr);
        dsGoiY.remove(0);
        gridAdapter.notifyDataSetChanged();
        txtNghia.setText("");
        txtNghia.requestFocus();
        txtInform.setVisibility(View.INVISIBLE);
        btnLamLai.setVisibility(View.GONE);
        btnBoQua.setVisibility(View.VISIBLE);
        btnKiemTra.setVisibility(View.VISIBLE);
        btnTuTiep.setVisibility(View.GONE);
    }

    private void taoTuVung() {
        dsGoiY.clear();
        final int n;
        if(flagNgayNhien == true)
        {
            n = rand.nextInt(COUNT);
            DATATU = dsTuVung.get(n).getTuvung();
            NGHIA = dsTuVung.get(n).getNghia();
            index = n;
        }
        else {
            if(index == -1)
            {
                n = 0;
                tuVungTemp = dsTuVung.get(n);
            }
            else
            {
                n = index + 1;
                tuVungTemp = dsTuVung.get(n);
            }
            index += 1;
            DATATU = dsTuVung.get(n).getTuvung();
            NGHIA = dsTuVung.get(n).getNghia();
        }
        txtTuVung.setText(NGHIA);
        // dữ liệu demo
        String []arr = DATATU.split("");
        Collections.addAll(dsGoiY, arr);
        dsGoiY.remove(0);
        gridAdapter.notifyDataSetChanged();
        txtPhatAm.setText(dsTuVung.get(n).getLoaitu());
        txtNghia.setText("");
        txtNghia.requestFocus();
    }

}
