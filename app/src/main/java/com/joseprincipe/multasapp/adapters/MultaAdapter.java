package com.joseprincipe.multasapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.joseprincipe.multasapp.MultaFormActivity;
import com.joseprincipe.multasapp.R;
import com.joseprincipe.multasapp.daos.MultaDAO;
import com.joseprincipe.multasapp.models.Multa;
import java.util.List;

public class MultaAdapter extends ArrayAdapter {
    public MultaAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final Multa multa = (Multa) getItem(position);


        TextView txtPlaca = view.findViewById(R.id.txtPlaca);
        TextView txtTipo = view.findViewById(R.id.txtTipo);
        TextView txtCausa = view.findViewById(R.id.txtCausa);
        txtPlaca.setText(multa.getPlaca() + (multa.isFijo() ? "(Fijo)" : ""));
        txtCausa.setText(multa.getCausa());
        txtTipo.setText(multa.getTipo());
        ImageButton btnDelete = view.findViewById(R.id.btnEliminar);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultaDAO multaDAO = new MultaDAO(getContext());
                multaDAO.delete(multa.getId());
                actualizar();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MultaFormActivity.class);
                intent.putExtra("id", multa.getId());
                getContext().startActivity(intent);
            }
        });


        return view;
    }

    private void actualizar(){
        this.clear();
        this.addAll(new MultaDAO(getContext()).all());
        this.notifyDataSetChanged();
    }
}