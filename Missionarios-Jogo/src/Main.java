
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.MostraStatusConsole;
import busca.Nodo;
import java.util.LinkedList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author facci
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<Integer> direita = new LinkedList<Integer>();
        LinkedList<Integer> esquerda = new LinkedList<Integer>();
        direita.add(3);
        direita.add(3);
        esquerda.add(0);
        esquerda.add(0);
        
        MissionariosJogo estadoInicial = new MissionariosJogo(direita,esquerda,'d',"estado inicial");
        
        // chama busca em largura
        System.out.println("busca em ....");
        
        Nodo n = new BuscaLargura(new MostraStatusConsole()).busca(estadoInicial);
        
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
        
    }
    
}
