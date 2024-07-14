package com.example.fptdictionary.data;

import android.app.Activity;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


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


}
