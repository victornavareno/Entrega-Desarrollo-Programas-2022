import java.util.*;

/**
 * La clase Ciclista representa a los ciclistas que competirán con sus Bicicletas cada Etapa. La
 * diferencia entre cada Ciclista viene marcada por la diferente habilidad y energía del Ciclista.
 * @author Victor
 * @author Andrés
 * @author Elena
 * @version 10.08
 */
public class Ciclista
{
    private String nombre;
    private double habilidad;
    private double energia;
    private boolean abandonado;
    
    private Etapa etapa;
    private Equipo equipo;
    private Bicicleta bicicleta;
    
    //Arraylist con los resultados obtenidos por el ciclista en cada etapa
    private List<Resultado> resultados; //SERÁ INICIALIZADO COMO ARRAYLIST

    /**
     * Constructor de la clase Ciclista
     * 
     * @param nombre     String con el nombre del ciclista
     * @param habilidad  Double con el nivel de habilidad del ciclista
     * @param energia    Double con la energia restante del ciclista durante la competición
     * @param abandonado   Boolean para indicar si el ciclista ha abandonado o no
     * @param equipo     Equipo al que pertenece el ciclista
     * @param bicicleta  Bicicleta asignada al ciclista
     */
    public Ciclista(String nombre, double habilidad, double energia, Equipo equipo)
    {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.energia = energia;
        
        this.equipo = equipo;        
        resultados = new ArrayList<Resultado>();
    }
    
    //MÉTODOS MODIFICADORES (set)
    /**
     * Asigna el nombre
     * 
     * @param nombre String con el nombre del Ciclista
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Asigna el valor de la habilidad
     * 
     * @param Double valor de la habilidad del ciclista
     */
    public void setHabilidad(double habilidad) {
        this.habilidad = habilidad;
    }
    
    /**
     * Asigna el valor de la energia
     * 
     * @param Double valor de la energia restante del ciclista
     */
    public void setEnergia(double energia) {
        this.energia = energia;
    }
               
    /**
     * Asigna el estado de abandonado 
     * 
     * @param Boolean estado de abandonado del ciclista en la competicion
     */
    public void setAbandonado(boolean abandonado) {
        this.abandonado = abandonado;
    }
    
