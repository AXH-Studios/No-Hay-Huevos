package es.axh_studios.nohayhuevos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.axh_studios.nohayhuevos.adapter.ParticipantesRecyclerViewAdapter;
import es.axh_studios.nohayhuevos.application.PikeApplication;
import es.axh_studios.nohayhuevos.domain.Apuesta;
import es.axh_studios.nohayhuevos.domain.Participacion;
import es.axh_studios.nohayhuevos.domain.Usuario;
import es.axh_studios.nohayhuevos.service.impl.PikeServiceImpl;
import es.axh_studios.nohayhuevos.service.impl.UserServiceImpl;

public class PikeDetailsActivity extends AppCompatActivity {

    private final Context context = this;

    private Apuesta apuesta;

    private TextView amountTextView;
    private RecyclerView participantesRecyclerView;
    private ImageButton participarButton;
    private Integer idPike;

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
        idPike = null;

        try{
            Uri data = intent.getData();
            List<String> params = data.getPathSegments();
            idPike = new Integer(params.get(1));

            // TODO Get intent filter
        }catch (Exception e){

        }
        if(idPike == null || idPike.equals(0)){
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
        participantesRecyclerView = (RecyclerView) findViewById(R.id.participantes);
        participarButton = (ImageButton) findViewById(R.id.participar);

        List<Participacion> participaciones = apuesta.getParticipaciones();
        //List<Participacion> participacionesString = new ArrayList<>();


        Map<Integer, String> mapaUsuarios = new HashMap<>();

        UserServiceImpl userService = new UserServiceImpl();

        boolean botonCerrarApuestaVisible = apuesta.getIdOwner().equals(usuarioConectado.getId());

        if(participaciones != null){
            for(Participacion p : participaciones){
                // Add nombres de usuario

                Integer id = p.getUser();
                if(mapaUsuarios.containsKey(id)){
                   p.setNombreUsuario(mapaUsuarios.get(id));
                } else {
                    Usuario u = userService.getUser(id.toString());

                    p.setNombreUsuario(u.getNombre());
                    mapaUsuarios.put(id, u.getNombre());
                }
            }
        }


        setTitle(apuesta.getDescripcion());
        amountTextView.setText(apuesta.getCantidad() + " €");

        participantesRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        participantesRecyclerView.setLayoutManager(mLayoutManager);

        if(apuesta.getEstado().equals("finished")){
            participarButton.setVisibility(View.GONE);
        }

        ParticipantesRecyclerViewAdapter adapter;
        adapter = new ParticipantesRecyclerViewAdapter(participaciones, this, usuarioConectado, apuesta, botonCerrarApuestaVisible);
        participantesRecyclerView.setAdapter(adapter);

        participarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.activity_wizard_step3, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.apuesta);

                final RelativeLayout relative = (RelativeLayout) promptsView
                        .findViewById(R.id.relative);
                relative.setVisibility(View.GONE);


                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        PikeServiceImpl pikeService = new PikeServiceImpl(usuarioConectado);

                                        String usuario = userInput.getText().toString();
                                        pikeService.participarApuesta(idPike, usuario);

                                        Intent i = new Intent();
                                        i.setClass(context, PikeDetailsActivity.class);
                                        i.putExtra("idPike", idPike);

                                        Toast.makeText(context, "PIKADO!",
                                                Toast.LENGTH_LONG).show();

                                        finish();
                                        context.startActivity(i);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }
}
