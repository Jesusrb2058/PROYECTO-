package TiposDatosAbstractos;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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
    private static int conttrolador_asociados = 0;
    private static List<String> cargosDisponibles = new ArrayList<>(Arrays.asList("Jefe", "Administrador", "Gerente", "Orientador", "Otro"));


    public void iniciarMenu() {
        while (true) {
            // Mostrar el menú
            String[] opciones = {"Agregar Nuevo Asociado", "Asignar cargo Directivo", "Imprimir lista de asociados Directivos", "Imprimir lista de asociados naturales"};
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el proceso a realizar:", "Menú Inicio",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            // Ejecutar el método correspondiente
            switch (seleccion) {
                case 0:
                    AgregarAsociados();
                    break;
                case 1:
                    AsignarCargo_Directivo_2(listaAsociados);
                    break;
                case 2:
                    ImprimirAsociadosDirectivos();
                    break;
                case 3:
                    ImprimirAsociadosNaturales();
                    break;
                default:
                    System.exit(0); // Salir del programa
                    break;
            }
        }
    }

    public int  opciones_Cargo(){
        int op = Integer.parseInt(JOptionPane.showInputDialog("1. Jefe \n2. Administrador \n3. Gerente \n4. Orientador \nSELECCIONA UN  CARGO A ASIGNAR: "));
        switch (op) {
            case 1: 
                
                break;
        
            default:
                break;
        }
        return op;

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
            //aqui va la selección de asociado
            LocalDate fechaIngreso = LocalDate.now();
            Asociados nuevoAsociado = new Asociados(numSocio, nombre, telefono, fechaIngreso);
            listaAsociados.add(nuevoAsociado);
            Directivo_O_Natural(listaAsociados);
            //JOptionPane.showMessageDialog(null, "Número de Socio: " + numSocio + "\nNombre: " + nombre + "\nTeléfono: " + telefono + "\nFecha de Ingreso: " + fechaIngreso);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido.");
        }

    }
    public static String seleccionarCargo(List<AsociadoDirecto> listaAD) {
        // si se selecciona un cargo proximamente no se puede volver a usar o seleccionar directamente de las opciones
        List<String> cargosNoAsignados = new ArrayList<>(cargosDisponibles);
        for (AsociadoDirecto a : listaAD) {
            cargosNoAsignados.remove(a.getCargo());
        }
        
        //pues aquí se muestran los cargos disponibles
        Object seleccion = JOptionPane.showInputDialog(
            null,
            "Seleccione el cargo para asignar:",
            "Opciones de Cargos",
            JOptionPane.QUESTION_MESSAGE,
            null,
            cargosNoAsignados.toArray(),
            cargosNoAsignados.get(0)
        );
        
        if (seleccion == null) {
            return null; // Se cancela la operación
        }
        
        String cargo = seleccion.toString();
        
        if (cargo.equals("Otro")) {
            cargo = JOptionPane.showInputDialog(null, "Ingrese el tipo de Cargo:");
            if (cargo == null || cargo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El cargo no puede quedar vacío.");
                return null;
            }
        }
        
        return cargo;
    }
    
    public static void AsignarCargo_Directivo( List<Asociados> listaAsociados) {
    
        Asociados asociado = listaAsociados.get(conttrolador_asociados);
        LocalDate fecha_tomaPosesion = LocalDate.now();
        String cargo = seleccionarCargo(listaAD);

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
    public static void RegistrarAportacionAsociadoNatural(List<Asociados> listaAsociados) {
        if (listaAsociados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay asociados registrados.");
            return;
        }
    
        if (conttrolador_asociados >= listaAsociados.size()) {
            JOptionPane.showMessageDialog(null, "Todos los asociados ya tienen un cargo asignado.");
            return;
        }
    
        Asociados asociado = listaAsociados.get(conttrolador_asociados);
    
        try {
            // Verificar si el asociado ya es un AsociadoNatural
            AsociadoNatural asociadoN = null;
            for (AsociadoNatural an : listaAN) {
                if (an.getNum_socio() == asociado.getNum_socio()) {
                    asociadoN = an;
                    break;
                }
            }
    
            // Si el asociado ya existe como AsociadoNatural, solo actualizar las aportaciones
            if (asociadoN != null) {
                while (true) {
                    String input2 = JOptionPane.showInputDialog("Ingresa la cantidad de nuevas aportaciones: ");
                    int nuevasAportaciones = Integer.parseInt(input2);
                    
                    if (nuevasAportaciones * asociadoN.getMontoAportaciones() < 2500) {
                        JOptionPane.showMessageDialog(null, "La cantidad total de aportaciones debe ser al menos 2500.");
                        continue; // Volver a pedir la aportación
                    }
                    
                    asociadoN.setMontoAportaciones(asociadoN.getMontoAportaciones() + (nuevasAportaciones * asociadoN.getMontoAportaciones()));
                    asociadoN.setCantidadAportaciones(asociadoN.getCantidadAportaciones() + nuevasAportaciones);
                    asociadoN.setFechaUltimaAportacion(LocalDate.now()); // Actualizar la fecha de la última aportación
                    JOptionPane.showMessageDialog(null, "Aportaciones actualizadas correctamente.");
                    return;
                }
            }
    
            // Si el asociado no es un AsociadoNatural, crear uno nuevo
            long montoAportacion;
            while (true) {
                String input = JOptionPane.showInputDialog("Ingresa el monto de la primera aportación (debe ser >= 2500): ");
                montoAportacion = Long.parseLong(input);
                
                if (montoAportacion >= 2500) {
                    break; // Salir del bucle si la aportación es válida
                }
                
                JOptionPane.showMessageDialog(null, "La primera aportación debe ser mayor o igual a 2500.");
            }
    
            LocalDate fecha_tomaPosesion = LocalDate.now();
            AsociadoNatural nuevoAsociadoN = new AsociadoNatural(
                asociado.getNum_socio(),
                asociado.getNombre(),
                asociado.getTelefono(),
                asociado.getFecha_ingreso(),
                montoAportacion,
                1, // Primera aportación
                fecha_tomaPosesion
            );
    
            listaAN.add(nuevoAsociadoN);
            conttrolador_asociados++;
            JOptionPane.showMessageDialog(null, "Aportación registrada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido y/o no deje vacío los espacios.");
        }
    }







    public static void AsignarCargo_Directivo_2(List<Asociados> listaAsociados) {
    if (listaAsociados.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay asociados registrados.");
        return;
    }

    try {
        // Pedir al usuario que ingrese el número de socio
        String inputNumSocio = JOptionPane.showInputDialog(null, "Ingrese el número del socio:");
        if (inputNumSocio == null || inputNumSocio.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El número de socio no puede estar vacío.");
            return;
        }

        int numSocio = Integer.parseInt(inputNumSocio);
        Asociados asociado = null;

        // Buscar el asociado en la lista
        for (Asociados a : listaAsociados) {
            if (a.getNum_socio() == numSocio) {
                asociado = a;
                break;
            }
        }

        if (asociado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un asociado con ese número.");
            return;
        }

        // Seleccionar el cargo de la lista disponible
        String cargo = seleccionarCargo(listaAD);
        if (cargo == null || cargo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un cargo.");
            return;
        }

        // Asignar el cargo al asociado y registrarlo como directivo
        LocalDate fecha_tomaPosesion = LocalDate.now();
        AsociadoDirecto asociadoD = new AsociadoDirecto(
            asociado.getNum_socio(),
            asociado.getNombre(),
            asociado.getTelefono(),
            asociado.getFecha_ingreso(),
            cargo,
            fecha_tomaPosesion
        );

        listaAD.add(asociadoD);
        JOptionPane.showMessageDialog(null, "Cargo asignado correctamente a " + asociado.getNombre());
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: Ingrese un número de socio válido.");
    }
}


public static void ImprimirAsociadosDirectivos() {
    if (listaAD.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay asociados directivos registrados.");
    } else {
        StringBuilder sb = new StringBuilder();
        for (AsociadoDirecto asociado : listaAD) {
            sb.append("Número de Socio: ").append(asociado.getNum_socio()).append("\n");
            sb.append("Nombre: ").append(asociado.getNombre()).append("\n");
            sb.append("Teléfono: ").append(asociado.getTelefono()).append("\n");
            sb.append("Fecha de Ingreso: ").append(asociado.getFecha_ingreso()).append("\n");
            sb.append("Cargo: ").append(asociado.getCargo()).append("\n");
            sb.append("Fecha de Toma de Posesión: ").append(asociado.getFecha_TomaPosesion()
            ).append("\n");
            sb.append("------------\n");
        }
        
        // con el text area se ve de manera que se podamos desplazar todo el rollo
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setText(sb.toString());
        textArea.setEditable(false);
        
        // y con el scrollpane se hace eso
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // ya después con un joptionpane se hace todo el pedo
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Asociados Directivos", JOptionPane.INFORMATION_MESSAGE);
    }
}

public static void ImprimirAsociadosNaturales() {
    if (listaAN.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay asociados naturales registrados.");
    } else {
        StringBuilder sb = new StringBuilder();
        for (AsociadoNatural asociado : listaAN) {
            sb.append(asociado.toString()).append("\n");
            sb.append("------------\n");  // Separador entre los asociados
        }
        
        // Crear un JTextArea para mostrar la información
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setText(sb.toString());
        textArea.setEditable(false);
        
        // Crear un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // Mostrar todo el contenido en un JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Asociados Naturales", JOptionPane.INFORMATION_MESSAGE);
    }
}

        //método auxiliar al método de asignar asociado, sea diretivo o natural
        public static void Directivo_O_Natural(List<Asociados> listaAsociados){
            //void aD = AsignarCargo_Directivo(listaAsociados);
            Object[] op = {"Asociado DIrectivo", "Asociado Natural"};
            int res = JOptionPane.showOptionDialog(null, 
            "¿Que tipo De asociado desea añadir?", 
            "SELECCIÓN DE ASOCIADO",  
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            op, 
            op[0]);
            if(res == 0){
                AsignarCargo_Directivo(listaAsociados);
            }     
            else if (res == 1){
                RegistrarAportacionAsociadoNatural(listaAsociados);
            }   
        }
    
    public static void run() {
        try {
            // Mostrar el diálogo
            int response = JOptionPane.showConfirmDialog(null, "Bienvenido al sistema de gestión de asociados", 
                                                         "Sistema", JOptionPane.DEFAULT_OPTION);
            
            if (response == JOptionPane.CLOSED_OPTION) {
                System.exit(0); 
            }
        } catch (Exception e) {
            System.exit(0); 
        }

    
        MyApp app = new MyApp();
        app.iniciarMenu(); 
    }

}
