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
import com.liwenpeng.topnews.bean.table_guoji.GuoJiBean;
import com.liwenpeng.topnews.bean.table_yule.YuleBean;
import com.liwenpeng.topnews.bean.table_tiyu.TiYuBean;
import com.liwenpeng.topnews.bean.table_caijing.CaiJingBean;
import com.liwenpeng.topnews.bean.table_junshi.JunShiBean;
import com.liwenpeng.topnews.bean.table_keji.KeJiBean;
import com.liwenpeng.topnews.bean.table_shishang.ShiShangBean;

import com.liwenpeng.topnews.bean.table_top.GDBeanDao;
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
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig gDBeanDaoConfig;
    private final DaoConfig socialBeanDaoConfig;
    private final DaoConfig guoNeiBeanDaoConfig;
    private final DaoConfig guoJiBeanDaoConfig;
    private final DaoConfig yuleBeanDaoConfig;
    private final DaoConfig tiYuBeanDaoConfig;
    private final DaoConfig caiJingBeanDaoConfig;
    private final DaoConfig junShiBeanDaoConfig;
    private final DaoConfig keJiBeanDaoConfig;
    private final DaoConfig shiShangBeanDaoConfig;

    private final GDBeanDao gDBeanDao;
    private final SocialBeanDao socialBeanDao;
    private final GuoNeiBeanDao guoNeiBeanDao;
    private final GuoJiBeanDao guoJiBeanDao;
    private final YuleBeanDao yuleBeanDao;
    private final TiYuBeanDao tiYuBeanDao;
    private final CaiJingBeanDao caiJingBeanDao;
    private final JunShiBeanDao junShiBeanDao;
    private final KeJiBeanDao keJiBeanDao;
    private final ShiShangBeanDao shiShangBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        gDBeanDaoConfig = daoConfigMap.get(GDBeanDao.class).clone();
        gDBeanDaoConfig.initIdentityScope(type);

        socialBeanDaoConfig = daoConfigMap.get(SocialBeanDao.class).clone();
        socialBeanDaoConfig.initIdentityScope(type);

        guoNeiBeanDaoConfig = daoConfigMap.get(GuoNeiBeanDao.class).clone();
        guoNeiBeanDaoConfig.initIdentityScope(type);

        guoJiBeanDaoConfig = daoConfigMap.get(GuoJiBeanDao.class).clone();
        guoJiBeanDaoConfig.initIdentityScope(type);

        yuleBeanDaoConfig = daoConfigMap.get(YuleBeanDao.class).clone();
        yuleBeanDaoConfig.initIdentityScope(type);

        tiYuBeanDaoConfig = daoConfigMap.get(TiYuBeanDao.class).clone();
        tiYuBeanDaoConfig.initIdentityScope(type);

        caiJingBeanDaoConfig = daoConfigMap.get(CaiJingBeanDao.class).clone();
        caiJingBeanDaoConfig.initIdentityScope(type);

        junShiBeanDaoConfig = daoConfigMap.get(JunShiBeanDao.class).clone();
        junShiBeanDaoConfig.initIdentityScope(type);

        keJiBeanDaoConfig = daoConfigMap.get(KeJiBeanDao.class).clone();
        keJiBeanDaoConfig.initIdentityScope(type);

        shiShangBeanDaoConfig = daoConfigMap.get(ShiShangBeanDao.class).clone();
        shiShangBeanDaoConfig.initIdentityScope(type);

        gDBeanDao = new GDBeanDao(gDBeanDaoConfig, this);
        socialBeanDao = new SocialBeanDao(socialBeanDaoConfig, this);
        guoNeiBeanDao = new GuoNeiBeanDao(guoNeiBeanDaoConfig, this);
        guoJiBeanDao = new GuoJiBeanDao(guoJiBeanDaoConfig, this);
        yuleBeanDao = new YuleBeanDao(yuleBeanDaoConfig, this);
        tiYuBeanDao = new TiYuBeanDao(tiYuBeanDaoConfig, this);
        caiJingBeanDao = new CaiJingBeanDao(caiJingBeanDaoConfig, this);
        junShiBeanDao = new JunShiBeanDao(junShiBeanDaoConfig, this);
        keJiBeanDao = new KeJiBeanDao(keJiBeanDaoConfig, this);
        shiShangBeanDao = new ShiShangBeanDao(shiShangBeanDaoConfig, this);

        registerDao(GDBean.class, gDBeanDao);
        registerDao(SocialBean.class, socialBeanDao);
        registerDao(GuoNeiBean.class, guoNeiBeanDao);
        registerDao(GuoJiBean.class, guoJiBeanDao);
        registerDao(YuleBean.class, yuleBeanDao);
        registerDao(TiYuBean.class, tiYuBeanDao);
        registerDao(CaiJingBean.class, caiJingBeanDao);
        registerDao(JunShiBean.class, junShiBeanDao);
        registerDao(KeJiBean.class, keJiBeanDao);
        registerDao(ShiShangBean.class, shiShangBeanDao);
    }
    
    public void clear() {
        gDBeanDaoConfig.clearIdentityScope();
        socialBeanDaoConfig.clearIdentityScope();
        guoNeiBeanDaoConfig.clearIdentityScope();
        guoJiBeanDaoConfig.clearIdentityScope();
        yuleBeanDaoConfig.clearIdentityScope();
        tiYuBeanDaoConfig.clearIdentityScope();
        caiJingBeanDaoConfig.clearIdentityScope();
        junShiBeanDaoConfig.clearIdentityScope();
        keJiBeanDaoConfig.clearIdentityScope();
        shiShangBeanDaoConfig.clearIdentityScope();
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

    public GuoJiBeanDao getGuoJiBeanDao() {
        return guoJiBeanDao;
    }

    public YuleBeanDao getYuleBeanDao() {
        return yuleBeanDao;
    }

    public TiYuBeanDao getTiYuBeanDao() {
        return tiYuBeanDao;
    }

    public CaiJingBeanDao getCaiJingBeanDao() {
        return caiJingBeanDao;
    }

    public JunShiBeanDao getJunShiBeanDao() {
        return junShiBeanDao;
    }

    public KeJiBeanDao getKeJiBeanDao() {
        return keJiBeanDao;
    }

    public ShiShangBeanDao getShiShangBeanDao() {
        return shiShangBeanDao;
    }

}