package es.axh_studios.nohayhuevos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import es.axh_studios.nohayhuevos.application.PikeApplication;
import es.axh_studios.nohayhuevos.domain.Usuario;

public class WizardStep2Activity extends AppCompatActivity {

    private EditText amountEditText;
    private ImageButton generarPike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_step2);

        Intent i = getIntent();
        final String descripcion = i.getExtras().getString("descripcion");

        amountEditText = (EditText) findViewById(R.id.cantidad);
        generarPike = (ImageButton) findViewById(R.id.siguiente_paso_2);

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

                Intent i = new Intent();
                i.putExtra("descripcion", descripcion);
                i.putExtra("cantidad", cantidad);

                i.setClass(WizardStep2Activity.this, WizardStep3Activity.class);
                startActivity(i);

            }
        });
    }
}
