import java.util.*;
import java.io.*;
/**
 * Representa los Equipos que competirán tanto por ganar la clasificación por Equipos como que
 * uno de sus Ciclistas gane el campeonato individual de Ciclistas.
 * 
 * @author Víctor
 * @version 1
 */
public class Equipo
{
    private String nombre;
    private List<Ciclista> ciclistas;
    private List<Ciclista> ciclistasAbandonado;
    private List<Bicicleta> bicicletas;


    // CAMPOS COMPARATOR
    Comparator<Bicicleta> comparadorBicicleta;
    Comparator<Ciclista> comparadorCiclista;
    private boolean ordenarOrdenInversoBicicleta;
    private boolean ordenarOrdenInversoCiclista; 
    
    //Inicializo el filewriter
    FileWriter writer;
    

    /**
     * Constructor parametrizado de la clase Equipo
     * 
     * @param String nombre, Comparator<Bicicleta> comparadorBicicleta, Comparator<Ciclista> comparatorCiclista, boolean ordenarOrdenInvBicicleta, boolean ordenarOrdenInvCiclista
     */
    public Equipo(String nombre, Comparator<Bicicleta> comparadorBicicleta, Comparator<Ciclista> comparatorCiclista, boolean ordenarOrdenInversoBicicleta, boolean ordenarOrdenInversoCiclista)
    {
        this.nombre = nombre;
        ciclistas = new ArrayList<Ciclista>();
        ciclistasAbandonado = new ArrayList<Ciclista>();
        bicicletas = new ArrayList<Bicicleta>();   

        this.comparadorBicicleta = comparadorBicicleta;
        this.comparadorCiclista = comparatorCiclista;
        this.ordenarOrdenInversoBicicleta = ordenarOrdenInversoBicicleta;
        this.ordenarOrdenInversoCiclista = ordenarOrdenInversoCiclista;
        
        if(this.nombre.length() == 0 || nombre == null ) { // controlo que el nombre tenga un valor no vacio ""
            throw new IllegalStateException("El nombre no debe estar inicializado a una cadena vacia o a nulo.");
        }
    }

    //MÉTODOS MODIFICADORES (set)
    /**
     * Asigna el nombre del Equipo
     * 
     * @param nombre String con el nombre del Equipo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Asigna la lista de ciclistas actuales del Equipo
     * 
     * @param competidores ArrayList<Ciclista> con los ciclistas
     */
    public void setCiclistas(ArrayList<Ciclista> ciclistas) {
        this.ciclistas = ciclistas;
    }

    /**
     * Asigna la lista de ciclistas que han abandonado
     * 
     * @param eliminados ArrayList<Ciclista> con los abandonados 
     */
    public void setEliminados(ArrayList<Ciclista> ciclistasAbandonado) {
        this.ciclistasAbandonado = ciclistasAbandonado;
    }

    /**
     * Asigna la lista de bicicletas de las que dispone el Equipo
     * 
     * @param bicicletas ArrayList<Bicicleta> con las bicicletas
     */
    public void setBicicletas(ArrayList<Bicicleta> bicicletas) {
        this.bicicletas = bicicletas;
    }

    //MÉTODOS DE ACCESO (get)
    /**
     * Devuelve el nombre del Equipo
     * 
     * @return String con el nombre del Equipo
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve la lista de ciclistas actuales del equipo
     * 
     * @return ArrayList<Ciclista> con los ciclistas 
     */
    public List<Ciclista> getCiclistas() {
        return this.ciclistas;
    }

    /**
     * Devuelve la lista de ciclistas que han abandonado el equipo
     * 
     * @return ArrayList<Ciclista> con los ciclistasAbandonado
     */
    public List<Ciclista> getCiclistasAbandonado() {
        return this.ciclistasAbandonado;
    }

    /**
     * Devuelve la lista de bicicletas del equipo
     * 
     * @return ArrayList<Bicicleta> con las bicicletas
     */
    public List<Bicicleta> getBicicletas() {
        return this.bicicletas;
    }

