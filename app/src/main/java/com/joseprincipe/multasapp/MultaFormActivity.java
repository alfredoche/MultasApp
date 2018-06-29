package com.joseprincipe.multasapp;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.joseprincipe.multasapp.R;
import com.joseprincipe.multasapp.daos.MultaDAO;
import com.joseprincipe.multasapp.models.Multa;

public class MultaFormActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multa_form);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        if(id >= 0){
            MultaDAO multaDAO = new MultaDAO(this);
            Multa multa = multaDAO.get(id);
            TextView txtPlaca = (TextView) findViewById(R.id.txtPlaca);
            TextView txtTipo = (TextView) findViewById(R.id.txtTipo);
            TextView txtCausa= (TextView) findViewById(R.id.txtCausa);
            TextView txtDni= (TextView) findViewById(R.id.txtDni);
            TextView txtNombre= (TextView) findViewById(R.id.txtApellido);
            TextView txtMonto= (TextView) findViewById(R.id.txtMonto);

            txtPlaca.setText(multa.getPlaca());
            txtTipo.setText(multa.getTipo());
            txtCausa.setText(multa.getCausa());
            txtDni.setText(multa.getDni());
            txtNombre.setText(multa.getNombre());
            txtMonto.setText(multa.getMonto());

        }


    }

    @Override
    public void onClick(View view) {
        int id = getIntent().getIntExtra("id", -1);

        TextView txtPlaca = (TextView) findViewById(R.id.txtPlaca);
        TextView txtTipo = (TextView) findViewById(R.id.txtTipo);
        TextView txtCausa= (TextView) findViewById(R.id.txtCausa);
        TextView txtDni= (TextView) findViewById(R.id.txtDni);
        TextView txtNombre= (TextView) findViewById(R.id.txtApellido);
        TextView txtMonto= (TextView) findViewById(R.id.txtMonto);
        Multa multa = new Multa();
        multa.setId(id);
        multa.setPlaca(txtPlaca.getText().toString());
        multa.setTipo(txtTipo.getText().toString());
        multa.setCausa(txtCausa.getText().toString());
        multa.setDni(txtDni.getText().toString());
        multa.setNombre(txtNombre.getText().toString());
        multa.setMonto(txtMonto.getText().toString());
        MultaDAO multaDAO = new MultaDAO(this);
        multaDAO.save(multa);
        Log.d("ok", "Guardando.. ");
        finish();
    }
}
