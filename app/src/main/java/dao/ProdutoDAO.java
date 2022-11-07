package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import helper.DbHelper;

public class ProdutoDAO {

    private Integer id;
    private String nome;
    private String descricao;
    private String valor;
    private String foto;

    private DbHelper db;


    private SQLiteDatabase database;


    public ProdutoDAO() {

    }

    public ProdutoDAO(Context context) {
        db = new DbHelper(context);
        database = db.getWritableDatabase();

    }

    public ProdutoDAO(Integer id, String nome, String descricao,
                      String valor, String foto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.foto = foto;

    }

    public ProdutoDAO(Integer id, String nome,
                      String descricao, String valor, String foto,
                      Context context) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.foto = foto;

        db = new DbHelper(context);
        database = db.getWritableDatabase();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public boolean inserir (){

        ContentValues cv = new ContentValues();
        cv.put("nome",this.nome);
        cv.put("descricao", this.descricao);
        cv.put("foto", this.foto);
        cv.put("valor", this.valor);

        long ret = database.insert("produto",
                null,cv);

        if (ret > 0){
            return true;
        }
        return false;

    }

    public boolean update(){
        ContentValues cv = new ContentValues();
        cv.put("nome",this.nome);
        cv.put("descricao", this.descricao);
        cv.put("foto", this.foto);
        cv.put("valor", this.valor);

        long ret = database.update("produto", cv,"id = ?", new String[]{this.id+""} );

        if (ret > 0){
            return true;
        }
        return false;
    }

    public boolean delete(){
        long ret = database.delete("produto","id = ?", new String[]{this.id+""} );

        if (ret > 0){
            return true;
        }
        return false;
    }


    public void obterProdutoById(Integer id){
        String sql = "Select * From produto where id = ?; ";
        Cursor c = database.rawQuery(sql,new String[]{id+""});

        if(c != null){
            c.moveToFirst();
        }
        this.id = Integer.parseInt(c.getString(0));
        this.nome = c.getString(1);
        this.descricao = c.getString(2);
        this.valor = c.getString(3);
        this.foto = c.getString(4);


    }


    public Cursor listarProdutos(){

        String sql = "SELECT id as _id, nome From produto;";
        Cursor c = database.rawQuery(sql,null);

        if(c != null){
            c.moveToFirst();
        }
        return c;
    }



}
