
/**
 * La clase Bicicleta representa a las distintas bicicletas 
 * que los Ciclistas pueden usar en las Etapas. 
 * 
 * @author Elena
 * @author Andres
 * @author Victor
 * @version 14.10
 */
public class Bicicleta
{
    // definicion de campos de la clase Bicicleta
    private String nombre;
    private int peso;
    
    private int velocidad;
    private int tiempo;
    
    /**
     *  Constructor parametrizado de Bicicleta
     */
    public Bicicleta(String nombreBici, int pesoBici)
    {
        this. nombre = nombreBici;
        this.peso = pesoBici;
    }
    
    //MÉTODOS MODIFICADORES (set)
    /**
     * Asigna el nombre de la bicicleta
     * @param String nombreBici 
     */
    void setNombre(String nombreBici){
        this.nombre = nombreBici;
    }
    
    /**
     * Asigna el peso de la bicicleta
     * @param int pesoBici
     */
    void setPeso(int pesoBici){
        this.peso = pesoBici;
    }
   
    //MÉTODOS DE ACCESO(get)
    /**
     * Devuelve el nombre de la Bicicleta
     * @return Double con la distancia recorrida en la etapa
     */
    String getNombre(){
        return this.nombre;
    }

    /**
     * Devuelve el peso de la Bicicleta
     * @return int peso
     */
    int getPeso(){
        return this.peso;
    }
    
    //OTROS METODOS DE LA CLASE Bicicleta:
    /**
     * muestra las características de una Bicicleta
     */    
    void mostrar(){
        System.out.println("Bicicleta: " + this.nombre);
        System.out.println("Peso: " + this.peso);
    }
    
    /**
     *  calcular y proporcionar su velocidad cuando es usada por un Ciclista 
     *  en particular en una Etapa en concreto
     *  
     * @param  habilidadCiclista, dificultadEtapa
     * @return    velocidad
     */
    public int calcularVelocidadBicicleta(int habilidadCiclista, int dificultadEtapa)
    {
        this.velocidad = (habilidadCiclista * 100) / (this.peso * dificultadEtapa);
        return this.velocidad;
    }
    
    /**
     *  calcular y proporcionar el tiempo necesario (medido en minutos) para terminar
     *  la Etapa cuando es usada por un Ciclista en particular en una Etapa concreta
     
     * @param  distanciaEtapa velocidadBicicleta
     * @return    tiempo
     */
    public int calcularTiempo(int distanciaEtapa, int velocidadBicicleta)
    {
        this.tiempo = (distanciaEtapa / velocidadBicicleta) * 60;
        return this.tiempo;
    }
    
    
}
