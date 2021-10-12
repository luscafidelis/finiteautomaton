import java.util.Scanner;

/**
 * Arquivo base do programa que simula finitos automatos
 */

public class FiniteAutomaton {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);        
        
        //Variavei para salvar os dados do trabalho
        int states, terminal, starter, transitions, inputSize;
        String terminals, transition[], acceptance, input;

        //Estado "inicial"
        State startState = new State(-1);
        
        states = scanner.nextInt();

        //Inicialização dos estados
        State[] q = new State[states];
        for(int i = 0; i < states; i++){
            q[i] = new State(i);
        }

        //Leitura dos terminais
        terminal = scanner.nextInt();
        terminals = scanner.nextLine();

        //Leitura dos estados iniciais
        starter = scanner.nextInt();
        scanner.nextLine();

        //Adicionando o estado inicial ao falso inicio
        for(int i = 0; i < starter; i++)
            startState.addTransition(".", q[i]);
        
        //Leitura e definição dos estados de aceitação
        acceptance = scanner.nextLine();
        String acceptanceStates[] = acceptance.split(" ");
        for (int i = 1 ; i < acceptanceStates.length; i++) {
            q[Integer.parseInt(acceptanceStates[i])].setAcceptance();
        }

        //Leitura das transições presentes
        transitions = scanner.nextInt();
        scanner.nextLine();
        
        transition =  new String[transitions];
        for(int i = 0; i < transitions; i++){
            transition[i] = scanner.nextLine();
            String t[] = transition[i].split(" ");

            q[Integer.parseInt(t[0])].addTransition(t[1], q[Integer.parseInt(t[2])]);        
        }

        //Leitura do tamanho da entrada
        inputSize = scanner.nextInt();
        scanner.nextLine();

        //Leitura das produções
        for(int i = 0; i < inputSize; i++){
            input = "."+scanner.nextLine();

            //Inicialização do automato
            State currentQ = startState;                            
            while(!input.isEmpty() && !input.equals("-") && currentQ != null){
                //Com base na entrada o automato é percorrido
                //Depois a primeira letra da string é removida
                currentQ = currentQ.goTo(input);            
                input = input.substring(1);
            }
            if(currentQ != null && currentQ.isAcceptance())
                System.out.println("aceita");
            else
                System.out.println("rejeita");
        }


    }

    
}
