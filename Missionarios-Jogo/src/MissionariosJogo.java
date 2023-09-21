
import busca.Estado;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author facci
 */
public class MissionariosJogo implements Estado {
   
   char barco;
    
    final LinkedList<Integer> direita;
    final LinkedList<Integer> esquerda;
    final List<String> operacoesRealizadas;
    final String op;

    public MissionariosJogo(LinkedList<Integer> direita, LinkedList<Integer> esquerda, char barco, String op) {
        /*this.direita = new LinkedList<Integer>(direita);
        this.esquerda = new LinkedList<Integer>(esquerda);*/
        this.direita = direita;
        this.esquerda = esquerda;
        this.operacoesRealizadas = new ArrayList<>();

        // Copiar os elementos de direita para a nova lista
        
        this.op =  op;
        this.barco = barco;

        System.out.println("lado esquerdo: " + this.esquerda.get(0) + ":" + this.esquerda.get(1));
        System.out.println("lado direito: " + this.direita.get(0) + ":" + this.direita.get(1));
}

    public void mudarMargem(){
        if(this.barco == 'd'){
            this.barco = 'e';
        }else{
            this.barco = 'd';
        }
    }
    
    
    public int contarRepeticoes(String op){
        int contador = 0;
        if(this.operacoesRealizadas.size()>10){
            for (String operacao: this.operacoesRealizadas){
                if (operacao == op){
                    contador++;
                }
            }
            
        }
        return contador;
    }
    
    public boolean ehValido(LinkedList<Integer> origem, LinkedList<Integer> destino,int qtdCanibais,int qtdMissionarios,String op){
        
        LinkedList<Integer>origem2 = new LinkedList<Integer>(origem);
        LinkedList<Integer>destino2 = new LinkedList<Integer>(destino);
        origem2.set(0,origem2.get(0) - qtdCanibais);
        origem2.set(1,origem2.get(1) - qtdMissionarios);
        destino2.set(0,destino2.get(0) + qtdCanibais);
        destino2.set(1,destino2.get(1) + qtdCanibais);
        if(origem2.get(0)<0||origem2.get(1)<0){
            return false;
        }else if(origem2.get(1)<origem2.get(0)){
            return false;
        }else if(destino2.get(1)<destino.get(0)){
            return false;
        }else if (!this.operacoesRealizadas.isEmpty()){
            if(op == this.operacoesRealizadas.get(this.operacoesRealizadas.size()-1)){
                return false;
            }else{
                return true;
            }
            
        }else{
            return true;
        }
       
        
    }
    public void moverMissionario(HashSet<Estado>  visitados){
        if(this.barco == 'd'){
           int qtdMissionarios = 1;
           String op = "levando 1 missionario para Esquerda";
           
           
           if(ehValido(this.direita,this.esquerda,0,qtdMissionarios,op)==true && this.barco == 'd'){
               this.direita.set(1,this.direita.get(1) - qtdMissionarios);
               this.esquerda.set(1, this.esquerda.get(1) + qtdMissionarios);
               mudarMargem();
               MissionariosJogo novo = new MissionariosJogo (this.direita,this.esquerda,
                       this.barco,op);
               
               novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
               novo.operacoesRealizadas.add(op);
               
               if (visitados.contains(novo)==true){
                   System.gc();
               }else{
                   visitados.add(novo);
               }
           }
        }else{
            int qtdMissionarios = 1;
            String op = "levando 1 missionario para direita";
            if(ehValido(this.esquerda,this.direita,0,qtdMissionarios,op)==true && this.barco == 'e'){
                this.direita.set(1,this.direita.get(1) + qtdMissionarios);
                this.esquerda.set(1, this.esquerda.get(1) - qtdMissionarios);
                

                mudarMargem();
                MissionariosJogo novo = new MissionariosJogo (this.direita,this.esquerda,
                        this.barco,"levando 1 missionario para direita");
                novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
                novo.operacoesRealizadas.add(op);
                
                if (visitados.contains(novo)==true){
                    System.gc();
                }else{
                    visitados.add(novo);
                }

            }
        }
       
        
    }
    public void moverMissionarios(HashSet<Estado>   visitados){
        if(this.barco=='d'){
             int qtdMissionarios = 2;
             String op = "levando 2 missionarios para esquerda";
            
            if(ehValido(this.direita,this.esquerda,0,qtdMissionarios,op)==true){
                this.direita.set(1,this.direita.get(1) - qtdMissionarios);
                this.esquerda.set(1, this.esquerda.get(1) + qtdMissionarios);
                mudarMargem();
                MissionariosJogo novo = new MissionariosJogo (this.direita,this.esquerda,
                        this.barco,"levando 2 missionario para Esquerda");
                novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
                novo.operacoesRealizadas.add(op);
               
                if (visitados.contains(novo)==true){
                    System.gc();
                }else{
                    visitados.add(novo);
                }
            }
        
        }else{
            int qtdMissionarios = 2;
            String op = "levando 2 missionarios para direita";
            if(ehValido(this.esquerda,this.direita,0,qtdMissionarios,op)==true){

                this.direita.set(1,this.direita.get(1) + qtdMissionarios);
                this.esquerda.set(1, this.esquerda.get(1) - qtdMissionarios);
                mudarMargem();
                MissionariosJogo novo = new MissionariosJogo (this.direita,this.esquerda,
                        this.barco,"levando 2 missionario para direita");
                novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
                novo.operacoesRealizadas.add(op);
                
                if (visitados.contains(novo)==true){
                    System.gc();
                }else{
                    visitados.add(novo);
                }

            }
            
        }
   
    }
       
