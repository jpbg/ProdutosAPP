package com.example.produtosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dao.ProdutoDAO;

public class ModificarProdutoActivity extends AppCompatActivity {

    ProdutoDAO pdao;
    EditText editText;
    EditText etdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_produto);


        Intent it = getIntent();
        String id = it.getStringExtra("id");

        pdao = new ProdutoDAO(this);

        pdao.obterProdutoById(Integer.parseInt(id));


         editText  = findViewById(R.id.etEditNome);
         etdesc = findViewById(R.id.etEditDescricao);

        editText.setText(pdao.getNome());
        etdesc.setText(pdao.getDescricao());

        Button btnAtualizar = findViewById(R.id.btnAtualizar);


        Button btnExcluir = findViewById(R.id.btnexcluir);


        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pdao.setNome(editText.getText().toString());
                pdao.setDescricao(etdesc.getText().toString());
                if(pdao.update()){
                    Intent it = new Intent(ModificarProdutoActivity.this, MainActivity.class);
                    startActivity(it);
                }

            }
        });


        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdao.delete()){
                    Intent it = new Intent(ModificarProdutoActivity.this, MainActivity.class);
                    startActivity(it);
                }
            }
        });



    }
}