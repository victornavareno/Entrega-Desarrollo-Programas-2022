import java.util.*;
/**
 * Representa a la clase Organizacion, capaz de gestionar el campeonato.
 * 
 * @author Víctor
 * @author Andrés  
 * @author Elena
 * @version 1
 */
public class Organizacion
{
    //ESTRUCTURAS DE DATOS IMPLEMENTADAS:
    private List<Etapa> etapas;
    private List<Equipo> equipos;
    private List<Ciclista> ciclistasCarrera;
    
    //ATRIBUTOS COMPARATOR
    Comparator<Etapa> comparadorEtapa;
    
    
    /**
     * Constructor parametrizado de la clase Organizacion
     * 
     * @param Comparator<Etapa> con la clase comparator que utilizaremos para ordenar el array de etapas
     */
    public Organizacion(Comparator<Etapa> comparadorEtapa)
    {
        etapas = new ArrayList<Etapa>();
        equipos = new ArrayList<Equipo>();
        ciclistasCarrera = new ArrayList<Ciclista>();
        
        //INIT COMPARATOR:
        this.comparadorEtapa = comparadorEtapa;
    }
    
        public Organizacion()
    {
        etapas = new ArrayList<Etapa>();
        equipos = new ArrayList<Equipo>();
        ciclistasCarrera = new ArrayList<Ciclista>();
        
        //INIT COMPARATOR:
    }


    /**
     * Gestiona todo el campeonato de ciclistas
     * 
     */
    //MÉTODO DE CONTROL DEL CAMPEONATO:        
    public void gestionarCampeonato(){
        //ORDENA Y MUESTRA ETAPAS:
        ordenarEtapas();
        mostrarEtapas();
        
        //MUESTRA EQUIPOS:
        mostrarEquipos();
        
        if(!(ciclistasCarrera.size()<=1)){
                for(Etapa etapa : etapas){
                    gestionarCarrera(etapa);
                    mostrarCarrera();
                }
            }
        mostrarFinCampeonato();
    }
    
    //FUNCIONALIDAD CLASE ORGANIZACIÓN =
    public void gestionarCarrera(Etapa etapa){
        for(Equipo equipo : equipos){     // CARGO LOS CICLISTAS DE CADA EQUIPO EN ciclistasCarrera
            enviarCiclistasCarrera(equipo);
        } 
        
        for(int i = 0; i<ciclistasCarrera.size(); i++){
            ciclistasCarrera.get(i).hacerCarrera(etapa); // HAGO LA CARRERA CON CADA CICLISTA DEL ARRAY
        }
        
        for(Ciclista ciclista : ciclistasCarrera){
            devolverCiclistasCarrera(ciclista); // DEVUELVO CADA CICLISTA A SU EQUIPO
        }
    }
    
    //CARGA CICLISTAS DE LOS EQUIPOS PARA CARRERA:
    public void enviarCiclistasCarrera(Equipo equipo){
        boolean finalCiclistas = false;
        while(!finalCiclistas){
            ciclistasCarrera.add(equipo.enviarCiclista());
            if (ciclistasCarrera.get(0).equals(null)){
                ciclistasCarrera.remove(0);
                finalCiclistas = true; // ROMPO WHILE 
            }
        }
    }
    
    //DEVOLVER CICLISTAS A LOS EQUIPOS TRAS CARRERA:
    public void devolverCiclistasCarrera(Ciclista ciclista){
        for(int i= 0; i< equipos.size(); i++){
             if(ciclista.getEquipo().equals(equipos.get(i))){
                equipos.get(i).devolverCiclista(ciclista);
                ciclistasCarrera.remove(ciclista);
            }
        }
       
    }
    
    
    //CARGA EQUIPOS
    /**
     * Añade un equipo al ArrayList de equipos
     * 
     * @param Objeto Equipo a añadir
     */
    public void inscribirEquipo(Equipo equipo)
    {
        equipos.add(equipo);
    }
    
