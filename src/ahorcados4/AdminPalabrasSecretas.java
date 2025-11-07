package ahorcados4;

import java.util.ArrayList;
import java.util.Random;

public class AdminPalabrasSecretas {
    
    private ArrayList<String> palabras;
    private Random random;
    
    public AdminPalabrasSecretas() {
        palabras = new ArrayList<>();
        random = new Random();
        cargarPalabrasIniciales();
    }
    
    private void cargarPalabrasIniciales() {
        palabras.add("AREA");
        palabras.add("AUDIO");
        palabras.add("LENOVO");
        palabras.add("RATON");
        palabras.add("BARCELONA");
        palabras.add("ERICK");
        palabras.add("GOKU");
        palabras.add("NUEZ");
        palabras.add("ARROYO");
        palabras.add("MEDELLIN");

        palabras.add("ALMENDRA");
        
        palabras.add("SILENCIO");
        palabras.add("CRISTAL");
        palabras.add("MURMULLO");
        palabras.add("RELOJ");
        palabras.add("NEBLINA");
        palabras.add("LAMPARA");
        palabras.add("TORNADO");

        palabras.add("TRAVESIA");
        palabras.add("PENDULO");
        palabras.add("ARENA");
        palabras.add("SUSURRO");
        palabras.add("ECLIPSE");
        palabras.add("BRUJULA");
        palabras.add("CASCADA");
        palabras.add("SOMBRERO");
        palabras.add("DESTELLO");
        palabras.add("THANOS");
        palabras.add("REAPER");
        palabras.add("VAMPIRO");
        palabras.add("TRAUMA");
    }
    
    public boolean agregarPalabra(String palabra) {
        palabra = palabra.toUpperCase();
        
        if (palabras.contains(palabra)) {
            return false;
        }
        
        palabras.add(palabra);
        return true;
    }
    
    public String obtenerPalabraAleatoria() {
        if (palabras.isEmpty()) {
            return "JAVA";
        }
        int indice = random.nextInt(palabras.size());
        return palabras.get(indice);
    }
    
    public ArrayList<String> getPalabras() {
        return palabras;
    }
    
    public int cantidadPalabras() {
        return palabras.size();
    }
    
}

