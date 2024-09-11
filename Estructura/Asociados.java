package Estructura;
    
import java.time.LocalDate;
    
public class Asociados {

    private int num_socio;
    private String nombre;
    private Long telefono;
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
    public long getTelefono() {
        return telefono;
    }
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }
    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    
    public Asociados(int num_socio, String nombre, long telefono, LocalDate fecha_ingreso) {
        this.num_socio = num_socio;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fecha_ingreso = fecha_ingreso;
    }
    
    @Override
    public String toString() {
        // Retornar la representación en String del objeto
        return "Número de Socio: " + num_socio + "\n" +
               "Nombre: " + nombre + "\n" +
               "Teléfono: " + telefono + "\n" +
               "Fecha de Ingreso: " + fecha_ingreso + "\n";

    }

}
