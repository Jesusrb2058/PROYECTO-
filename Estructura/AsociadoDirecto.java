package Estructura;

import java.time.LocalDate;


public class AsociadoDirecto extends Asociados {

    private String cargo;
    private LocalDate fecha_TomaPosesion;
    private int contador_Asociados_Directivos;
    
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
    
    public int getContador_Asociados_Directivos() {
        return contador_Asociados_Directivos;
    }
    public void setContador_Asociados_Directivos(int contador_Asociados_Directivos) {
        this.contador_Asociados_Directivos = 0;
    }
    public AsociadoDirecto(int num_socio, String nombre, int telefono, LocalDate fecha_ingreso, String cargo,
            LocalDate fecha_TomaPosesion, AsociadoDirecto[] arreglo_AD, int contador_Asociados_Directivos) {
        super(num_socio, nombre, telefono, fecha_ingreso);
        this.cargo = cargo;
        this.fecha_TomaPosesion = fecha_TomaPosesion;
        this.contador_Asociados_Directivos = 0;
    }

    @Override
    public String toString() {
        return super.toString() + "ASOCIADO_DIRECTIVO [cargo=" + cargo + ", fecha_TomaPosesion=" + fecha_TomaPosesion
                + ", contador_Asociados_Directivos=" + contador_Asociados_Directivos + "]";
    }
    
    

    
    



    
}
