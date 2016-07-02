package es.axh_studios.nohayhuevos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import es.axh_studios.nohayhuevos.application.PikeApplication;
import es.axh_studios.nohayhuevos.domain.Apuesta;
import es.axh_studios.nohayhuevos.service.impl.PikeServiceImpl;

public class PikeDetailsActivity extends AppCompatActivity {

    private final Apuesta apuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pike_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        try{
            Integer idPike = this.getIntent().getExtras().getInt("idPike");
            PikeServiceImpl pikeService = new PikeServiceImpl();

            PikeApplication application = new PikeApplication();

            //pikeService.findPike(idPike, idUsuario);

        } catch (Exception e){
            Intent i = new Intent();
            i.setClass(this, MainActivity.class);
            startActivity(i);
        }


    }
}
