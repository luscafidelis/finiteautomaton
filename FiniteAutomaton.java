import java.util.Scanner;

/**
 * main
 */
public class FiniteAutomaton {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);        
        
        int states, terminal, starter, transitions, inputSize;
        String terminals,starters, transition[], acceptance, input;

        State startState = new State(-1);
        
        states = scanner.nextInt();

        State[] q = new State[states];
        for(int i = 0; i < states; i++){
            q[i] = new State(i);
        }

        terminal = scanner.nextInt();
        terminals = scanner.nextLine();

        starter = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < starter; i++)
            startState.addTransition(".", q[i]);
        
        acceptance = scanner.nextLine();
        String acceptanceStates[] = acceptance.split(" ");
        for (int i = 1 ; i < acceptanceStates.length; i++) {
            q[Integer.parseInt(acceptanceStates[i])].setAcceptance();
        }

        transitions = scanner.nextInt();
        scanner.nextLine();
        
        transition =  new String[transitions];
        for(int i = 0; i < transitions; i++){
            transition[i] = scanner.nextLine();
            String t[] = transition[i].split(" ");

            q[Integer.parseInt(t[0])].addTransition(t[1], q[Integer.parseInt(t[2])]);        
        }

        inputSize = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < inputSize; i++){
            input = "."+scanner.nextLine();

            State currentQ = startState;                            
            while(!input.isEmpty() && !input.equals("-") && currentQ != null){
                String c = input.substring(0, 1);
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
