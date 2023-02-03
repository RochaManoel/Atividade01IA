package Engine;

import java.util.Scanner;
import Entities.Queue;
import Entities.Report;
import Entities.Node;

public class Menu {
    public Scanner sc = new Scanner(System.in);
    private int id = 1;
    private boolean repetitiveStates = true;
    public Node nodeStart = new Node(id,0, 3, 3, 0, 0);
    private Queue queue = new Queue();
    private Report report = new Report();

    public Menu(){
        System.out.println("Problema: Missionários e Canibais\n");
        boolean control = true;
        while(control){
            System.out.println("Digite a opção desejada: \n[0] - Encerrar Programa\n[1] - Busca da solução com estados repetidos\n[2] - Busca da solução sem estados repetidos\n[3] - Relatório Geral");
            int option = sc.nextInt();
            switch(option){
                case 0:
                    control = false;
                    System.out.println("\nPrograma Encerrado com sucesso.");
                    break;
                case 1:
                    repetitiveStates = true;
                    engine();
                    break;
                case 2:
                    repetitiveStates = false;
                    engine();
                    break;
                case 3:
                    report.printRecord();
                    break;
                default:
                    System.out.println("\nComando Inválido, Por favor digite novamente!\n");
                    break;
            }
        }
    }
    private void engine(){
        id = 1;
        queue.enqueue(nodeStart);
        breadthFirstSearch(nodeStart);
        queue = new Queue();
    }

    private void breadthFirstSearch(Node node){
        //Construction of the stop condition, which is the transfer of all those involved to the other side of the river with the maintenance of the condition indicated in the problem.
        if(node.validationStateEnd()){
            System.out.println("\nEstado final encontrado com sucesso!\nRelatório de dados:");
            System.out.println("    - Número de criação de Nós: " + this.id);
            System.out.println("    - Características do Nó (Estado Final):");
            System.out.println("        Id: " + node.id);
            System.out.println("        Nível: " + node.level);
            System.out.println("        Quantidade Inicial de Missionários: " + node.quantMissionariesStart);
            System.out.println("        Quantidade Inicial de Canibais    : " + node.quantCannibalsStart);
            System.out.println("        Quantidade Inicial de Missionários: " + node.quantMissionariesEnd);
            System.out.println("        Quantidade Inicial de Missionários: " + node.quantCannibalsEnd);
            System.out.println();
            report.addDates(repetitiveStates, this.id, node.id, node.level);
        }
        else{
            //If the stop condition check returns false, we initialize the generation of new nodes. In sequence we perform a new search on the next element in the queue.
            queue.dequeue();
            nodeGeneration(node);
            breadthFirstSearch(queue.getFirst());
        }
    }

    private void nodeGeneration(Node node){
        int level = node.level + 1;
        int missionariesStart = node.quantMissionariesStart;
        int cannibalsStart = node.quantCannibalsStart;
        int missionariesEnd = node.quantMissionariesEnd;
        int cannibalsEnd = node.quantCannibalsEnd;

        //Validação quando um Missionário é transportado pelo barco
        if(validate(missionariesStart-1, cannibalsStart, missionariesEnd+1, cannibalsEnd)){
            id++;
            queue.enqueue(new Node(id, level, missionariesStart-1, cannibalsStart, missionariesEnd+1, cannibalsEnd));
        }
        //Validação quando dois Missionários são transportados pelo barco
        if(validate(missionariesStart-2, cannibalsStart, missionariesEnd+2, cannibalsEnd)){
            id++;
            queue.enqueue(new Node(id, level, missionariesStart-2, cannibalsStart, missionariesEnd+2, cannibalsEnd));
        }
        //Validação quando um Missionário e um Canibal são transportados pelo barco
        if(validate(missionariesStart-1, cannibalsStart-1, missionariesEnd+1, cannibalsEnd+1)){
            id++;
            queue.enqueue(new Node(id, level, missionariesStart-1, cannibalsStart-1, missionariesEnd+1, cannibalsEnd+1));
        }
        //Validação quando um Canibal é transportado pelo barco
        if(validate(missionariesStart, cannibalsStart-1, missionariesEnd, cannibalsEnd+1)){
            id++;
            queue.enqueue(new Node(id, level, missionariesStart, cannibalsStart-1, missionariesEnd, cannibalsEnd+1));
        }
        //Validação quando dois Canibais são transportados pelo barco
        if(validate(missionariesStart, cannibalsStart-2, missionariesEnd, cannibalsEnd+2)){
            id++;
            queue.enqueue(new Node(id, level, missionariesStart, cannibalsStart-2, missionariesEnd, cannibalsEnd+2));
        }
    }

    private boolean validate(int mStart, int cStart, int mEnd, int cEnd){
        /*
            Conditions that invalidate the creation of new nodes:
            - When it reaches unrealistic values;
            - When on the initial side of the path there is a greater existence of Cannibals and a non-zero amount of Missionaries;
            - When on the final side of the path there is a greater existence of Cannibals and a non-zero amount of Missionaries.

            Obs.: When requested not to repeat states, one more condition is included, the creation of new nodes.
        */
        if(!repetitiveStates && queue.stateInQueue(mStart, cStart, mEnd, cEnd)){
            return false;
        }
        return ((mStart < 0 || cStart < 0) || (mStart < cStart && mStart != 0) || (mEnd < cEnd && mEnd >0)) ? false : true;
    }

}
