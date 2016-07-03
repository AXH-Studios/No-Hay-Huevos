package es.axh_studios.nohayhuevos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import es.axh_studios.nohayhuevos.application.PikeApplication;
import es.axh_studios.nohayhuevos.domain.Apuesta;
import es.axh_studios.nohayhuevos.domain.Participacion;
import es.axh_studios.nohayhuevos.domain.Usuario;
import es.axh_studios.nohayhuevos.service.impl.PikeServiceImpl;

public class PikeDetailsActivity extends AppCompatActivity {

    private Apuesta apuesta;

    private TextView amountTextView;
    private TextView descripcionTextView;
    private ListView participantesListView;
    private Button participarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pike_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PikeApplication application = (PikeApplication) getApplication();
        final Usuario usuarioConectado = application.getUsuarioConectado();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PikeServiceImpl pikeService = new PikeServiceImpl(usuarioConectado);

                String url = pikeService.generarUrlCompartir(apuesta.getId());

                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                share.putExtra(Intent.EXTRA_SUBJECT, "URL del pike");
                share.putExtra(Intent.EXTRA_TEXT, url);

                startActivity(Intent.createChooser(share, "Comparte tu pike!"));
            }
        });

        Intent intent = getIntent();
        Integer idPike = null;

        try{
            Uri data = intent.getData();
            String param = data.getQueryParameter("id");
            idPike = new Integer(param); // "1234"

            // TODO Get intent filter
        }catch (Exception e){

        }
        if(idPike == null){
            try{
                idPike = intent.getExtras().getInt("idPike");
            } catch (Exception e){}
        }

        if(idPike != null){
            PikeServiceImpl pikeService = new PikeServiceImpl(usuarioConectado);

            apuesta = pikeService.findPike(idPike);
        }



        if(apuesta == null){
            Intent i = new Intent();

            if(usuarioConectado == null){
                i.setClass(this, LoginActivity.class);
            } else {
                i.setClass(this, MainActivity.class);
            }
            finish();
            startActivity(i);
        }

        amountTextView = (TextView) findViewById(R.id.amount);
        descripcionTextView = (TextView) findViewById(R.id.descripcion);
        participantesListView = (ListView) findViewById(R.id.participantes);
        participarButton = (Button) findViewById(R.id.participar);

        List<Participacion> participaciones = apuesta.getParticipaciones();

        boolean botonVisible = false;
        if(participaciones != null){
            for(Participacion p : participaciones){
                // TODO Get usuario conectado y comprobar con usuario participante
            }
        }

        if(!botonVisible){
            participarButton.setVisibility(View.GONE);
        }

        amountTextView.setText(apuesta.getCantidad().toString());
        descripcionTextView.setText(apuesta.getDescripcion());

        // TODO Adapter listview


        participarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }
}
