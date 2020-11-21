/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.black.tree;
import java.util.Random;

/**
 *
 * @author logra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Arbol groot = new Arbol();
        Random rand = new Random();
        
        int dato = 0, azar = 0;        
        int tam = rand.nextInt(10) + 8;
        System.out.print("Insertando " + tam + " elementos: ");
        
        for(int i = 0; i < tam; ++i){
            dato = rand.nextInt(100001);
            groot.insertar(dato);
        }
        
        groot.recorrer(Recorrido.INORDER);
        
        azar = rand.nextInt(100001);
        System.out.print("\nEliminando " + azar + " del arbol");
        groot.eliminar(azar);
        System.out.print("Arbol actualizado: ");
        groot.recorrer(Recorrido.INORDER);
        
        System.out.print("\nEliminando " + dato + " del arbol");
        groot.eliminar(dato);
        System.out.print("Arbol actualizado: ");
        groot.recorrer(Recorrido.INORDER);
        
        System.out.print("\nRecorrido preOrden: ");
        groot.recorrer(Recorrido.PREORDER);
        
        System.out.print("\nRecorrido inOrden: ");
        groot.recorrer(Recorrido.INORDER);
        
        System.out.print("\nRecorrido postOrden: ");
        groot.recorrer(Recorrido.POSTORDER);
        
        System.out.print("\n");
        groot.buscarNumero(azar);
    }
    
}
