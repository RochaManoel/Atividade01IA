package Entities;

public class Report {
    public int gerateSearchOne;
    public int gerateSearchTwo;
    public int idSearchOne;
    public int idSearchTwo;
    public int level;

    public Report(){
        this.gerateSearchOne = 0;
        this.gerateSearchTwo = 0;
        this.idSearchOne = 0;
        this.idSearchTwo = 0;
        this.level = 0;
    }

    //Validates whether both queries presented were produced.
    public boolean validateBFS(){
        return ((idSearchOne == 0) || (idSearchTwo == 0) || (gerateSearchOne == 0) || (gerateSearchTwo == 0)) ? false : true;
    }

    public void addDates(boolean control, int generate, int id, int level){
        if(control){
            this.gerateSearchOne = generate;
            this.idSearchTwo = id;
        }
        else{
            this.gerateSearchTwo = generate;
            this.idSearchOne = id;
        }
        this.level = level;
    }

    public void printRecord(){
        if(validateBFS()){
            System.out.println("\nAlgoritmo utilizado para a implementação: Busca em Largura (BFS)");
            System.out.println("     - Algoritmo Completo;");
            System.out.println("     - Algoritmo Ótimo;");
            System.out.println("     - Algoritmo de consumo de tempo exponencial;");
            System.out.println("     - algoritmo de consumo de memória exponencial.\n");
            System.out.println("Proposição acerca da verificação de estados repetidos:");
            System.out.println("     - Busca com estados repetitivos:");
            System.out.println("         Quantidade de estados gerados: " + this.gerateSearchOne + "\n         Id do estado final: " + this.idSearchOne + "\n         Nível: " + this.level);
            System.out.println("     - Busca sem estados repetitivos:");
            System.out.println("         Quantidade de estados gerados: " + this.gerateSearchTwo + "\n         Id do estado final: " + this.idSearchTwo + "\n         Nível: " + this.level);
            System.out.println("\n     Pode-se concluir que a verificação apresentou uma melhora na geração de estados e produção destes.");
            System.out.println("Logo, sabe-se que para desempenho máximo é sim válido a verificação da existência de estados repetitivos.");
        }
        else{
            System.out.println("\nNecessário realizar buscas, após isso retorne, Obrigado!\n");
        }
    }
}
