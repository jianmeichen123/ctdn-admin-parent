package com.gi.ctdn.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class BaseDBConf {


    public final static int DEFAULT_INITIAL_SIZE = 0;
    public final static int DEFAULT_MAX_ACTIVE_SIZE = 8;
    public final static int DEFAULT_MAX_IDLE = 8;
    public final static int DEFAULT_MIN_IDLE = 0;
    public final static int DEFAULT_MAX_WAIT = -1;
    public final static String DEFAULT_VALIDATION_QUERY = null;                                                //
    public final static boolean DEFAULT_TEST_ON_BORROW = false;
    public final static boolean DEFAULT_TEST_ON_RETURN = false;
    public final static boolean DEFAULT_WHILE_IDLE = true;
    public static final long DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS = 60 * 1000L;
    public static final long DEFAULT_TIME_BETWEEN_CONNECT_ERROR_MILLIS = 30 * 1000;
    public static final int DEFAULT_NUM_TESTS_PER_EVICTION_RUN = 3;
    /**
     *
     */
    public static final long DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS = 1000L * 60L * 30L;
    private static final long serialVersionUID = 1L;
    protected volatile boolean defaultAutoCommit = true;
    protected volatile Boolean defaultReadOnly;
    protected volatile int defaultTransactionIsolation;
    protected volatile String defaultCatalog = null;

    protected String name;

    @Value("${spring.datasource.username}")
    protected volatile String username;
    @Value("${spring.datasource.password}")
    protected volatile String password;
    @Value("${spring.datasource.url}")
    protected volatile String jdbcUrl;
    @Value("${spring.datasource.driver-class-name}")
    protected volatile String driverClass;

    protected volatile ClassLoader driverClassLoader;
    protected volatile Properties connectProperties = new Properties();

    @Value("${spring.datasource.initialSize}")
    protected volatile int initialSize = DEFAULT_INITIAL_SIZE;
    @Value("${spring.datasource.maxActive}")
    protected volatile int maxActive = DEFAULT_MAX_ACTIVE_SIZE;
    @Value("${spring.datasource.minIdle}")
    protected volatile int minIdle = DEFAULT_MIN_IDLE;
    protected volatile int maxIdle = DEFAULT_MAX_IDLE;
    @Value("${spring.datasource.maxWait}")
    protected volatile long maxWait = DEFAULT_MAX_WAIT;
    protected int notFullTimeoutRetryCount = 0;

    @Value("${spring.datasource.validationQuery}")
    protected volatile String validationQuery = DEFAULT_VALIDATION_QUERY;
    protected volatile int validationQueryTimeout = -1;
    @Value("${spring.datasource.poolPreparedStatements}")
    protected volatile boolean poolPreparedStatements = false;
    protected volatile boolean sharePreparedStatements = false;
    protected volatile int maxPoolPreparedStatementPerConnectionSize = 10;
    protected volatile boolean inited = false;
    @Value("${spring.datasource.filters}")
    protected String filters ;
    protected volatile int queryTimeout;
    protected volatile int transactionQueryTimeout;
    protected long createTimespan;
    protected volatile int maxWaitThreadCount = -1;
    protected volatile boolean accessToUnderlyingConnectionAllowed = true;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    protected volatile long timeBetweenEvictionRunsMillis = DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
    protected volatile int numTestsPerEvictionRun = DEFAULT_NUM_TESTS_PER_EVICTION_RUN;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    protected volatile long minEvictableIdleTimeMillis = DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
    protected volatile boolean removeAbandoned;
    protected volatile long removeAbandonedTimeoutMillis = 300 * 1000;
    protected volatile boolean logAbandoned;
    @Value("${spring.datasource.maxOpenPreparedStatements}")
    protected volatile int maxOpenPreparedStatements = -1;
    protected volatile String dbType;
    protected volatile long timeBetweenConnectErrorMillis = DEFAULT_TIME_BETWEEN_CONNECT_ERROR_MILLIS;
    @Value("${spring.datasource.testOnBorrow}")
    private volatile boolean testOnBorrow = DEFAULT_TEST_ON_BORROW;
    @Value("${spring.datasource.testOnReturn}")
    private volatile boolean testOnReturn = DEFAULT_TEST_ON_RETURN;
    @Value("${spring.datasource.testWhileIdle}")
    private volatile boolean testWhileIdle = DEFAULT_WHILE_IDLE;
    private boolean clearFiltersEnable = true;





    @Bean(name = "dataSourceBase")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setFilters(filters);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        dataSource.setQueryTimeout(queryTimeout);
        dataSource.setTransactionQueryTimeout(transactionQueryTimeout);
        return dataSource;
    }
}
