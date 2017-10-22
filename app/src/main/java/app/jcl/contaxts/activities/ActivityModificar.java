package app.jcl.contaxts.activities;

/**
 * Created by Juan Carlos Leto on 17/10/2017.
 */

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.jcl.contaxts.R;
import app.jcl.contaxts.utils.CheckBDD;
import app.jcl.contaxts.utils.Contacto;

public class ActivityModificar extends AppCompatActivity {

    private TextView tvTextoNom;
    private TextView tvTextoApe;
    private TextView tvTextoMail;
    private TextView tvTextoTel;

    private int id;
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00A8C1"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        Bundle bundle = getIntent().getExtras();

        this.id = bundle.getInt("id");
        this.nombre = bundle.getString("nombre");
        this.apellido = bundle.getString("apellido");
        this.mail = bundle.getString("mail");
        this.telefono = bundle.getString("telefono");

        tvTextoNom=(TextView) findViewById(R.id.tfNombre);
        tvTextoApe=(TextView) findViewById(R.id.tfApellido);
        tvTextoMail=(TextView) findViewById(R.id.tfMail);
        tvTextoTel=(TextView) findViewById(R.id.tfTelefono);

        tvTextoNom.setText(this.nombre);
        tvTextoApe.setText(this.apellido);
        tvTextoMail.setText(this.mail);
        tvTextoTel.setText(this.telefono);
    }

    public void modificar (View view)
    {
        CheckBDD bd = new CheckBDD(getApplicationContext());
        Contacto co = new Contacto(this.id,tvTextoNom.getText().toString(),tvTextoApe.getText().toString(),tvTextoMail.getText().toString(),tvTextoTel.getText().toString());
        bd.modificar(co);
        Toast.makeText(this,"Contacto modificado",Toast.LENGTH_SHORT).show();
        this.finish();
    }
}

