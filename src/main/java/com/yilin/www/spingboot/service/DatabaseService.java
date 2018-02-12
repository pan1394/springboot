package com.yilin.www.spingboot.service;

 

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService extends JdbcDaoSupport {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	public void setDs(DataSource datasource) {
		super.setDataSource(datasource);
	}
	
	public Set<String> allTables() {  
		return this.getJdbcTemplate().execute(new ConnectionCallback<Set<String>>(){
			@Override
			public Set<String>  doInConnection(Connection con)
					throws SQLException, DataAccessException {
				
				/*try {
					Thread.sleep(100000);
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}*/
				DatabaseMetaData meta = con.getMetaData();  
				ResultSet res = meta.getTables(null, null, null, new String[]{"TABLE"});  
	            HashSet<String> set=new HashSet<String>();  
	            while (res.next()) {  
	                set.add(res.getString("TABLE_NAME"));  
	            }   
		        return set;  
			}
    	});
	}
	
}
