package py.edu.facitec.taskapp;

import java.sql.SQLException;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.oss.datahelper.DataBaseManager;

public class DBA {
	private static final String DB_NAME = "agenda_db.sqlite";
	private static final int DB_VERSION = 1;
	
	public static void init(Context context){
		DataBaseManager.init(context, DB_NAME, DB_VERSION);
		
		ConnectionSource source = DataBaseManager.getInstance()
												 .getHelper()
												 .getConnectionSource();
		try {
			TableUtils.createTableIfNotExists(source, Tarea.class);
			//Demas tablas
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Dao<Tarea, Integer> getTareaDao(){
		try {
			return DataBaseManager.getInstance()
								  .getHelper()
								  .getDao(Tarea.class);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
