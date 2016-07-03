package es.axh_studios.nohayhuevos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.axh_studios.nohayhuevos.ApuestaFragment.OnListFragmentInteractionListener;
import es.axh_studios.nohayhuevos.domain.Apuesta;
import es.axh_studios.nohayhuevos.domain.Participacion;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Apuesta} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ParticipantesRecyclerViewAdapter extends RecyclerView.Adapter<ParticipantesRecyclerViewAdapter.ViewHolder> {

    private final List<Participacion> mValues;
    private final Context context;

    public ParticipantesRecyclerViewAdapter(List<Participacion> items, Context c) {
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
        holder.textoPrincipal.setText(mValues.get(position).getValor());
        holder.comentario.setText(mValues.get(position).getNombreUsuario());
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
