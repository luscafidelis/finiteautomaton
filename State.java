import java.util.ArrayList;

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

