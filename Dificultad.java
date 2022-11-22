/**
 * Clase Enum Dificultad las Etapas pueden tener una dificultad SENCILLA (0.9), 
 * NORMAL (1.0) o COMPLEJA (1.1)
 * 
 * @author Victor
 * @version 1
 */
public enum Dificultad
{
    SENCILLA("SENCILLA", 0.9),
    NORMAL("NORMAL", 1.0),
    COMPLEJA("COMPLEJA", 1.1);
    
    private String nombre;
    private double valor;
    
    private Dificultad(String nombre, double valor){
        this.nombre = nombre;
        this.valor = valor;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public double getValor(){
        return this.valor;
    }
}