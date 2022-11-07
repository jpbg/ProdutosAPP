package com.example.produtosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import dao.ProdutoDAO;

public class MainActivity extends AppCompatActivity {


    ListView lvProdutos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProdutos = findViewById(R.id.list_view);

        lvProdutos.setEmptyView(findViewById(R.id.tvEmpty));

        ProdutoDAO pDao = new ProdutoDAO(MainActivity.this);
        Cursor cursor = pDao.listarProdutos();

        //Cursor cc = new ProdutoDAO(this).listarProdutos();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this,
                R.layout.lista_produtos,
                cursor,
                new String[]{"_id", "nome"},
                new int[]{R.id.id, R.id.nome},0);

        lvProdutos.setAdapter(adapter);



        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, id+"", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this, ModificarProdutoActivity.class);
                it.putExtra("id", id+"");
                startActivity(it);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tela_principal,
                menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_add:
                Intent add_mem = new Intent(this, AdicionarProdutoActivity.class);
                startActivity(add_mem);

        }


        return super.onOptionsItemSelected(item);
    }
}