    //MÉTODOS DE LOS ARRAYLIST:
    /**
     * Añade una bicicleta al arraylist
     * 
     * @param Objeto Bicicleta con la bicicleta a introducir
     */
    public void anadirBicicleta(Bicicleta bicicleta){
        this.bicicletas.add(bicicleta);
    }

    /**
     * Añade un ciclista al ArrayList de ciclistas
     * 
     * @param Objeto Ciclista con el ciclista a introducir
     */
    public void anadirCiclista(Ciclista ciclista){
        this.ciclistas.add(ciclista);
    }

    //Ordenando los ArrayList de ciclistas y bicicletas
    /**
     * Ordena el ArrayList de ciclistas segun criterios del comparadorCiclista
     * 
     * @return ArrayList<Ciclista> con los ciclistas ordenados 
     */
    private void OrdenarCiclistas(){
        //System.out.println("Ordenando ciclistas");  
        if(this.ordenarOrdenInversoCiclista == true){      
            Collections.sort(this.ciclistas, Collections.reverseOrder(comparadorCiclista)); // ORDENO EN ORDEN INVERSO
        }
        else Collections.sort(this.ciclistas, comparadorCiclista); // ORDEN NORMAL
    }

    /**
     * Ordena el ArrayList de ciclistas abandonados segun criterios del comparadorCiclista
     * 
     * @return ArrayList<Ciclista> con los ciclistas abandonados ordenados 
     */
    private void OrdenarCiclistasAbandonado(){
        if(this.ordenarOrdenInversoCiclista == true){      
            Collections.sort(this.ciclistasAbandonado, Collections.reverseOrder(comparadorCiclista)); // ORDENO EN ORDEN INVERSO
        }
        else Collections.sort(this.ciclistasAbandonado, comparadorCiclista);
    }

    /**
     * Ordena el ArrayList de bicicletas segun criterios del comparadorBicicleta
     * 
     * @return ArrayList<Bicicleta> con las bicicletas
     */
    private void OrdenarBicicletas(){
        //System.out.println("Ordenando bicicletas");
        if(this.ordenarOrdenInversoBicicleta == true){      
            Collections.sort(this.bicicletas, Collections.reverseOrder(comparadorBicicleta)); // ORDENO EN ORDEN INVERSO
        }
        else Collections.sort(this.bicicletas, comparadorBicicleta); // ORDEN NORMAL
    }

    /**
     * Calcula el total del tiempo obtenido en todas las etapas por todos los ciclistas del equipo
     * 
     * @return double tiempoTotalEquipo con la suma del tiempo de todos los ciclistas en todas las etapas
     */
    public double calcularTiempoTotal(){
        double tiempoTotalEquipo = 0;
        for (Ciclista ciclista : ciclistas){ // BUCLE FOR EACH QUE RECORRE EL ARRAY DE CICLISTAS DEL  EQUIPO
            tiempoTotalEquipo = tiempoTotalEquipo + ciclista.calcularTiempoTotal();
        }   
        return tiempoTotalEquipo;
    }

    /**
     * Calcula la media del tiempo obtenido en todas las etapas por todos los ciclistas del equipo
     * 
     * @return double mediaTiempo con la media del tiempo de todos los ciclistas en todas las etapas
     */
    public double calcularMediaTiempo(){
        return calcularTiempoTotal() / obtenerTotalCiclistas();
    }

    /**
     * Devuelve cadena con las caracteristicas del equipo en el formato indicado
     * 
     * @return String con la cabecera del equipo
     */
    @Override
    public String toString(){
        return "%%% " + nombre;
    }
    
    /**
    * Técnica de generación de hashcode, según se indica en el libro:
    * Effective Java by Joshua Bloch.
    */
    @Override
    public int hashCode()
    {
        int result = 11; // definimos numero primo
        result = 3 * result + getNombre().hashCode();
        return result;
    }

    /**
     * Imprime por pantalla cadenas con los Ciclistas que forman parte del Equipo
     * 
     */
    public String mostrarCiclistas(){
        OrdenarCiclistas();
        String listaCiclistas = "";
        for(Ciclista ciclista : ciclistas){
            listaCiclistas = listaCiclistas + ciclista.toString() + '\n';
        }
        return listaCiclistas;
    }

