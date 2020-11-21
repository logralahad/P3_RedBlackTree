/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.black.tree;

/**
 *
 * @author logra
 */
public class Arbol{
    
    NodoArbol raiz;
    NodoArbol NIL = NIL();
    boolean bandera = false;
    
    public void insertar(int dato) {
        NodoArbol nuevo = crearNodo(dato);
        NodoArbol y = null;
        NodoArbol x = this.raiz;

        while (x != NIL) {
            y = x;
            if (nuevo.dato < x.dato){
                x = x.izq;
            }else{
                x = x.der;
            }
        }

        nuevo.padre = y;

        if (y == null) {
            raiz = nuevo;
        }else if(nuevo.dato < y.dato){
            y.izq = nuevo;
        }else{
            y.der = nuevo;
        }

        if (nuevo.padre == null) {
            nuevo.color = 0;
            return;
        }

        if (nuevo.padre.padre == null) return;
        arreglarInsertar(nuevo);
    }
    
    public void eliminar(int dato){
        NodoArbol buscar =  encontrar(raiz, dato);
        if(buscar != null){
            bandera = true;
            NodoArbol aux = new NodoArbol();
            NodoArbol aux2 = new NodoArbol();
            
            aux2 = buscar;
            int aux2color = aux2.color;
            
            if(buscar.izq == NIL){
                aux = buscar.der;
                traspaso(buscar, buscar.der);
            }else if(buscar.der == NIL){
                aux = buscar.izq;
                traspaso(buscar, buscar.izq);
            }else{
                aux2 = minimo(buscar.der);
                aux2color = aux2.color;
                aux = aux2.der;
                
                if(aux2.padre == buscar){
                    aux.padre = aux2;
                }else{
                    traspaso(aux2, aux2.der);
                    aux2.der = buscar.der;
                    aux2.der.padre = aux2;
                }
                
                traspaso(buscar, aux2);
                aux2.izq = buscar.izq;
                aux2.izq.padre = aux2;
                aux2.color = buscar.color;
            }
            System.out.print("\nSe elimino el numero " + dato + "\n");
            if(aux2color == 0) arreglarEliminar(aux);  
        }else{
            System.out.print("\nNo se elimino el numero " + dato + " porque no existe\n");
        }
    }

    public void rotacionIzquierda(NodoArbol nuevo){
        NodoArbol aux = nuevo.der;
        nuevo.der = aux.izq;
        
        if (aux.izq != NIL){
            aux.izq.padre = nuevo;
        }
        
        aux.padre = nuevo.padre;
        if(nuevo.padre == null){
            raiz = aux;
        }else if(nuevo == nuevo.padre.izq){
            nuevo.padre.izq = aux;
        }else{
            nuevo.padre.der = aux;
        }
        
        aux.izq = nuevo;
        nuevo.padre = aux;
    }

    public void rotacionDerecha(NodoArbol nuevo){
        NodoArbol aux = nuevo.izq;
        nuevo.izq = aux.der;
        
        if (aux.der != NIL) {
            aux.der.padre = nuevo;
        }
        
        aux.padre = nuevo.padre;
        if (nuevo.padre == null) {
            raiz = aux;
        }else if(nuevo == nuevo.padre.der){
            nuevo.padre.der = aux;
        }else{
            nuevo.padre.izq = aux;
        }
        
        aux.der = nuevo;
        nuevo.padre = aux;
    }
    
    public void traspaso(NodoArbol aux, NodoArbol aux2){
        if(aux.padre == null){
            raiz = aux2;
        }else if(aux == aux.padre.izq){
            aux.padre.izq = aux2;
        }else{
            aux.padre.der = aux2;
        }
        
        aux2.padre = aux.padre;
    }
    
    public void arreglarInsertar(NodoArbol nuevo) {
        NodoArbol tio;
        
        while (nuevo.padre.color == 1){
            if (nuevo.padre == nuevo.padre.padre.der) {
                tio = nuevo.padre.padre.izq;
                
                if (tio.color == 1) {
                    tio.color = 0;
                    nuevo.padre.color = 0;
                    nuevo.padre.padre.color = 1;
                    nuevo = nuevo.padre.padre;
                }
                else{
                    if (nuevo == nuevo.padre.izq){
                        nuevo = nuevo.padre;
                        rotacionDerecha(nuevo);
                    }
                    
                    nuevo.padre.color = 0;
                    nuevo.padre.padre.color = 1;
                    rotacionIzquierda(nuevo.padre.padre);
                }
            }
            
            else{ 
                tio = nuevo.padre.padre.der;

                if (tio.color == 1){
                    tio.color = 0;
                    nuevo.padre.color = 0;
                    nuevo.padre.padre.color = 1;
                    nuevo = nuevo.padre.padre;
                }
                else{                   
                    if (nuevo == nuevo.padre.der) {
                        nuevo = nuevo.padre;
                        rotacionIzquierda(nuevo);
                    }
                    
                    nuevo.padre.color = 0;
                    nuevo.padre.padre.color = 1;
                    rotacionDerecha(nuevo.padre.padre);
                }
            }
            if (nuevo == raiz) break;
        }
        raiz.color = 0;
    }
    
