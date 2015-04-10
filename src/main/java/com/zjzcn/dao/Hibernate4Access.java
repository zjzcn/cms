package com.zjzcn.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jdbc.Work;
import org.snaker.engine.DBAccess;
import org.snaker.engine.SnakerException;
import org.snaker.engine.access.ScriptRunner;
import org.snaker.engine.access.hibernate.HibernateAccess;
import org.snaker.engine.access.jdbc.JdbcHelper;

/**
 * hibernate4方式的数据库访问
 * @author yuqs
 * @since 2.0
 */
public class Hibernate4Access extends HibernateAccess implements DBAccess {
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 取得hibernate的connection对象
     */
    protected Connection getConnection() throws SQLException {
        if (sessionFactory instanceof SessionFactoryImpl) {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            ConnectionProvider provider = sessionFactoryImpl.getServiceRegistry().getService(ConnectionProvider.class);
            if(provider != null) return provider.getConnection();
        }
        return null;
    }

    public Blob createBlob(byte[] bytes) {
        return getSession().getLobHelper().createBlob(bytes);
    }

    public void runScript() {
        getSession().doWork(new Work() {
            public void execute(Connection conn) throws SQLException {
                if(JdbcHelper.isExec(conn)) {
                    return;
                }
                try {
                    String databaseType = JdbcHelper.getDatabaseType(conn);
                    String schema = "db/core/schema-" + databaseType + ".sql";
                    ScriptRunner runner = new ScriptRunner(conn, true);
                    runner.runScript(schema);
                } catch (Exception e) {
                    throw new SnakerException(e);
                }
            }
        });
    }
}
