package Estructura;
    
import java.time.LocalDate;
 
public class AsociadoDirecto extends Asociados {

    private String cargo;
    private LocalDate fecha_TomaPosesion;
    //private int contador_Asociados_Directivos;
    
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public LocalDate getFecha_TomaPosesion() {
        return fecha_TomaPosesion;
    }
    public void setFecha_TomaPosesion(LocalDate fecha_TomaPosesion) {
        this.fecha_TomaPosesion = fecha_TomaPosesion;
    }
    
    
    public AsociadoDirecto(int num_socio, String nombre, long telefono, LocalDate fecha_ingreso, String cargo,
            LocalDate fecha_TomaPosesion) {
        super(num_socio, nombre, telefono, fecha_ingreso);
        this.cargo = cargo;
        this.fecha_TomaPosesion = fecha_TomaPosesion;
    }

    
    
    @Override
    public String toString() {
        return super.toString() + "Cargo: " + cargo + ",\nFecha toma de Posesión: " + fecha_TomaPosesion;
    }
    
    

    
    



    
}