    public void arreglarEliminar(NodoArbol nuevo){
        NodoArbol tio;
        
        while(nuevo != raiz && nuevo.color == 0){
            if(nuevo == nuevo.padre.izq){
                tio = nuevo.padre.der;
                
                if(tio.color == 1){
                    tio.color = 0;
                    nuevo.padre.color = 1;
                    rotacionIzquierda(nuevo.padre);
                    tio = nuevo.padre.der;
                }
                
                if(tio.izq.color == 0 && tio.der.color == 0){
                    tio.color = 1;
                    nuevo = nuevo.padre;
                }else{
                    if(tio.der.color == 0){
                        tio.izq.color = 0;
                        tio.color = 1;
                        rotacionDerecha(tio);
                        tio = nuevo.padre.der;
                    }
                    
                    tio.color = nuevo.padre.color;
                    nuevo.padre.color = 0;
                    tio.der.color = 0;
                    rotacionIzquierda(nuevo.padre);
                    nuevo = raiz;
                }               
            }
            
            else{
                tio = nuevo.padre.izq;
                
                if(tio.color == 1){
                    tio.color = 0;
                    nuevo.padre.color = 1;
                    rotacionDerecha(nuevo.padre);
                    tio = nuevo.padre.izq;
                }
                
                if(tio.der.color == 0 && tio.izq.color == 0){
                    tio.color = 1;
                    nuevo = nuevo.padre;
                }else{
                    if(tio.izq.color == 0){
                        tio.der.color = 0;
                        tio.color = 1;
                        rotacionIzquierda(tio);
                        tio = nuevo.padre.izq;
                    }
                    
                    tio.color = nuevo.padre.color;
                    nuevo.padre.color = 0;
                    tio.izq.color = 0;
                    rotacionDerecha(nuevo.padre);
                    nuevo = raiz;
                }
            }
        }
        nuevo.color = 0;
    }
    
    public NodoArbol minimo(NodoArbol aux){
        while(aux.izq != NIL){
            aux = aux.izq;
        }
        
        return aux;
    }
    
    public void preOrden(NodoArbol aux){
        if (aux != NIL) {
            System.out.print(aux.dato + " ");
            preOrden(aux.izq);
            preOrden(aux.der);
        } 
    }

    public void inOrden(NodoArbol aux){
        if (aux != NIL) {
            inOrden(aux.izq);
            System.out.print(aux.dato + " ");
            inOrden(aux.der);
        } 
    }

    public void postOrden(NodoArbol aux){
        if (aux != NIL) {
            postOrden(aux.izq);
            postOrden(aux.der);
            System.out.print(aux.dato + " ");
        } 
    }

    public NodoArbol encontrar(NodoArbol aux, int dato){
        if (aux == null || dato == aux.dato) return aux;
        if (dato < aux.dato) return encontrar(aux.izq, dato);
        return encontrar(aux.der, dato);
    }
    
    public void buscarNumero(int dato){
	NodoArbol aux = encontrar(raiz, dato);
        if(aux == null){
            System.out.println("No se encontro el numero " + dato);
        }else{
            System.out.println("Se encontro el numero " + aux.dato);
        }
    }
    
    public NodoArbol crearNodo(int dato){
        NodoArbol nuevo = new NodoArbol();
        nuevo.padre = null;
        nuevo.dato = dato;
        nuevo.izq = NIL;
        nuevo.der = NIL;
        nuevo.color = 1;
        
        return nuevo;
    }
    
    public NodoArbol NIL(){
        NIL = new NodoArbol();
        NIL.color = 0;
        NIL.izq = null;
        NIL.der = null;
        raiz = NIL;
        
        return NIL;
    }
    
    
    public void recorrer(Recorrido tipo){
        switch(tipo){
            case INORDER:
                inOrden(this.raiz);
                System.out.print("\n");
                break;
            case POSTORDER:
                postOrden(this.raiz);
                System.out.print("\n");
                break;
            case PREORDER:
                preOrden(this.raiz);
                System.out.print("\n");
                break;
        }
    }
    
}