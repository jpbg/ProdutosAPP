package helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "produtos.dv", null, 1);
    }

    public DbHelper(Context context){
        super(context, "produtos.dv", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table produto ( id integer primary key autoincrement, nome text, descricao text, valor text, foto text);";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists produto ;";
        db.execSQL(sql);
        onCreate(db);
    }
}
