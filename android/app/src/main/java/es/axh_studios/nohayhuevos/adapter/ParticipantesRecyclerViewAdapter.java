package es.axh_studios.nohayhuevos.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.axh_studios.nohayhuevos.ApuestaFragment.OnListFragmentInteractionListener;
import es.axh_studios.nohayhuevos.PikeDetailsActivity;
import es.axh_studios.nohayhuevos.R;
import es.axh_studios.nohayhuevos.domain.Apuesta;
import es.axh_studios.nohayhuevos.domain.Participacion;
import es.axh_studios.nohayhuevos.domain.Usuario;
import es.axh_studios.nohayhuevos.service.impl.PikeServiceImpl;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Apuesta} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ParticipantesRecyclerViewAdapter extends RecyclerView.Adapter<ParticipantesRecyclerViewAdapter.ViewHolder> {

    private final List<Participacion> mValues;
    private final Usuario usuarioConectado;
    private final Context context;
    private final Boolean botonCerrarApuestaVisible;
    private final Apuesta pike;

    public ParticipantesRecyclerViewAdapter(List<Participacion> items, Context c, Usuario usuario, Apuesta pike, Boolean botonCerrarApuestaVisible) {
        mValues = items;
        usuarioConectado = usuario;
        this.pike = pike;
        this.botonCerrarApuestaVisible = botonCerrarApuestaVisible;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textoPrincipal.setText(mValues.get(position).getValor());
        holder.comentario.setText(mValues.get(position).getNombreUsuario());

        if(holder.mItem.getUser().equals(pike.getIdWinner())){
            holder.mView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            holder.textoPrincipal.setTextColor(context.getResources().getColor(android.R.color.white));
            holder.comentario.setTextColor(context.getResources().getColor(android.R.color.white));
        }

        if(botonCerrarApuestaVisible){


            holder.mView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("Finalizar apuesta");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Pulsa si para finalizar apuesta")
                            .setCancelable(false)
                            .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Participacion participacion = holder.mItem;

                                    PikeServiceImpl pikeService = new PikeServiceImpl(usuarioConectado);

                                    pikeService.resolverApuesta(pike.getId(), participacion.getEmail());

                                    Intent i = new Intent();
                                    i.setClass(context, PikeDetailsActivity.class);
                                    i.putExtra("idPike", pike.getId());

                                    ((Activity) context).finish();
                                    context.startActivity(i);
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                    return true;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textoPrincipal;
        public final TextView comentario;
        public Participacion mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textoPrincipal = (TextView) view.findViewById(android.R.id.text1);
            comentario = (TextView) view.findViewById(android.R.id.text2);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textoPrincipal.getText() + "'";
        }

    }
}
