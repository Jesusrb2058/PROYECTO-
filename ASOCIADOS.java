package asociados;

import java.time.LocalDate;

public class ASOCIADOS {

    private int num_socio;
    private String nombre;
    private int telefono;
    private LocalDate fecha_ingreso;
    
    public int getNum_socio() {
        return num_socio;
    }
    public void setNum_socio(int num_socio) {
        this.num_socio = num_socio;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }
    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    public ASOCIADOS(int num_socio, String nombre, int telefono, LocalDate fecha_ingreso) {
        this.num_socio = num_socio;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fecha_ingreso = fecha_ingreso;
    }
    
    @Override
    public String toString() {
        return "ASOCIADOS [num_socio=" + num_socio + ", nombre=" + nombre + ", telefono=" + telefono
                + ", fecha_ingreso=" + fecha_ingreso + "]";
    }

    

}
