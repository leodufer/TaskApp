package py.edu.facitec.taskapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	EditText tareaEditText;
	Button addButton;
	ListView tareasListView; 
	
	List<Tarea> tareas = new ArrayList<Tarea>();
	TareaAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tareaEditText = (EditText)findViewById(R.id.editext_tarea);
		addButton = (Button)findViewById(R.id.button_add);
		
		tareasListView = (ListView)findViewById(R.id.listView_tareas);
		//recuperar tareas de la bbdd
		DBA.init(getApplicationContext());
		
		try {
			tareas = DBA.getTareaDao().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		 adapter = new TareaAdapter(tareas, getApplicationContext());
		 tareasListView.setAdapter(adapter);
		
		
		
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String texto = tareaEditText.getText().toString();
				
				if(texto.length()>4){
					//TODO agregar
					Tarea t = new Tarea();
					t.setTexto(texto);
					t.setFecha(new Date());
					//tareas.add(t);
					try {
						DBA.getTareaDao().create(t);
						tareas = DBA.getTareaDao().queryForAll();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//volvemos a crear el adapter
					adapter = new TareaAdapter(tareas, getApplicationContext());
					tareasListView.setAdapter(adapter);
					
					tareaEditText.setText("");
				}else{
					//no agregar
					Toast.makeText(getApplicationContext(), 
							"La tarea debe tener mas de 4 caracteres", 
							Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		
	}

}
