package TiposDatosAbstractos;
import javax.swing.*;
import java.time.LocalDate;

public class AsociadoFormulario {

    public static void main(String[] args) {
        boolean telefonoValido = false;
        String nombre = "";
        String telefono = "";
        
        while (!telefonoValido) {
            // Crear los componentes del formulario
            JTextField nombreField = new JTextField(10);
            JTextField telefonoField = new JTextField(10);
            
            // Crear un panel para agrupar los campos
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Ingrese el nombre del asociado:"));
            panel.add(nombreField);
            panel.add(new JLabel("Ingrese el teléfono del asociado (10 dígitos):"));
            panel.add(telefonoField);
            
            // Mostrar el panel en un JOptionPane
            int result = JOptionPane.showConfirmDialog(null, panel, "Formulario de Asociado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Verificar si el usuario hizo clic en OK
            if (result == JOptionPane.OK_OPTION) {
                nombre = nombreField.getText();
                telefono = telefonoField.getText();
                
                // Validar que el teléfono tenga exactamente 10 dígitos
                if (telefono.matches("\\d{10}")) {
                    telefonoValido = true; // Número de teléfono válido
                } else {
                    JOptionPane.showMessageDialog(null, "El número de teléfono debe tener exactamente 10 dígitos.");
                }
            } else {
                System.out.println("Operación cancelada.");
                return; // Salir del programa si el usuario cancela
            }
        }

        // Si el número de teléfono es válido, continuar con el resto de los datos
        LocalDate fechaIngreso = LocalDate.now();

        // Mostrar los datos ingresados
        JOptionPane.showMessageDialog(null, 
            "Nombre: " + nombre + "\n" +
            "Teléfono: " + telefono + "\n" +
            "Fecha de Ingreso: " + fechaIngreso);
    }
}
