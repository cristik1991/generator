package com.cristik.code.database;

import com.cristik.code.database.dialect.mysql.Mysql;
import com.cristik.code.database.dialect.oracle.Oracle;
import com.cristik.code.database.dialect.postgresql.Postgresql;
import com.cristik.code.database.dialect.sqlserver.SqlServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cristik
 */
public class DataBaseFactory {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseFactory.class);

    public static DataBase getDataBase(DataBaseType dataBaseType) {
        logger.debug("use database type:{}", dataBaseType.name());
        switch (dataBaseType) {
            case MYSQL:
                return new Mysql();
            case ORACLE:
                return new Oracle();
            case SQLSERVER:
                return new SqlServer();
            case POSTGRESQL:
                return new Postgresql();
            default:
                return null;
        }
    }

    public enum DataBaseType {
        MYSQL,
        ORACLE,
        POSTGRESQL,
        SQLSERVER,;
    }

}
