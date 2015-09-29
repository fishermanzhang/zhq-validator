package com.zhq;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy//AOP的切面功能
@EnableTransactionManagement
public class AppConfig {
	@Bean
	public DataSource getDataSource(){
		BasicDataSource ds=new BasicDataSource();
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setPassword("dt2015");
		ds.setUsername("dt2015");
		return ds;
	}
	@Bean
	public LocalSessionFactoryBean getSessionFactory(){
		LocalSessionFactoryBean lsfb=new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		Properties prop=new Properties();
		prop.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		prop.put("hibernate.show_sql", "true");
		lsfb.setHibernateProperties(prop);
		lsfb.setPackagesToScan("com.zhq.entity");
		return lsfb;
	}
	
	 @Bean
	    public HibernateTransactionManager getTM(){
	        HibernateTransactionManager tm = new HibernateTransactionManager();
	        tm.setSessionFactory(getSessionFactory().getObject());
	        return tm;
	    }
	
}
