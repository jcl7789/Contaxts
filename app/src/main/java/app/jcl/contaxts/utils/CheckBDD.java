package app.jcl.contaxts.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Juan Carlos Leto on 17/10/2017.
 */

public class CheckBDD extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String tabla_contactos = "CREATE TABLE TablaContactos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, mail TEXT, telefono TEXT)";
    private static final String nombre_tabla = "TablaContactos";
    private SQLiteDatabase db;
    private static String[] campos = {"id", "nombre", "apellido", "mail", "telefono"};


    public CheckBDD(Context c) {
        super(c, "contactos", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_contactos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tabla_contactos);
        onCreate(db);
    }

    public void borrar_todos() {
        db = getWritableDatabase();
        db.delete(nombre_tabla, null, null);
    }

    public void borrar(int id) {
        db = getWritableDatabase();
        db.delete(nombre_tabla, "id=" + id, null);
        db.close();
    }

    public int insertar(String nom, String ape, String mail, String tel) {
        long posicion = 0;
        db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("id", obtenerProximoID());
            valores.put("nombre", nom);
            valores.put("apellido", ape);
            valores.put("mail", mail);
            valores.put("tel", tel);
            posicion = db.insert(nombre_tabla, null, valores);
            db.close();
        }
        return (int) posicion;
    }

    private int obtenerProximoID() {
        db = getReadableDatabase();
        String[] campos = {"id"};
        Cursor cursor = db.query(nombre_tabla, campos, null, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return 1;
        } else {
            cursor.moveToLast();
            int n = 1 + cursor.getInt(0);
            Log.i("Mensaje", "Se devolvió" + n + "\n\n");
            return n;
        }
    }

    public ArrayList<Contacto> listar() {
        db = getReadableDatabase();
        ArrayList<Contacto> lista = new ArrayList<>();
        Cursor cursor = db.query(nombre_tabla, campos, null, null, null, null, "nombre");
        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return null;
        } else {
            do {
                Contacto contacto = new Contacto(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                lista.add(contacto);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return lista;
    }

    public Contacto buscar(int id) {
        db = getReadableDatabase();
        Cursor cursor = db.query(nombre_tabla, campos, "id=" + id, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor != null) cursor.moveToFirst();
        Contacto contacto = new Contacto(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        db.close();
        cursor.close();
        return contacto;

    }

    public void modificar(Contacto contacto) {
        db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", contacto.getId());
        valores.put("nombre", contacto.getNombre());
        valores.put("apellido", contacto.getApellido());
        valores.put("mail", contacto.getMail());
        valores.put("tel", contacto.getTelefono());
        db.update(nombre_tabla, valores, "id=" + contacto.getId(), null);
        db.close();
    }
}