    /**
     * Devuelve true si hay ciclistas abandonados en este equipo, false si no hay
     * 
     * @return boolean
     */
    public boolean hayAbandonados(){
        return !ciclistasAbandonado.isEmpty();
    }

    /**
     * Imprime por pantalla cadenas con los Ciclistas abandonados que forman parte del Equipo
     * 
     */
    public String mostrarCiclistasAbandonados(){
        OrdenarCiclistasAbandonado();
        String listaCiclistas = "";
        for(Ciclista ciclista : ciclistasAbandonado){
            listaCiclistas = listaCiclistas + ciclista.toString() + '\n';
        }
        return listaCiclistas;
    }

    /**
     * Calcula y devuelve el tiempo acumulado sin abandonar de un ciclista que ha abandonado
     * 
     * @return double 
     */
    private double calcularTiempoAbandonado(Ciclista ciclista){
        return (ciclista.calcularTiempoTotal() - ciclista.getEnergia());
    }

    //ENVIAR CICLISTAS
    /**
     * Envia el primer Ciclista de la lista, asignandole la primera bicicleta de la lista de bicicletas. Después borra ambos para dejar prioridad al siguiente
     * 
     * @return Ciclista 
     */
    public Ciclista enviarCiclista(){
        OrdenarCiclistas();
        OrdenarBicicletas();
        
        Ciclista ciclista;
        Bicicleta bicicleta;
        if (this.ciclistas.isEmpty() || this.bicicletas.isEmpty()){ // COMPRUEBO QUE EL ARRAY DE CICLISTAS O BICICLETAS ESTÉ VACIO
            ciclista = null;
        }
        else{
            this.ciclistas.get(0).setBicicleta(this.bicicletas.get(0)); // ASIGNO UNA BICICLETA AL CICLISTA
            ciclista = this.ciclistas.get(0);
            this.bicicletas.remove(0); //ELIMINO LA PRIMERA BICICLETA DE LA LISTA
            this.ciclistas.remove(0); //ELIMINO EL PRIMER CICLISTA DE LA LISTA
        } 
        return ciclista;
    }

    /**
     * Devuelve el numero total de ciclistas que hay en el equipo
     * 
     * @return Int total de ciclistas en el ArrayList
     */
    public int obtenerTotalCiclistas(){
        return ciclistas.size();
    }

    /**
     * Vuelve a insertar un ciclista y su bicicleta en las listas, cuando termina la carrera
     * 
     * @param Ciclista ciclista que ha terminado la carrera
     */
    public void devolverCiclista(Ciclista ciclista){
        if(ciclista.getBicicleta()!= null){
            this.bicicletas.add(ciclista.getBicicleta());
        }
        ciclista.actualizarAbandono();
        
        if(ciclista != null){ // compruebo que el ciclista no sea nulo antes de añadirlo
            if(ciclista.getAbandonado() == false){
                this.ciclistas.add(ciclista); //LO AÑADO A LA LISTA DE CICLISTAS SI AÚN TIENE ENERGIA
            }
            else {
                this.ciclistasAbandonado.add(ciclista); //LO AÑADO A LA LISTA DE ABANDONADOS SI ABANDONA
            }
        }
       
    }
    
    /**
     * Comprueba que la bicicleta pasada es igual que el nativo comparando sus campos, return false si algún valor distinto
     * 
     * @param Object 
     * @return boolean 
     */
    @Override
    public boolean equals(Object obj){        
        if(this == obj) {
            return true; //Ambos referencian al mismo objeto
        }
        if(!(obj instanceof Bicicleta)) {
            return false; //Tienen diferentes tipos
        }
        Equipo other = (Equipo) obj;
        
        // comparo cada campo significativo de
        // this y other:
        // return true si todos los valores iguales
        // return false si algún valor distinto
        if (getNombre() != other.getNombre()){
            return false;
        }
        return true;
    }
}
