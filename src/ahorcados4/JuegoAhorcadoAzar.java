package ahorcados4;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    
    private AdminPalabrasSecretas admin;
    
    public JuegoAhorcadoAzar(AdminPalabrasSecretas admin) {
        super();
        this.admin = admin;
        inicializarPalabraSecreta();
    }
    
    public void inicializarPalabraSecreta() {
        this.palabraSecreta = admin.obtenerPalabraAleatoria();
        this.palabraActual = "";
        for (int i = 0; i < palabraSecreta.length(); i++) {
            palabraActual += "_";
        }
    }
    
    public void actualizarPalabraActual(char letra) {
        String nueva = "";
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                nueva += letra;
            } else {
                nueva += palabraActual.charAt(i);
            }
        }
        palabraActual = nueva;
    }
    
    public boolean verificarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        
        if (letrasUsadas.contains(letra)) {
            return false;
        }
        
        letrasUsadas.add(letra);
        
        boolean encontrada = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                encontrada = true;
                break;
            }
        }
        
        if (encontrada) {
            actualizarPalabraActual(letra);
        } else {
            intentos++;
        }
        
        return encontrada;
    }
    
    public boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }
    
    public void jugar() {
    }
    
}

