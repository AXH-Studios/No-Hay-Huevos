package es.axh_studios.nohayhuevos.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.axh_studios.nohayhuevos.ApuestaFragment.OnListFragmentInteractionListener;
import es.axh_studios.nohayhuevos.PikeDetailsActivity;
import es.axh_studios.nohayhuevos.domain.Apuesta;

/**
 * {@link RecyclerView.Adapter} that can display a {@link es.axh_studios.nohayhuevos.domain.Apuesta} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ApuestaRecyclerViewAdapter extends RecyclerView.Adapter<ApuestaRecyclerViewAdapter.ViewHolder> {

    private final List<Apuesta> mValues;
    private final Context context;

    public ApuestaRecyclerViewAdapter(List<Apuesta> items, Context c) {
        mValues = items;
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

        Apuesta a = mValues.get(position);

        holder.textoPrincipal.setText(a.getDescripcion());

        String txt = a.getCantidad().toString();

        if(a.getParticipaciones() != null){
            txt += " / " + a.getParticipaciones().size();
        }

        holder.comentario.setText(txt);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = holder.getMITem().getId();

                Intent i = new Intent();
                i.setClass(context, PikeDetailsActivity.class);
                i.putExtra("idPike", id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textoPrincipal;
        public final TextView comentario;
        public Apuesta mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textoPrincipal = (TextView) view.findViewById(android.R.id.text1);
            comentario = (TextView) view.findViewById(android.R.id.text2);
        }

        public Apuesta getMITem(){
            return mItem;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textoPrincipal.getText() + "'";
        }
    }
}
