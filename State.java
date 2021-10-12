import java.util.ArrayList;

/*Estrutura do estado do automato
 *Possui uma lista chamada de Map para as possíveis transições
 *Nesse Map são salvos os estados possíveis e quais as chaves para cada estado
 *Também é salvo se o estado é de aceitação e uma lista de caracteres para verificar
 *quais são os possíveis caminhos do estado, 
*/
public class State {
    private ArrayList<Map> transitions;
    private boolean acceptance;
    private int label;
    private String paths;

    public State(int q){
        transitions = new ArrayList<Map>();
        this.acceptance = false;
        label = q;
        paths = "";
    }
    
    public void addTransition(String input, State state){
        transitions.add(new Map(state, input));
        paths = paths+input;
    }
    
    private ArrayList<Map> getTransitions(){
        return transitions;
    }
 
    /* A função goTo é a função que retorna o caminho que deve ser seguido no estado
     * a partir da entrada. Basicamente a função da prioridade para estados de aceitação
     * quando o tamanho da entrada é 1 e a transição é possui a chave necessária.
     * A função busca a transição que possui a chave correta e depois verifica se o estado
     * próximo estado vai ser a aceitação ou se possui caminhos válidos até a aceitação,
     * caso não encontre ele busca o próximo estado cuja a transição possua a chave necessária
     * pela cadeia.
     */
    public State goTo(String input){
        State next = null;
        for (Map transition: transitions ){
            if(transition.getKey().equals(input.substring(0,1))){
                if(input.length() <= 1){
                    if(transition.getState().isAcceptance()){
                        next = transition.getState();
                    }         
                }else if(verifyNext(transition.getState(), input.substring(1))){
                    next = transition.getState();                  
                }else if(next == null){
                    next = transition.getState();
                }   
            }
        }

        return next;
    }

    public boolean isAcceptance(){
        return acceptance;
    }

    public void setAcceptance(){
        acceptance = true;
    }

    public int getLabel(){
        return label;
    }

    public String getPaths(){
        return paths;
    }
    
    //Função que verifica os demais caminhos para procurar um aceitável.
    public boolean verifyNext(State q, String input){
        
        if(q.paths.contains(input.substring(0,1))){
            if(input.length() == 1){
                return true;
            }else{
                for (Map transition: q.getTransitions()){
                    if(transition.getKey().equals(input.substring(0,1))){
                        return verifyNext(transition.getState(),input.substring(1));
                    }
                }
            }
        }



        return false;
    }

    //Classe privada para mapear o objeto Map
    private class Map{
        private State state;
        private String key;

        public Map(State state, String key){
            this.state = state;
            this.key = key;
        }

        public String getKey(){
            return key;
        }

        public State getState(){
            return state;
        }
    }

}

