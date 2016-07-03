package es.axh_studios.nohayhuevos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import es.axh_studios.nohayhuevos.application.PikeApplication;
import es.axh_studios.nohayhuevos.domain.Apuesta;
import es.axh_studios.nohayhuevos.domain.Usuario;
import es.axh_studios.nohayhuevos.service.impl.PikeServiceImpl;

public class WizardStep3Activity extends AppCompatActivity {

    private EditText apuestaEditText;
    private ImageButton generarPike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_step3);

        Intent i = getIntent();
        final String descripcion = i.getExtras().getString("descripcion");
        final Double cantidad = i.getExtras().getDouble("cantidad");


        apuestaEditText = (EditText) findViewById(R.id.apuesta);
        generarPike = (ImageButton) findViewById(R.id.pikate);

        PikeApplication application = (PikeApplication) getApplication();
        final Usuario usuarioConectado = application.getUsuarioConectado();

        generarPike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apuesta = apuestaEditText.getText().toString();


                if(apuesta == null){
                    apuestaEditText.setError("La apuesta no puede estar vacía");
                    return;
                }

                PikeServiceImpl pikeService = new PikeServiceImpl(usuarioConectado);

                Apuesta ap = new Apuesta();
                ap.setDescripcion(descripcion);
                ap.setCantidad(cantidad);

                Integer pike = pikeService.crearApuesta(ap, apuesta);

                if(pike == null){
                    return;
                }

                Intent i = new Intent();
                i.setClass(WizardStep3Activity.this, PikeDetailsActivity.class);
                i.putExtra("idPike", pike);
                startActivity(i);

            }
        });
    }
}
