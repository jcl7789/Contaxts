package app.jcl.contaxts.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.jcl.contaxts.R;
import app.jcl.contaxts.utils.CheckBDD;

public class ActivityAgregar extends AppCompatActivity {

    private TextView tvTextoId;
    private TextView tvTextoNom;
    private TextView tvTextoApe;
    private TextView tvTextoMail;
    private TextView tvTextoTel;
    private Button bAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00A8C1"));
        this.getSupportActionBar().setBackgroundDrawable(colorDrawable);

        tvTextoNom = (TextView) findViewById(R.id.tfNombre);
        tvTextoApe = (TextView) findViewById(R.id.tfApellido);
        tvTextoMail = (TextView) findViewById(R.id.tfMail);
        tvTextoTel = (TextView) findViewById(R.id.tfTelefono);
        bAgregar = (Button) findViewById(R.id.buttonAgregar);

    }


    public void agregar(View view) {
        CheckBDD bd = new CheckBDD(getApplicationContext());
        bd.insertar(tvTextoNom.getText().toString(), tvTextoApe.getText().toString(), tvTextoMail.getText().toString(), tvTextoTel.getText().toString());
        Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT).show();

        this.finish();
    }
}