    public void moverCanibal(HashSet<Estado>  visitados){
        if (this.barco == 'd'){
            int qtdCanibais = 1;
            String op = "levando 1 canibal para esquerda";
            
            if(ehValido(this.direita,this.esquerda,qtdCanibais,0,op)==true ){
                this.direita.set(0,this.direita.get(0) - qtdCanibais);
                this.esquerda.set(0, this.esquerda.get(0) + qtdCanibais);
                mudarMargem();
                MissionariosJogo novo = new MissionariosJogo (this.direita,this.esquerda,
                        this.barco,"levando 1 canibal para Esquerda");
                novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
                novo.operacoesRealizadas.add(op);
                
                if (visitados.contains(novo)==true){
                    System.gc();
                }else{
                    visitados.add(novo);
                }
            }}else{
            int qtdCanibais = 1;
            String op = "levando 1 canibal para direita";
            
            if(ehValido(this.esquerda,this.direita,qtdCanibais,0,op)==true && this.barco == 'e'){
                this.direita.set(0,this.direita.get(0) + qtdCanibais);
                this.esquerda.set(0, this.esquerda.get(0) - qtdCanibais);
                mudarMargem();
                MissionariosJogo novo = new MissionariosJogo (this.direita,this.esquerda,
                        this.barco,"levando 1 canibal para direita");
                novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
                novo.operacoesRealizadas.add(op);
                
                if (visitados.contains(novo)==true){
                    System.gc();
                }else{
                    visitados.add(novo);
                }
            }
        }
        
        
    }
    public void moverCanibais(HashSet<Estado> visitados){
        if(this.barco=='d'){
            int qtdCanibais = 2;
            String op = "levando 2 canibais para esquerda";
            
            if(ehValido(this.direita,this.esquerda,qtdCanibais,0,op)==true && this.barco == 'd'){
                this.direita.set(0,this.direita.get(0) - qtdCanibais);
                this.esquerda.set(0, this.esquerda.get(0) + qtdCanibais);
                mudarMargem();
                MissionariosJogo novo = new MissionariosJogo (this.direita,this.esquerda,
                        this.barco,"levando 2 canibais para Esquerda");
                novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
                novo.operacoesRealizadas.add(op);
                
                if (visitados.contains(novo)==true){
                    System.gc();
                }else{
                    visitados.add(novo);
                }
            }
        }else{
            int qtdCanibais = 2;
            String op = "levando 2 canibais para direita";
            
            if(ehValido(this.esquerda,this.direita,qtdCanibais,0,op)==true && this.barco == 'e'){
                this.direita.set(0,this.direita.get(0) + qtdCanibais);
                this.esquerda.set(0, this.esquerda.get(0) - qtdCanibais);
                mudarMargem();
                MissionariosJogo novo = new MissionariosJogo (this.direita,this.esquerda,
                        this.barco,"levando 2 canibais para direita");
                novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
                novo.operacoesRealizadas.add(op);
               
                if (visitados.contains(novo)==true){
                    System.gc();
                }else{
                    visitados.add(novo);
                }

            }
            
        }
        
        
    }
   