    /**
     * Asigna el Equipo
     * 
     * @param Equipo asignado al ciclista
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
        
    /**
     * Asigna la Bicicleta
     * 
     * @param Bicicleta bicleta asignada al ciclista
     */
    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }
    
    //MÉTODOS DE ACCESO (get)
    /**
     * Devuelve el nombre 
     * 
     * @return String con el nombre del ciclista
     */
    public String getNombre()
    {
        return this.nombre;
    }
    
    /**
     * Devuelve la habilidad
     * 
     * @return Double con la habilidad del ciclista
     */
    public double getHabilidad()
    {
        return this.habilidad;
    }

    /**
     * Devuelve la energia 
     * 
     * @return double con la energia restante del ciclista
     */
    public double getEnergia()
    {
        return this.energia;
    }
    
    /**
     * Devuelve el resultado en su última etapa
     * 
     * @return double con el tiempo obtenido en la ultima etapa
     */
    public double getTiempoEtapa(){
        return obtenerTiempoEtapa(resultados.get(0).getEtapa());
    }

    /**
     * Devuelve el estado de abandonado del ciclista 1 abandonado 0 no abandonado
     * 
     * @return Boolean true si el ciclista ha abandonado
     */
    public boolean getAbandonado()
    {
        return this.abandonado;
    }
    
    /**
     * Devuelve el Equipo
     * @return Equipo con el equipo asignado
     */
    public Equipo getEquipo()
    {
        return this.equipo;
    }
    
    /**
     * Devuelve la Bicicleta 
     * @return Bicicleta con la bicicleta asignada
     */
    public Bicicleta getBicicleta()
    {
        return this.bicicleta;
    }
    
    //MÉTODOS CONTROL DEL ARRAYLIST de Resultados:
    /**
     * Encuentra y devuelve el resultado obtenido en una etapa en concreto
     * @param Etapa la etapa de la cual queremos conocer el tiempo resultado
     * @return double tiempo obtenido en la etapa
     */
    public double obtenerTiempoEtapa(Etapa etapa){
        double tiempoEtapa = 0;
        int indice = 0;
        boolean etapaEncontrada = false; // bandera búsqueda en el ArrayList
        
        while((indice < resultados.size()) && (!etapaEncontrada)){
            if(resultados.get(indice).getEtapa().equals(etapa)){ //USAMOS EL EQUALS PARA COMPARAR OBJETOS
                tiempoEtapa = resultados.get(indice).getTiempo();
                etapaEncontrada = true;
            }
            else indice++;
        }
        return tiempoEtapa;
    }
    
    /**
     * Devuelve el numero de etapas en las que ha participado el ciclista, incluye la etapa en la que abandona
     * @return Int total de etapas
     */
    public int obtenerTotalEtapas(){
        return this.resultados.size();
    }
    
    /**
     * Devuelve el numero de etapas que ha terminado el ciclista, no incluye la etapa en la que abandona
     * @return Int total de etapas completadas
     */
    public int obtenerTotalEtapasTerminadas(){
        int totalEtapasTerminadas = 0;
        if(getAbandonado() == true){
            totalEtapasTerminadas = resultados.size() - 1;
        }
        else {
            totalEtapasTerminadas = resultados.size();
        }
        return totalEtapasTerminadas;
    }
    
    /**
     * Calcula el tiempo total empleado por un ciclista en todas sus etapas completadas
     * @return double tiempo obtenido en todas las etapas que completa
     */
    public double calcularTiempoTotal(){
        actualizarAbandono();
        double tiempoTotal = 0;
        
        // if (getAbandonado() == true){    // SI HA ABANDONADO RECORREMOS ARRAY HASTA SIZE -1        
            // for (int indice = 0; indice < resultados.size(); indice++)
            // tiempoTotal = tiempoTotal + resultados.get(indice).getTiempo(); // SUMANDO EL TIEMPO DE CADA POSICIÓN
        // }  
        // else
            for (int i = 0; i<resultados.size(); i++){
                tiempoTotal = tiempoTotal + resultados.get(i).getTiempo();
        }     
        return tiempoTotal;
    }
 
    /**
     * Devuelve la etapa final del ciclista, en la que abandona
     * @return Etapa etapa en la que el ciclista abandona
     */
    public Etapa obtenerEtapaAbandonado(){
        if(getAbandonado() == true){
            return this.resultados.get(resultados.size()).getEtapa();
        }
        else return null;
    }
    
    // OTROS MÉTODOS DE LA CLASE CICLISTA:
    
    /**
     * Indica si el ciclista posee una bicicleta asignada o no
     * 
     * @return boolean estado de asignación de la Bicicleta (true: tiene bicicleta asignada, false: no tiene)
     */
    public boolean tieneBicicleta(){
        boolean tiene;
        if(this.bicicleta.equals(null)){
            tiene = false;
        }
        else {
            tiene = true;
        }
        return tiene;
    }
    
    /**
     * muestra las características de un Ciclista
     * 
     * @return String con los atributos del ciclista en formato de salida correcto
     */
    public String toString(){               
        return "<ciclista: " + nombre + "> <energía: " + Math.round(energia*100.0)/100.0 + "> <habilidad: " + Math.round(habilidad*100.0)/100.0 + "> <tiempo acumulado sin abandonar: " + Math.round(calcularTiempoTotal()*100.0)/100.0 + "> <abandonado: " + abandonado +">";
    }

    /**
     * Comprueba y actualiza el estado del ciclista, si no tiene energia abandona 
     * 
     */
    public void actualizarAbandono(){
        if(this.energia > 0){
            setAbandonado(false);
        }
        else{
            setAbandonado(true);
        }
    }
    
    /**
     * Calcula y actualiza la energía del ciclista
     * 
     */
    public void actualizarEnergia(Etapa etapa){
        energia = energia - bicicleta.calcularTiempo(etapa.getDistancia(),bicicleta.calcularVelocidad(this.habilidad, etapa.getDificultad()));
        setEnergia(energia);
    }
    
    
    /**
     * Calcula y actualiza los resultados (tiempo) de un ciclista en una etapa en concreto utilizando su bicicleta (participar en una carrera)
     * 
     * @param Etapa en la que participa
     */
    public void hacerCarrera(Etapa etapa){
        double velocidadBicicleta = bicicleta.calcularVelocidad(this.habilidad, etapa.getDificultad()); // CALCULO LA VELOCIDAD QUE ALCANZARÁ EL CICLISTA PARA ESTA ETAPA CON LA BICICLETA
        double tiempoCarrera = bicicleta.calcularTiempo(etapa.getDistancia(), velocidadBicicleta); // CALCULO EL TIEMPO QUE TARDARÁ EL CICLISTA CON LA BICICLETA PARA ESA ETAPA
        
        actualizarEnergia(etapa); //CON EL TIEMPO DE LA ETAPA, CALCULO Y ACTUALIZO LA ENERGIA DEL CICLISTA
        actualizarAbandono();
        
        if (!this.abandonado){ // NO HA ABANDONADO
            resultados.add(new Resultado(tiempoCarrera, etapa)); // AÑADO UN NUEVO RESULTADO AL ARRAY
        }
        else{
            resultados.add(new Resultado(this.energia, etapa)); // SE HA QUEDADO SIN ENERGIA, INSERTAMOS EL TIEMPO NEGATIVO EN EL ARRAY RESULTADOS
        }
    }   
}

