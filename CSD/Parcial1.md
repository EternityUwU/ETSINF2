#### TEORÍA
Respecto	a	las	diferencias	entre	la	programación	concurrente	y	la	programación	secuencial:

|||
|--|--|
|1. Los	programas	secuenciales	pueden	generar	condiciones	de	carrera,	al	igual	que	 los	programas	concurrentes.| F
|2. Un	proceso	con	varios	hilos	de	ejecución,	ejecutado	con	un	procesador	con	un	único	núcleo	que	ofrece	multiprogramación,	es	un	ejemplo	de	aplicación concurrente.| V

Respecto	a	la	programación	concurrente	en	Java:
|||
|--|--|
|3. Si	un	método	se	etiqueta	con	synchronized y	se	invoca	desde	otro	método	de	la	misma	instancia	etiquetado	también	con	synchronized,	provoca	que	el	hilo	se	espere	a	sí	mismo	(interbloqueo).|F

Sobre	el	problema	de	la	sección	crítica:	
|||
|--|--|
|4. Una	solución	correcta	para	el	problema	de	la	sección	crítica		debe	cumplir	las	propiedades	de	exclusión	mutua,	retención	y	espera,	no	expulsión	y	espera	circular |F
|5. El	problema	de	la	sección	crítica	aparece	cuando	varios	hilos	comparten	un	objeto	inmutable	(constante)	al	que	todos	los	hilos	necesitan	acceder	de	forma	simultánea.|F
|6. El	problema	de	la	sección	crítica	se	soluciona	empleando	correctamente	mecanismos	de	sincronización,	como	por	ejemplo	monitores,	semáforos	y	cerrojos.| V
|7. Si	empleamos	un	único	cerrojo	para	proteger	las	secciones	críticas	de	cierto	objeto,	se	permitirá	como	máximo	un	hilo	ejecutando	dichas secciones	críticas |V
|8. En	una	sección	crítica	protegida	por	un	lock,	el	protocolo	de	salida	libera	el	lock,	permitiendo	que	todos	los	hilos	bloqueados	en	la	entrada	accedan	al	objeto|F

Sobre	el	concepto	de	monitor	y	sus	variantes:
|||
|--|--|
|9. Suponiendo	que	haya	algún	hilo	suspendido	en	la	variable	condición		c ,	un	monitor	que	siga	el	modelo	de	Hoare	suspende	al	hilo	que	ha	invocado	a	c.notify() y	activa	a	uno	de	los	hilos	que	llamó	antes	a	c.wait().|V
|10. El	lenguaje	Java	proporciona	por	defecto	monitores	de	tipo	"Lampson/Redell".	| V
|11. El	monitor	Lampson-Redell	garantiza	que	tras	una	operación	notify(),		el	hilo	reactivado	encuentra	el	estado	del	monitor	exactamente	igual	que	estaba	cuando	se	ejecutó	dicho	notify.	| F

Dado el siguiente programa Java: 
```java

public class GreatBoss extends Thread {
    rotected int workers = 0;
    public GreatBoss(int workers) {this.workers = workers;}

    public void myAction(){
        for (int i = 0; i<= workers; i++){
          System.out.println("Preparing worker " + i + " for: " +
             Thread.currentThread().getName());
          new Thread(new Runnable(){
              public void run(){
                 System.out.println("Task finished"); }
          });
        }
     }
 
    public void run() {
        myAction();
        try{Thread.sleep(workers*1000);}
        catch(InterruptedException ie){ie.printStackTrace();};
        System.out.println(Thread.currentThread().getName() +  "done");
    }

    public static void main(String[] argv) {
        for (int i=0; i<10; i++){
            GreatBoss boss = new GreatBoss(i);
            if (i<5) { 
                boss.setName("Chief" + i);
                boss.start();
            }
        }
    }
 }

```
|||
|--|--|
|12. Al	ejecutarlo,	veremos	por	pantalla al	menos una línea	con	la	sentencia Task finishe|F
|13. Al	ejecutarlo,	veremos	por	pantalla, entre otras cosas, la	línea	Preparing worker 4 for: Chief4|V
|14. Con este código se	crearán	un	total de 10	hilos,	aparte	de	main.	| F
|15. Al	ejecutarlo,	veremos	por	pantalla, entre otras cosas, 5 líneas con la palabra `done` V
|16. Se	requiere etiquetar	el	método	myAction() con	la	etiqueta synchronized para evitar las condiciones de carrera. |F

![](https://media.discordapp.net/attachments/705068953315311717/825436417164705802/unknown.png)
|||
|--|--|
|17. Se encuentran en interbloqueo los procesos H5 y H7. |F 
|18. El sistema presenta al menos una secuencia segura.| V
|19. Si el proceso H4 solicita una instancia de cualquiera de los recursos del sistema (sin liberar las dos instancias que ya tiene asignadas del recurso R2), se producirá un interbloqueo y ningún proceso podrá acabar.| V 
|20. En este GAR se dan todas las condiciones de Coffman.| V

Sobre	las	condiciones	de	Coffman	y	las	situaciones	de	interbloqueo:
|||
|--|--|
|21. Las condiciones de Coffman permiten diseñar sistemas que cumplan con todas ellas, para así garantizar que no se producirán interbloqueos. |F
| 22. Una de las condiciones de Coffman consiste en solicitar todos los recursos requeridos inicialmente, de modo que los hilos se bloquean (retención y espera) si existe un conflicto en las peticiones.| F
| 23. Las situaciones de interbloqueo pueden prevenirse asignando los recursos de manera que nunca se genere un ciclo dirigido. |V 
|24. Una de las condiciones de Coffman consiste en que los recursos asignados pueden ser expropiados.|F
|25. En esta solución se puede sobrepasar el máximo número de piezas blancas o azules en los cestos, puesto que se incrementan los contadores antes de comprobar si caben.| F 
---
Un taller de una joyería quiere montar collares de perlas solo blancas, solo azules o combinados de perlas blancas y azules. Para ello dispone de 5 encargados y 2 cestos, uno para cada color de perla, con capacidad limitada . Para organizar la producción se decide que un encargado será el proveedor de perlas blancas, otro encargado proveerá las perlas azules y el resto de encargados se destinará al montaje de cada tipo de collar. El monitor `GestorDePerlas`, gestiona el número de perlas almacenadas en los cestos. Hay un hilo asociado a cada encargado. Los encargados de proveer perlas, se encargan de obtener una perla y almacenarla en el cesto correspondiente utilizando los métodos `AñadirBlanca` ó `AñadirAzul`. El resto de encargados solicitan al monitor el número de perlas de cada color que necesitan para montar el collar utilizando el método `SolicitarPedido`. Analice la siguiente propuesta para el monitor `GestorDePerlas`
|||
|--|--|
|26. El atributo PedidoEnCurso es necesario para proporcionar exclusión mútua en el acceso al método SolicitarPedido .| F
| 27. La solución no es correcta porque la invocación al método notifyAll en AñadirBlanca y AñadirAzul, debería ser la última instrucción en ambos métodos. |F
| 28. El calificativo synchronized en los métodos AñadirBlanca y AñadirAzul, no es necesario ponerlo, ya que sólo hay un hilo que añade piezas blancas y un hilo que añade piezas azules.| F
| 29. El atributo PedidoEnCurso se utiliza para conseguir que cuando un pedido P1 está esperando a que se completen las piezas solicitadas, los nuevos pedidos no se atenderán hasta que se complete P1.| V
| 30. La solución propuesta para el monitor es correcta, y sincroniza adecuadamente según el enunciado, los proveedores de perlas y la gestión de los pedidos que realizan los montadores.|V