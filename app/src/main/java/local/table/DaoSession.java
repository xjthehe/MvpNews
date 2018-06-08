package local.table;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig newsTypeInfoDaoConfig;

    private final NewsTypeInfoDao newsTypeInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        newsTypeInfoDaoConfig = daoConfigMap.get(NewsTypeInfoDao.class).clone();
        newsTypeInfoDaoConfig.initIdentityScope(type);

        newsTypeInfoDao = new NewsTypeInfoDao(newsTypeInfoDaoConfig, this);

        registerDao(NewsTypeInfo.class, newsTypeInfoDao);
    }
    
    public void clear() {
        newsTypeInfoDaoConfig.clearIdentityScope();
    }

    public NewsTypeInfoDao getNewsTypeInfoDao() {
        return newsTypeInfoDao;
    }

}