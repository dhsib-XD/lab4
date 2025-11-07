/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ahorcados4;

/**
 *
 * @author mavasquez
 */
public class Ahorcados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdminPalabrasSecretas admin = new AdminPalabrasSecretas();
        
        JuegoAhorcadoFijo juegoFijo = new JuegoAhorcadoFijo("PROGRAMACION");
        juegoFijo.inicializarPalabraSecreta();
        
        JuegoAhorcadoAzar juegoAzar = new JuegoAhorcadoAzar(admin);
        juegoAzar.inicializarPalabraSecreta();
        
        AhorcadosGui gui = new AhorcadosGui();
        gui.setVisible(true);
    }
    
}
