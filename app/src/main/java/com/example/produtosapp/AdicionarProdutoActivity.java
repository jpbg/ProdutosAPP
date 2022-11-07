package com.example.produtosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dao.ProdutoDAO;

public class AdicionarProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produto);


        Button btnCad = findViewById(R.id.btcad);
        EditText nome = findViewById(R.id.etNomeProduto);
        EditText desc = findViewById(R.id.etdescProduto);
        EditText val = findViewById(R.id.etValorProduto);



        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validação

                ProdutoDAO p = new ProdutoDAO(null,
                        nome.getText().toString(),
                        desc.getText().toString(),
                        val.getText().toString(),
                        "",
                        AdicionarProdutoActivity.this);

                if(p.inserir()){
                    Intent main =
                            new Intent(AdicionarProdutoActivity.this,
                                    MainActivity.class);

                    startActivity(main);

                }


            }


        });

    }
}