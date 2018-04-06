package com.liwenpeng.topnews.bean.table_top;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.liwenpeng.topnews.bean.table_top.GDBean;
import com.liwenpeng.topnews.bean.table_shehui.SocialBean;
import com.liwenpeng.topnews.bean.table_guonei.GuoNeiBean;

import com.liwenpeng.topnews.bean.table_top.GDBeanDao;
import com.liwenpeng.topnews.bean.table_shehui.SocialBeanDao;
import com.liwenpeng.topnews.bean.table_guonei.GuoNeiBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig gDBeanDaoConfig;
    private final DaoConfig socialBeanDaoConfig;
    private final DaoConfig guoNeiBeanDaoConfig;

    private final GDBeanDao gDBeanDao;
    private final SocialBeanDao socialBeanDao;
    private final GuoNeiBeanDao guoNeiBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        gDBeanDaoConfig = daoConfigMap.get(GDBeanDao.class).clone();
        gDBeanDaoConfig.initIdentityScope(type);

        socialBeanDaoConfig = daoConfigMap.get(SocialBeanDao.class).clone();
        socialBeanDaoConfig.initIdentityScope(type);

        guoNeiBeanDaoConfig = daoConfigMap.get(GuoNeiBeanDao.class).clone();
        guoNeiBeanDaoConfig.initIdentityScope(type);

        gDBeanDao = new GDBeanDao(gDBeanDaoConfig, this);
        socialBeanDao = new SocialBeanDao(socialBeanDaoConfig, this);
        guoNeiBeanDao = new GuoNeiBeanDao(guoNeiBeanDaoConfig, this);

        registerDao(GDBean.class, gDBeanDao);
        registerDao(SocialBean.class, socialBeanDao);
        registerDao(GuoNeiBean.class, guoNeiBeanDao);
    }
    
    public void clear() {
        gDBeanDaoConfig.clearIdentityScope();
        socialBeanDaoConfig.clearIdentityScope();
        guoNeiBeanDaoConfig.clearIdentityScope();
    }

    public GDBeanDao getGDBeanDao() {
        return gDBeanDao;
    }

    public SocialBeanDao getSocialBeanDao() {
        return socialBeanDao;
    }

    public GuoNeiBeanDao getGuoNeiBeanDao() {
        return guoNeiBeanDao;
    }

}
