package app.jcl.contaxts.activities;

/**
 * Created by Juan Carlos Leto on 17/10/2017.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import app.jcl.contaxts.R;
import app.jcl.contaxts.utils.CheckBDD;
import app.jcl.contaxts.utils.Contacto;

public class ActivityListar extends AppCompatActivity {

    ArrayList<Contacto> contactos = null;
    private ListView listViewListadoDeContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00A8C1"));
        this.getSupportActionBar().setBackgroundDrawable(colorDrawable);


        listViewListadoDeContactos = (ListView) findViewById(R.id.lvContactos);

        CheckBDD gestorDeContacto = new CheckBDD(getApplicationContext());
        this.contactos = gestorDeContacto.listar();

        if (this.contactos == null)
        {
            Toast.makeText(this,"No hay contactos para mostrar", Toast.LENGTH_SHORT).show();
        }
        else
        {
            final ArrayAdapter<Contacto> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,this.contactos);
            listViewListadoDeContactos.setAdapter(adapter);


            listViewListadoDeContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Contacto c = adapter.getItem(position);
                    lanzarVerContacto(null,c);
                }
            });
        }
    }

    public void lanzarVerContacto(View v, Contacto c) {
        Intent t= new Intent(this,ActivityVer.class);
        t.putExtra("id", c.getId());
        t.putExtra("nombre", c.getNombre());
        t.putExtra("apellido", c.getApellido());
        t.putExtra("mail", c.getMail());
        t.putExtra("telefono", c.getTelefono());
        startActivity(t);
        this.finish();
    }

    public void vaciarBD (View v)
    {
        CheckBDD bd = new CheckBDD(getApplicationContext());
        bd.borrar_todos();
        Toast.makeText(this,"Se han borrado todos los contactos", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
