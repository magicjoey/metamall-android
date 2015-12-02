package com.metamall.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <p>.</p>
 *
 * @author Wang Yi
 * @version account.java 1.0 Created@2015-11-23 19:34 $
 */
public class account extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Class";
    private static final int DATABASE_VERSION=1;
    public account(Context context){
        super(context,DATABASE_NAME,null,
        DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase ac){
        ac.execSQL("CREATE TABLE ACCOUNT("+"_id integer primary key,"+
                "username text no null,password text no null");
    }
    @Override
    public void onUpgrade(SQLiteDatabase ac,
                          int oldVersion,int newVersion){
        ac.execSQL("DROP TABLE TF EXISTS ACCOUNT");
        onCreate(ac);
    }
}




