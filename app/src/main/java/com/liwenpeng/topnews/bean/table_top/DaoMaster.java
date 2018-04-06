package com.liwenpeng.topnews.bean.table_top;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

import com.liwenpeng.topnews.bean.table_shehui.SocialBeanDao;
import com.liwenpeng.topnews.bean.table_guonei.GuoNeiBeanDao;
import com.liwenpeng.topnews.bean.table_guoji.GuoJiBeanDao;
import com.liwenpeng.topnews.bean.table_yule.YuleBeanDao;
import com.liwenpeng.topnews.bean.table_tiyu.TiYuBeanDao;
import com.liwenpeng.topnews.bean.table_caijing.CaiJingBeanDao;
import com.liwenpeng.topnews.bean.table_junshi.JunShiBeanDao;
import com.liwenpeng.topnews.bean.table_keji.KeJiBeanDao;
import com.liwenpeng.topnews.bean.table_shishang.ShiShangBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 4): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 4;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        GDBeanDao.createTable(db, ifNotExists);
        SocialBeanDao.createTable(db, ifNotExists);
        GuoNeiBeanDao.createTable(db, ifNotExists);
        GuoJiBeanDao.createTable(db, ifNotExists);
        YuleBeanDao.createTable(db, ifNotExists);
        TiYuBeanDao.createTable(db, ifNotExists);
        CaiJingBeanDao.createTable(db, ifNotExists);
        JunShiBeanDao.createTable(db, ifNotExists);
        KeJiBeanDao.createTable(db, ifNotExists);
        ShiShangBeanDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        GDBeanDao.dropTable(db, ifExists);
        SocialBeanDao.dropTable(db, ifExists);
        GuoNeiBeanDao.dropTable(db, ifExists);
        GuoJiBeanDao.dropTable(db, ifExists);
        YuleBeanDao.dropTable(db, ifExists);
        TiYuBeanDao.dropTable(db, ifExists);
        CaiJingBeanDao.dropTable(db, ifExists);
        JunShiBeanDao.dropTable(db, ifExists);
        KeJiBeanDao.dropTable(db, ifExists);
        ShiShangBeanDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(GDBeanDao.class);
        registerDaoClass(SocialBeanDao.class);
        registerDaoClass(GuoNeiBeanDao.class);
        registerDaoClass(GuoJiBeanDao.class);
        registerDaoClass(YuleBeanDao.class);
        registerDaoClass(TiYuBeanDao.class);
        registerDaoClass(CaiJingBeanDao.class);
        registerDaoClass(JunShiBeanDao.class);
        registerDaoClass(KeJiBeanDao.class);
        registerDaoClass(ShiShangBeanDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}
