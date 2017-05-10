package com.baseactiviti.dao;

import org.springframework.stereotype.Repository;

/**
 * @author zhailz
 *
 *         时间：2017年4月10日 ### 下午2:28:56
 */
@Repository
public class ActivitiBasicDataSource extends org.apache.commons.dbcp.BasicDataSource {

  private int maxActive = 200;// 最大连接数量
  private int maxIdle = 20;// 最大空闲连接
  private int minIdle = 20;// 最小空闲连接
  private int maxWait = 30000;// 超时等待时间以毫秒为单位 1000毫秒等于1秒
  private int initialSize = 10;// 初始化连接
  private boolean removeAbandoned = true;// 是否自动回收超时连接
  private int removeAbandonedTimeout = 120;// 超时时间(以秒数为单位)
  private boolean logAbandoned = true;// 是否在自动回收超时连接的时候打印连接的超时错误

  private boolean testOnBorrow = false;
  // testWhileIdle会定时校验numTestsPerEvictionRun个连接，只要发现连接失效，就将其移除再重新创建。
  private boolean testWhileIdle = false;
  private int numTestsPerEvictionRun = 200;//
  private long timeBetweenEvictionRunsMillis = 30000L;//
  private long minEvictableIdleTimeMillis = 1800000L;//

  public ActivitiBasicDataSource() {
    ini();
  }

  private void ini() {
    inipg();
  }

  private void inipg() {
    setDriverClassName("com.mysql.jdbc.Driver");
    setUrl("jdbc:mysql://localhost:3306/activiti");
    setUsername("root");
    setPassword("root");
    setDefaultAutoCommit(false);
    setMaxActive(this.maxActive);// 最大连接数量
    setMaxIdle(this.maxIdle);// 最大空闲连接
    setMinIdle(this.minIdle);// 最小空闲连接
    setMaxWait(this.maxWait);// 超时等待时间以毫秒为单位 1000等于60秒
    setInitialSize(this.initialSize);// 初始化连接
    setRemoveAbandoned(this.removeAbandoned);
    setRemoveAbandonedTimeout(this.removeAbandonedTimeout);
    setTestOnBorrow(this.testOnBorrow);
    setLogAbandoned(this.logAbandoned);
    setTestWhileIdle(this.testWhileIdle);
    setNumTestsPerEvictionRun(this.numTestsPerEvictionRun);
    setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
    setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
  }
}