    public void moverCanibalMissionarios(HashSet<Estado>  visitados){
        if(this.barco=='d'){
            int qtdCanibais = 1;
            int qtdMissionarios = 1;
            String op = "levando 1 canibal e 1 missionario para Esquerda";
            
            if (ehValido(this.esquerda,this.direita,qtdCanibais,qtdMissionarios,op)==true && this.barco=='d'){
                this.direita.set(0,this.direita.get(0) - qtdCanibais);
                this.esquerda.set(0, this.esquerda.get(0) + qtdCanibais);
                this.direita.set(1,this.direita.get(1) - qtdMissionarios);
                this.esquerda.set(1, this.esquerda.get(1) + qtdMissionarios);
                mudarMargem();
                MissionariosJogo novo = new MissionariosJogo (new LinkedList<Integer>(this.direita),new LinkedList<Integer>(this.esquerda),
                        this.barco,"levando 1 canibal e 1 missionario para Esquerda");
                novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
                novo.operacoesRealizadas.add(op);
                
                if (visitados.contains(novo)==true){
                    System.gc();
                }else{
                    visitados.add(novo);
                }

            }
        }else{
            int qtdCanibais = 1;
            int qtdMissionarios = 1;
            
            String op = "levando 1 canibal e 1 missionario para Direita";
            
            if (ehValido(this.esquerda,this.direita,qtdCanibais,qtdMissionarios,op)==true && this.barco=='e'){
                this.direita.set(0,this.direita.get(0) + qtdCanibais);
                this.esquerda.set(0, this.esquerda.get(0) - qtdCanibais);
                this.direita.set(1,this.direita.get(1) + qtdMissionarios);
                this.esquerda.set(1, this.esquerda.get(1) - qtdMissionarios);

                mudarMargem();
                MissionariosJogo novo = new MissionariosJogo (new LinkedList<Integer>(this.direita),new LinkedList<Integer>(this.esquerda),
                        this.barco,"levando 1 canibal e 1 missionario para Direita");
                novo.operacoesRealizadas.addAll(this.operacoesRealizadas); 
                novo.operacoesRealizadas.add(op);
                
                if (visitados.contains(novo)==true){
                    System.gc();
                }else{
                    visitados.add(novo);
                }

            }
            
        }
       
    }
    
    @Override
    public String getDescricao() {
        return "jogo dos missionarios";
    }

    @Override
    public boolean ehMeta() {
        return this.direita.get(0) == 0 && this.direita.get(1) == 0 || this.esquerda.get(0) == 3 && this.esquerda.get(1) == 3;
    }

    @Override
    public int custo() {
        return 1;
    }
    
    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<Estado>(); // a lista de sucessores
        HashSet<Estado> visitadosTmp = new HashSet<Estado>();
        
        moverCanibal(visitadosTmp);
        moverMissionario(visitadosTmp);
        moverCanibalMissionarios(visitadosTmp);
        moverMissionarios(visitadosTmp);
        moverCanibais(visitadosTmp);
        
        
        visitados.clear();
        visitados.addAll(visitadosTmp);
        return visitados;
    }
    public boolean verificaListas(LinkedList<Integer> lista1, LinkedList<Integer>lista2){
        if (lista1.size() != lista2.size()) {
            return false;
        }
        for(int i = 0; i<lista1.size();i++){
            if(lista1.get(i)!=lista2.get(i)){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof MissionariosJogo) {
            MissionariosJogo e = (MissionariosJogo)o;
            
            return /*verificaListas(e.direita,this.direita)&&
                   verificaListas(e.esquerda,this.esquerda)&&
                   this.barco == e.barco;*/
                   this.direita.get(0) == e.direita.get(0)&&
                   this.direita.get(1) == e.direita.get(1)&&
                   this.esquerda.get(0)==e.esquerda.get(0)&&
                   this.esquerda.get(1)==e.esquerda.get(1)&&
                   this.barco == e.barco&&
                   this.operacoesRealizadas.equals(e.operacoesRealizadas);
                   
        }
             
                   
        
        return false;
    }
    
    @Override
   public int hashCode() {
        return (""+this.direita.get(0) + this.direita.get(1) + this.esquerda.get(0)+ this.esquerda.get(1) + this.barco + this.op).hashCode();
    }
    @Override
    public String toString(){
        return "("+this.direita.get(0) + this.direita.get(1) + this.esquerda.get(0)+this.esquerda.get(1)  + this.barco+")"+this.op+"\n";
    }

   
    
}
