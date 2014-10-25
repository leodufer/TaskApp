package py.edu.facitec.taskapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TareaAdapter extends BaseAdapter {
	
	private List<Tarea> tareas;
	private Context context;
	
	public TareaAdapter(List<Tarea> tareas, Context context){
		this.tareas = tareas;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return tareas.size();
	}

	@Override
	public Object getItem(int position) {
		
		return tareas.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return tareas.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		
		if(v==null){
			LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.item_tarea,null);
		}
		
		Tarea t = tareas.get(position);
		
		TextView textoTextView = (TextView) v.findViewById(R.id.textViewTexto);
		textoTextView.setText(t.getTexto());
		
		
		TextView fechaTextView = (TextView) v.findViewById(R.id.textViewFecha);
		fechaTextView.setText(t.getFecha().toString());
		
		return v;
	}

	
	
	
	
}
