package com.someco.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.someco.BookCrudApplication;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author ikarmatsky
 *
 */
@Configuration
@EnableJpaRepositories ("com.someco.repository")
@EnableTransactionManagement
public class JpaConfig implements TransactionManagementConfigurer{

	@Value("${dataSource.url}")
	private String jdbcUrl;
	@Value("${dataSource.driver}")
	private String driverClassName;
	@Value("${dataSource.username}")
	private String username;
	@Value("${dataSource.password}")
	private String password;
	@Value("${hibernate.dialect}")
	private String dialect;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddl;
	
	@Override
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new JpaTransactionManager();
	}
	
	@Bean ("dataSource")
	public DataSource dataSource(){
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(jdbcUrl);
		config.setDriverClassName(driverClassName);
		config.setUsername(username);
		config.setPassword(password);
		return new HikariDataSource(config);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory (){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPackagesToScan("com.someco.entity");
		
		Properties jpaProps = new Properties();
		jpaProps.put(org.hibernate.cfg.Environment.DIALECT, dialect);
		jpaProps.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddl);
		jpaProps.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
		
		emf.setJpaProperties(jpaProps);
		
		return emf;
	}

}
