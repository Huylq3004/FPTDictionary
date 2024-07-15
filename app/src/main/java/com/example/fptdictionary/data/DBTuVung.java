package com.example.fptdictionary.data;

import android.app.Activity;

import android.content.Context;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.fptdictionary.model.TuVung;

import java.util.ArrayList;


public class DBTuVung extends SQLiteOpenHelper{

    private static int version = 1;
    private static final String DATABASE_NAME ="dbtuvung";
    private static final String TABLE_TU_VUNG ="tuvung";
    private static final String TuVung_ID ="id";
    private static final String TuVung_tuvung ="tuvung";
    private static final String TuVung_nghia ="nghia";
    private static final String TuVung_phatam ="phatam";
    private static final String TuVung_loaitu ="loaitu";

    private static final String TABLE_CHU_DE ="chude";
    private static final String ChuDe_ID ="id";
    private static final String ChuDe_TEN ="ten";
    private static final String ChuDe_MOTA ="mota";

    private static final String TABLE_TUVUNG_CHUDE ="tuvungchude";
    private static final String TuVungChuDe_TuVung ="tuvung_id";
    private static final String TuVungChuDe_ChuDe ="chude_id";
    private static SQLiteDatabase db = null;

    private Activity context;
    public DBTuVung(Activity context) {
        super(context, DATABASE_NAME, null, version);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = this.context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db = this.context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }


    public ArrayList<TuVung> getAllTuVung(){
        db = this.context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        Cursor cursor = db.query(TABLE_TU_VUNG, null, null,
                null, null, null, null, null);
        ArrayList<TuVung> dsTuVung = new ArrayList<TuVung>();
        while (cursor.moveToNext())
        {
            TuVung tuVung = new TuVung(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            dsTuVung.add(tuVung);
        }
        cursor.close();
        db.close();
        return dsTuVung;
    }

    //Get list TuVung by ChuDe
    public ArrayList<TuVung> getListTuVungByChuDe(int chude_id){
        db = this.context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery(
                "SELECT * FROM tuvung where id in (select tuvung_id from tuvungchude where chude_id = " + chude_id +" ) ",null);
        ArrayList<TuVung> dsTuVung = new ArrayList<>();
        while (cursor.moveToNext())
        {
            TuVung tuVung = new TuVung(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            dsTuVung.add(tuVung);
        }
        cursor.close();
        db.close();
        return dsTuVung;
    }


}
