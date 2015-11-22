package itson.mx.deliveryapp.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chapa on 21/11/2015.
 */
public class Utils {
    public static SQLiteDatabase db;
    public static void bd(Context context){
        try{
            Bd bdres= new Bd(context, "bdLikes", null, 1, "CREATE TABLE like (platillo TEXT, foto TEXT)");
            Utils.db=bdres.getWritableDatabase();
            String s[]={"platillo", "foto"};

            Cursor c=Utils.db.query("like", s, null, null, null, null, null);
            if(c.moveToFirst()){
            }else{
                Utils.db.execSQL("INSERT INTO like (platillo, foto) VALUES ('', '')");
                c=Utils.db.query("like", s, null, null, null, null, null);
            }
        }catch(Exception e){

        }
    }

    public static void insertBd(Context context, String platillo, String foto){
        if(!existeLike(context, platillo)) {
            ContentValues valores = new ContentValues();
            valores.put("platillo", platillo);
            valores.put("foto", foto);

            //Actualizamos el registro en la base de datos
            if (Utils.db == null || !Utils.db.isOpen()) {
                Bd bdres = new Bd(context, "bdLikes", null, 1, "CREATE TABLE like (platillo TEXT, foto TEXT)");
                Utils.db = bdres.getWritableDatabase();
            }
            Utils.db.insert("like", null, valores);
            Utils.db.close();
        }
    }

    public static List<String> getLikes(Context context){
        List<String> likes = new ArrayList<>();
        if(Utils.db== null || !Utils.db.isOpen()){
            Bd bdres= new Bd(context, "bdLikes", null, 1, "CREATE TABLE like (platillo TEXT, foto TEXT)");
            Utils.db=bdres.getWritableDatabase();
        }

        String s[]={"platillo", "foto"};

        Cursor c=Utils.db.query("like", s, null, null, null, null, null);
        if(c.moveToFirst()){
            while (c.isAfterLast() == false) {
                String like = c.getString(c.getColumnIndex("platillo"));
                String foto = c.getString(c.getColumnIndex("foto"));
                likes.add(like);
                c.moveToNext();
            }
        }
        Utils.db.close();
        return likes;
    }

    private static boolean existeLike(Context context, String valor){
        boolean existe = false;
        if(Utils.db== null || !Utils.db.isOpen()){
            Bd bdres= new Bd(context, "bdLikes", null, 1, "CREATE TABLE like (platillo TEXT, foto TEXT)");
            Utils.db=bdres.getWritableDatabase();
        }

        String s[]={"platillo", "foto"};

        Cursor c=Utils.db.query("like", s, null, null, null, null, null);
        if(c.moveToFirst()){
            while (c.isAfterLast() == false) {
                String like = c.getString(c.getColumnIndex("platillo"));
                if(like.equals(valor)){
                    existe = true;
                }
                c.moveToNext();
            }
        }
        Utils.db.close();
        return existe;
    }
}
