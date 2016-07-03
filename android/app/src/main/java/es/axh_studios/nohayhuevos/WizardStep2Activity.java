package es.axh_studios.nohayhuevos;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.axh_studios.nohayhuevos.application.PikeApplication;
import es.axh_studios.nohayhuevos.domain.Apuesta;
import es.axh_studios.nohayhuevos.domain.Usuario;
import es.axh_studios.nohayhuevos.service.impl.PikeServiceImpl;

public class WizardStep2Activity extends AppCompatActivity {

    private EditText amountEditText;
    private Button generarPike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_step2);

        Intent i = getIntent();
        final String descripcion = i.getExtras().getString("descripcion");

        amountEditText = (EditText) findViewById(R.id.cantidad);
        generarPike = (Button) findViewById(R.id.pikate);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        PikeApplication application = (PikeApplication) getApplication();
        final Usuario usuarioConectado = application.getUsuarioConectado();

        generarPike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double cantidad = null;

                try{
                    cantidad = new Double(amountEditText.getText().toString());
                } catch (Exception e){

                }

                if(cantidad == null){
                    amountEditText.setError("La cantidad no puede estar vacía");
                    return;
                }

                PikeServiceImpl pikeService = new PikeServiceImpl(usuarioConectado);

                Apuesta ap = new Apuesta();
                ap.setDescripcion(descripcion);
                ap.setCantidad(cantidad);

                Integer pike = pikeService.crearApuesta(ap, "test");

                if(pike == null){
                    return;
                }

                Intent i = new Intent();
                i.setClass(WizardStep2Activity.this, PikeDetailsActivity.class);
                i.putExtra("idPike", pike);
                startActivity(i);

            }
        });
    }
}
