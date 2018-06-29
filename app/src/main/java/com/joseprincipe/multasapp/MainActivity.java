package com.joseprincipe.multasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.joseprincipe.multasapp.daos.MultaDAO;
import com.joseprincipe.multasapp.adapters.MultaAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btnNuevaMulta);

        button.setOnClickListener(this);
        MultaDAO multaDAO = new MultaDAO(this);
        MultaAdapter adapter = new MultaAdapter(this, multaDAO.all());
        ListView listView = (ListView) findViewById(R.id.listViewMultas);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = (ListView) findViewById(R.id.listViewMultas);
        ArrayAdapter adapter = (ArrayAdapter)listView.getAdapter();
        adapter.clear();
        adapter.addAll(new MultaDAO(this).all());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MultaFormActivity.class);
        startActivity(intent);

    }




}
