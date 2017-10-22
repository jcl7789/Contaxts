package app.jcl.contaxts.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.jcl.contaxts.R;
import app.jcl.contaxts.utils.CheckBDD;

public class ActivityVer extends AppCompatActivity {

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
        setContentView(R.layout.activity_ver);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00A8C1"));
        try {
            this.getSupportActionBar().setBackgroundDrawable(colorDrawable);
        }
        catch (NullPointerException e){
            this.getActionBar().setBackgroundDrawable(colorDrawable); //getSupportActionBar().setBackgroundDrawable(colorDrawable);
        }
        Bundle bundle = getIntent().getExtras();

        this.id = bundle.getInt("id");
        this.nombre = bundle.getString("nombre");
        this.apellido = bundle.getString("apellido");
        this.mail = bundle.getString("mail");
        this.telefono = bundle.getString("telefono");

        tvTextoNom=(TextView) findViewById(R.id.tvNombre);
        tvTextoApe=(TextView) findViewById(R.id.tvApellido);
        tvTextoMail=(TextView) findViewById(R.id.tvMail);
        tvTextoTel=(TextView) findViewById(R.id.tvTelefono);

        tvTextoNom.setText(this.nombre);
        tvTextoApe.setText(this.apellido);
        tvTextoMail.setText(this.mail);
        tvTextoTel.setText(this.telefono);

    }

    public void lanzarModificarContacto(View v) {
        Intent t= new Intent(this,ActivityModificar.class);
        t.putExtra("id", this.id);
        t.putExtra("nombre", this.nombre);
        t.putExtra("apellido", this.apellido);
        t.putExtra("mail", this.mail);
        t.putExtra("telefono", this.telefono);
        startActivity(t);
        this.finish();
    }

    public void borrarElContacto(View v) {
        CheckBDD bd = new CheckBDD(getApplicationContext());
        bd.borrar(this.id);
        Toast.makeText(this,"Contacto borrado", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void llamarPorTelefono(View view) {
        Intent tel = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+this.telefono));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(tel);


    }

    public void enviarMail(View view){
        Intent mail= new Intent(Intent.ACTION_SEND);
        mail.putExtra(Intent.EXTRA_EMAIL,new String[]{this.mail});
        //mail.putExtra(Intent.EXTRA_SUBJECT,"Hola Carlos");
        //mail.putExtra(Intent.EXTRA_TEXT,"Hola Carlos, como te va?");
        mail.setType("message/rfc822");
        //mail.putExtra(Intent.EXTRA_CC,new String[]{"monicakuhn2@yahoo.com.ar","carlosecimino@gmail.com"});
        //mail.putExtra(Intent.EXTRA_BCC,new String[]{"consultas@profmatiasgarcia.com.ar","carlosecimino@gmail.com"});
        //startActivity(Intent.createChooser(mail,"Elija un email cliente: "));
        startActivity(mail);
    }

    public void enviarWhatsApp(View view){
        Intent whatsApp=new Intent(Intent.ACTION_SEND);
        //whatsApp.putExtra(Intent.EXTRA_TEXT,"El texto que vas a enviar");
        whatsApp.setType("text/plain");
        whatsApp.setPackage("com.whatsapp");
        startActivity(whatsApp);
    }
}

