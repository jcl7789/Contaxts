package app.jcl.contaxts.utils;

/**
 * Created by Juan Carlos Leto on 17/10/2017.
 */

public class Contacto {
    private int id;
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;

    public Contacto(int id, String nombre, String apellido, String mail, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
    }

    public Contacto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String toStringPorApellido() {
        return apellido + ", " + nombre;
    }

    public String toStringPorNombre() {
        return nombre + " " + apellido;
    }
}
