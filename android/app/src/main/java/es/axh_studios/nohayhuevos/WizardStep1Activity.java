package es.axh_studios.nohayhuevos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class WizardStep1Activity extends AppCompatActivity {

    private EditText descripcionEditText;
    private ImageButton siguienteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_step1);

        descripcionEditText = (EditText) findViewById(R.id.descripcion);
        siguienteButton = (ImageButton) findViewById(R.id.siguiente_paso_1);

        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descripcion = descripcionEditText.getText().toString();

                if(descripcion == null || descripcion.equals("")){
                    descripcionEditText.setError("La descripción no puede estar vacía");
                    return;
                }

                Intent i = new Intent();
                i.putExtra("descripcion", descripcion);

                i.setClass(WizardStep1Activity.this, WizardStep2Activity.class);
                startActivity(i);
            }
        });
    }
}