    /**
     * Asigna un ArrayList completo de equipos
     * 
     * @param equipos ArrayList<Equipo> con los equipos que participan
     */
    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }
    
    //CARGA ETAPAS
    /**
     * Añade una etapa al ArrayList de etapas
     * 
     * @param Objeto Etapa a añadir
     */
    public void anadirEtapa(Etapa etapa){
        etapas.add(etapa);
    }
    
    /**
     * Asigna un ArrayList completo de etapas
     * 
     * @param etapas ArrayList<Etapa> con las etapas de la competición
     */
    public void setEtapas(ArrayList<Etapa> etapas) {
        this.etapas = etapas;
    }
    
    //CARGA CICLISTAS
    /**
     * Añade un ciclista al ArrayList de ciclistas
     * 
     * @param Objeto Ciclista a añadir
     */
    public void anadirCiclistaCarrera(Ciclista ciclista){
        ciclistasCarrera.add(ciclista);
    }
    
    /**
     * Asigna un ArrayList completo de ciclistas
     * 
     * @param ciclistasCarrera ArrayList<Ciclista> con los ciclistas que participarán en la carrera
     */
    public void setCiclistasCarrera(ArrayList<Ciclista> ciclistasCarrera) {
        this.ciclistasCarrera = ciclistasCarrera;
    }
    
    //MÉTODOS DE ORDENACION DE ARRAYLIST CON COMPARATOR:
    /**
     * Ordena el ArrayList de etapas segun criterios del comparadorEtapa
     * 
     * @return ArrayList<Etapa> con las etapas ordenadas 
     */
    public void ordenarEtapas(){
        Collections.sort(this.etapas, comparadorEtapa);
    }
    
    //ENVIAR Y DEVOLVER CICLISTAS PARA CADA CARRERA, Y CELEBRAR LA ETAPA:
    
    
    
    //SALIDA POR PANTALLA:
    public void mostrarEtapas(){
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("||||||||||||||||||| ETAPAS DEL CAMPEONATO |||||||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        for(Etapa etapa : etapas){
            System.out.println(etapa.toString());
        }
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
    }
    
    public void mostrarEquipos(){
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%% EQUIPOS DEL CAMPEONATO %%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        
        for(Equipo equipo : equipos){
            System.out.println(equipo.toString().toUpperCase());
            System.out.println("\n");
            
            equipo.mostrarCiclistas();
        }
    }
    
    public void mostrarCarrera(){
        for(int i = 0; i<etapas.size(); i++){
            System.out.println("************************************");
            System.out.println("*** CARRERA<"+ (i+1) + "> EN <etapa:"+ etapas.get(i).getNombre() + "> <dificultad: " + etapas.get(i).getDificultad() + "> <distancia: " + etapas.get(i).getDistancia()+")> ***");   
            System.out.println("************************************");
            System.out.println("************************************");
            System.out.println("******************************** Ciclistas que van a competir en " + etapas.get(i).getNombre() + " *******************************");
            System.out.println("**********************************************************************************************************");
            for(Ciclista ciclista :ciclistasCarrera){
                System.out.println("<ciclista:"+ciclista.getNombre()+"> <energía: "+ Math.round(ciclista.getEnergia()*100.0) / 100.0 + "> <habilidad: "+ ciclista.getHabilidad() + "> <tiempo acumulado sin abandonar:"+ Math.round(ciclista.calcularTiempoTotal() * 100.0)/100.0 + "> <abandonado:"+ ciclista.getAbandonado() + ">");
            }
            
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++++++++ Comienza la carrera en " + etapas.get(i).getNombre() + " ++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //TODO: ORDENAR CICLISTAS CON COMPARATOR
            
            for (int j = 0; j<ciclistasCarrera.size(); j++){
                boolean tieneBicicleta = ciclistasCarrera.get(j).tieneBicicleta();
                String tiene;
                if(tieneBicicleta){
                    tiene = "con bicicleta";
                }
                else {
                    tiene = "sin bicicleta";
                }
                
                System.out.println("@@@ ciclista " + (j+1) + "de "+ ciclistasCarrera.size());
                System.out.println();
                System.out.println("<ciclista:" + ciclistasCarrera.get(j).getNombre() + "> <energía: " + ciclistasCarrera.get(j).getEnergia() + "> <habilidad: " + ciclistasCarrera.get(j).getHabilidad() + "> <tiempo acumulado sin abandonar: " + ciclistasCarrera.get(j).calcularTiempoTotal() + "> <abandonado:" + ciclistasCarrera.get(j).getAbandonado() + "> " + tiene );
                if (tieneBicicleta){
                    System.out.println(ciclistasCarrera.get(j).getBicicleta().toString() + ")> en etapa " + etapas.get(i).getNombre());
                    System.out.println("+++ Con estas condiciones el ciclista " + ciclistasCarrera.get(j).getNombre() + " con la bicicleta " + ciclistasCarrera.get(j).getBicicleta().getNombre() + " alcanza una velocidadad de " + ciclistasCarrera.get(j).getBicicleta().calcularVelocidad(ciclistasCarrera.get(j).getHabilidad(), etapas.get(i).getDificultad()) + " km/hora +++");
                    System.out.println("+++ " + ciclistasCarrera.get(j).getNombre() +" termina la etapa en " + ciclistasCarrera.get(j).obtenerTiempoEtapa(etapas.get(i)) + " minutos +++");
                    System.out.println("+++ La energía del ciclista "+ ciclistasCarrera.get(j).getNombre() + " tras la carrera es " + Math.round(ciclistasCarrera.get(j).getEnergia()*100.0)/100.0 + " +++");
                    System.out.println("@@@");
                }
            }
             
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+++++++++++++++++ Clasificación final de la carrera en "+ etapas.get(i).getNombre() +" ++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            for(int k = 0; k<ciclistasCarrera.size(); k++) { //BUCLE FOR, NECESITO EL INDICE
                // TODO: ORDENAR ciclistasCarrera POR TIEMPO CON COMPARATOR
                System.out.println("@@@ Posición(" + (k+1) + "): "+ ciclistasCarrera.get(k).getNombre() + " - Tiempo: " + Math.round(ciclistasCarrera.get(k).obtenerTiempoEtapa(etapas.get(i))*100.0)/100.0 +" minutos @@@");
            }
        }
    }
    
    
    public void mostrarFinCampeonato(){
        System.out.println("****************************************************");
        System.out.println("**************** FIN DEL CAMPEONATO ****************");
        System.out.println("****************************************************");
        System.out.println("********** CLASIFICACIÓN FINAL DE CICLISTAS **********");
        System.out.println("****************************************************");
        
        //TODO: ORDENAR ciclistasCarrera POR TIEMPO TOTAL COMPARATOR
        for(int i = 0; i < ciclistasCarrera.size(); i++){
            System.out.println("@@@ Posición(" + (i+1) + "): " + ciclistasCarrera.get(i).getNombre() + " - Tiempo Total: " + Math.round(ciclistasCarrera.get(i).calcularTiempoTotal()*100.0)/100.0 + " @@@");
            for (int j = 0; j< etapas.size(); j++){
                System.out.println("Carrera(" + etapas.get(j).getNombre() + ") - Tiempo: " + Math.round(ciclistasCarrera.get(i).obtenerTiempoEtapa(etapas.get(j)) * 100.0)/100 + " minutos");
            }
        }
        System.out.println("\n");
        
        //TODO: ORDENAR EQUIPOS POR MEDIA MINUTOS EN ORDEN ASCENDENTE
        System.out.println("****************************************************");
        System.out.println("********** CLASIFICACIÓN FINAL DE EQUIPOS **********");
        System.out.println("****************************************************");
        for(int k = 0; k < equipos.size(); k++){
            System.out.println("@@@ Posición( +" + (k+1) + "+) " + equipos.get(k).getNombre() + " con " + Math.round(equipos.get(k).calcularTiempoTotal()/ etapas.size()*100.0)/100 + " minutos de media @@@");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println("%%% "+ equipos.get(k).getNombre().toUpperCase() + " %%% Media Minutos de Ciclistas sin abandonar " + Math.round(equipos.get(k).calcularTiempoTotal()/ etapas.size()*100.0)/100 + " %%%");
            System.out.println("\n");
            
            equipos.get(k).mostrarCiclistas();
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        }
    }
    

}
