package app.jcl.contaxts;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import app.jcl.contaxts.activities.ActivityAgregar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#00a8c1"));
        getSupportActionBar().setBackgroundDrawable(cd);
    }

    public void lanzarAgregarContacto(View v){
        Intent t = new Intent(this, ActivityAgregar.class);
        startActivity(t);

    }
}
