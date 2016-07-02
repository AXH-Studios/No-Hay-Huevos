package es.axh_studios.nohayhuevos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.axh_studios.nohayhuevos.service.impl.PikeServiceImpl;

public class WizardStep2Activity extends AppCompatActivity {

    private EditText amountEditText;
    private Button generarPike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_step2);

        Intent i = getIntent();
        String descripcion = i.getExtras().getString("descripcion");

        amountEditText = (EditText) findViewById(R.id.cantidad);
        generarPike = (Button) findViewById(R.id.pikate);

        generarPike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descripcion = amountEditText.getText().toString();

                if(descripcion == null || descripcion.equals("")){
                    amountEditText.setError("La cantidad no puede estar vacía");
                    return;
                }

                PikeServiceImpl pikeService = new PikeServiceImpl();

                // TODO Generar pike

                
            }
        });
    }
}
