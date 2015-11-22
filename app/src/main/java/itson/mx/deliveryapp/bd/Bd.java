package itson.mx.deliveryapp.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chapa on 21/11/2015.
 */
public class Bd extends SQLiteOpenHelper{
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate;

    public Bd(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version, String sqlCreate) {
        super(contexto, nombre, factory, version);
        this.sqlCreate = sqlCreate;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla

        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

    }
}