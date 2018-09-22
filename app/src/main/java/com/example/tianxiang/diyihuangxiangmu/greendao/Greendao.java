package com.example.tianxiang.diyihuangxiangmu.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Greendao {
    private Greendao(){

    }
    private static DaoSession mDaoSession;
    public static DaoSession getmDaoSession(Context context){

        if (mDaoSession==null){
            synchronized (Greendao.class){
                if (mDaoSession==null){
                    DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(context,"tian-db",null);
                    SQLiteDatabase db = helper.getWritableDatabase();

                    mDaoSession=new DaoMaster(db).newSession();
                }
            }
        }
        return mDaoSession;
    }
}
