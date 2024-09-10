package TiposDatosAbstractos;

import java.util.ArrayList;
import java.util.List;

import Estructura.AsociadoDirecto;
import Estructura.AsociadoNatural;
import Estructura.Asociados;
import java.time.LocalDate;
import javax.swing.*;
import java.util.Random;

public class MyApp {

    // Lista para almacenar los asociados
    private static List<Asociados> listaAsociados = new ArrayList<>();
    private static List<AsociadoDirecto> listaAD = new ArrayList<>();
    private static List<AsociadoNatural> listaAN = new ArrayList<>();
    private static int conttrolador_asociados = 0; //esta vaina controla el registro de asociados directivos para que a la hora de imprimir no haya pedo  



    public void iniciarMenu() {
        while (true) {
            // Mostrar el menú
            String[] opciones = {"Agregar Nuevo Asociado", "Asignar cargo Directivo", "Registro de aportación", "Imprimir lista de asociados Directivos", "Imprimir lista de asociados naturales"};
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el proceso a realizar:", "Menú Inicio",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            // Ejecutar el método correspondiente
            switch (seleccion) {
                case 0:
                    AgregarAsociados();
                    break;
                case 1:
                    AsignarCargo_Directivo(listaAsociados);
                    break;
                case 2:
                    RegistrarAportacionAsociadoNatural(listaAsociados);
                    break;
                case 3:
                    ImprimirAsociadosDirectivos();
                    break;
                case 4:
                    ImprimirAsociadosNaturales();
                    break;
                default:
                    System.exit(0); // Salir del programa
                    break;
            }
        }
    }

    public static void AgregarAsociados() {
        try {
            Random rand = new Random();
            int numSocio = rand.nextInt(90000) + 10000;
            String nombre;
            long telefono;
            
            while (true) {
                JTextField nombreField = new JTextField(10);
                JTextField telefonoField = new JTextField(10);
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Ingrese el nombre del asociado:"));
                panel.add(nombreField);
                panel.add(new JLabel("Ingrese el teléfono (10 dígitos):"));
                panel.add(telefonoField);
                int result = JOptionPane.showConfirmDialog(null, panel, "Formulario de Asociado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                if (result != JOptionPane.OK_OPTION) {
                    System.out.println("Operación cancelada.");
                    return;
                }
                
                nombre = nombreField.getText();
                String telefonoStr = telefonoField.getText();
                if (telefonoStr.matches("\\d{10}")) {
                    telefono = Long.parseLong(telefonoStr);
                    break;
                }
                JOptionPane.showMessageDialog(null, "El número de teléfono debe tener exactamente 10 dígitos.");
            }
            LocalDate fechaIngreso = LocalDate.now();
            Asociados nuevoAsociado = new Asociados(numSocio, nombre, telefono, fechaIngreso);
            listaAsociados.add(nuevoAsociado);
            JOptionPane.showMessageDialog(null, "Número de Socio: " + numSocio + "\nNombre: " + nombre + "\nTeléfono: " + telefono + "\nFecha de Ingreso: " + fechaIngreso);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido.");
        }

    }
    

    public static void AsignarCargo_Directivo( List<Asociados> listaAsociados) {
        if (listaAsociados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay asociados registrados");
            return;
        }
        if (conttrolador_asociados >= listaAsociados.size()) {
            JOptionPane.showMessageDialog(null, "Todos los asociados ya tienen un cargo asignado.");
            return;
        }  //esta mae ni se les ocurra quitarla (el segundo if del metodo), sino a la hora de imprimir solo se va a imprimir el primer asociado registrado
        //ctm yonathan <3
        //Y: ese wey 
        Asociados asociado = listaAsociados.get(conttrolador_asociados);
        String cargo = JOptionPane.showInputDialog(null, "Ingresa el cargo para asignar: ");
        LocalDate fecha_tomaPosesion = LocalDate.now();
        if (cargo == null){
            JOptionPane.showInputDialog(null, "El cargo no puede quedar vacío.");
            return;
        }
        AsociadoDirecto asociadoD = new AsociadoDirecto(
            asociado.getNum_socio(),
            asociado.getNombre(),
            asociado.getTelefono(),
            asociado.getFecha_ingreso(),
            cargo,
            fecha_tomaPosesion
        );
        listaAD.add(asociadoD);
        conttrolador_asociados++;
    }
    

    public static void RegistrarAportacionAsociadoNatural(List<Asociados> listaAsociados){
    if (listaAsociados.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay asociados registrados");
        return;
    }
    if (conttrolador_asociados >= listaAsociados.size()) {
        JOptionPane.showMessageDialog(null, "Todos los asociados ya tienen un cargo asignado.");
        return;
    }  
    Asociados asociado = listaAsociados.get(conttrolador_asociados);
    try{
    String input = JOptionPane.showInputDialog("Ingresa el monto de aportaciones: ");
    Long montoAportaciones = Long.parseLong(input);
    String input2 = JOptionPane.showInputDialog("Ingresa la cantidad de aportaciones: ");
    int cantidadAportaciones = Integer.parseInt(input2);
    LocalDate fecha_tomaPosesion = LocalDate.now();
    AsociadoNatural asociadoN = new AsociadoNatural(
        asociado.getNum_socio(),
        asociado.getNombre(),
        asociado.getTelefono(),
        asociado.getFecha_ingreso(),
        montoAportaciones,
        cantidadAportaciones,
        fecha_tomaPosesion
    );
    listaAN.add(asociadoN);
    conttrolador_asociados++;
    }catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido y/o No deje vacio los espacios");
        return;
    }


    
}

    public static void ImprimirAsociadosDirectivos() {
        if (listaAD.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay asociados directivos registrados.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (AsociadoDirecto asociado : listaAD) {
                sb.append(asociado.toString()).append("\n");
                sb.append("------------\n"); //Y: le agregue un separador dentro de la lista
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    public static void ImprimirAsociadosNaturales() {
        if (listaAN.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay asociados naturales registrados.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (AsociadoNatural asociado : listaAN) {
                sb.append(asociado.toString()).append("\n");
                sb.append("------------\n");  //Y: le agregue un separador dentro de la lista
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
    
    public static void run() {
        try {
            // Mostrar el diálogo
            int response = JOptionPane.showConfirmDialog(null, "Bienvenido al sistema de gestión de asociados", 
                                                         "Sistema", JOptionPane.DEFAULT_OPTION);
            // Si se cierra la ventana o se presiona "OK", response será -1
            if (response == JOptionPane.CLOSED_OPTION) {
                System.exit(0); // Cerrar el programa si se cierra la ventana
            }
        } catch (Exception e) {
            System.exit(0); // Cerrar el programa en caso de error
        }

        // Lo único que hace es llamar a la función para iniciar todo
        MyApp app = new MyApp();
        app.iniciarMenu(); 
    }

}
