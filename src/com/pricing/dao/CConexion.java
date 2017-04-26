package com.pricing.dao;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.pricing.util.EjecutorSQL;
public class CConexion 
{
	private DataSource ds;
    private EjecutorSQL ejec;
   // private DriverManagerDataSource dsmanager;
    private Boolean estaconectado;
   // private Connection conexion;
    
    public DataSource getDataSource(){    	
    	return ds;
    }
    public EjecutorSQL getEjecutorSQL(){
    	return ejec;
    }
    public CConexion(){    	
    	try {
    		//introduce las condiciones de la conexión.
//    		ApplicationContext ap=new ClassPathXmlApplicationContext("com/pricing_pat/resources/applicationContext.xml");
    		//Crea la instancia con el pool.
//    		pds=PoolDataSourceFactory.getPoolDataSource();
//    		pds=(PoolDataSource) ap.getBean("dataSource");
    		//Pripiedades del pool. 
//	        pds.setInitialPoolSize(5);
    		 PoolProperties p = new PoolProperties();
//             p.setUrl("jdbc:postgresql://45.56.120.138:5432/DBPricing_FPP");
             p.setUrl("jdbc:postgresql://localhost:5432/DBPricing_FPP");
             p.setDriverClassName("org.postgresql.Driver");
             p.setUsername("postgres");
//             p.setPassword("LAnube2016@db");
             p.setPassword("12345");
             p.setJmxEnabled(true);
             p.setTestWhileIdle(false);
             p.setTestOnBorrow(true);
             p.setValidationQuery("SELECT 1");
             p.setTestOnReturn(false);
             p.setValidationInterval(30000);
             p.setTimeBetweenEvictionRunsMillis(30000);
             p.setMaxActive(100);
             p.setInitialSize(20);
             p.setMaxWait(10000);
             p.setRemoveAbandonedTimeout(60);
             p.setMinEvictableIdleTimeMillis(30000);
             p.setMinIdle(10);
//             p.setLogAbandoned(true);
//             p.setRemoveAbandoned(true);
             p.setJdbcInterceptors(
               "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
               "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
             ds = new DataSource();
             ds.setPoolProperties(p);
            ejec=EjecutorSQL.getEjecutorSQL(ds);
		} catch (Exception e){
			// TODO: handle exception
			estaconectado=false;
			System.out.println(e.toString());
		}
    }
	public Boolean getEstaconectado() {
		return estaconectado;
	}
    public List ejecutarProcedimiento(String procedimiento,String[] values){
    	return getEjecutorSQL().ejecutarProcedimiento(procedimiento, values);
    }
	
}
