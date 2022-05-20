import java.util.ArrayList;

public class TransitTable extends FSM {
    ArrayList<Transition> transitions;

    public TransitTable(String _word) {
        super(_word);
        transitions = new ArrayList<Transition>();
    }

    @Override
    public boolean scanner() {
        Event event;
        buildTransitionTable();
        int count = 0;
        do {
            if (count < text.length())
                event = recognizeEvent(text.charAt(count));
            else
                event = Event.EOS;
            handleEvent(event);
            count++;
        }
        while ((state != State.Success) && (state != State.Error));
        if (state == State.Success)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    @Override
    public Event recognizeEvent(char ch) {
        Event temp;
        switch (ch) {
            case '+':
            case '-':
                temp = Event.PlusOrMinus;
                break;
            case '6':
            case '7':
            case '8':
            case '9':
                temp = Event.Digit;
                break;
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
                temp = Event.UppercaseLetter;
                break;
            default:
                temp = Event.ANY;
        }
        return temp;
    }

    @Override
    public void handleEvent(Event event) {
        int length = transitions.size();
        for (int i = 0; i < length; i++) {
            Transition transition = transitions.get(i);
            if ((state == transition.startState) && (event == transition.trigger)) {
                state = transition.endState;
                return;
            }
        }
    }

    private void buildTransitionTable() {
        transitions.add(new Transition(State.Initial, State.Q1, Event.PlusOrMinus));
        transitions.add(new Transition(State.Initial, State.Error, Event.Digit));
        transitions.add(new Transition(State.Initial, State.Error, Event.UppercaseLetter));
        transitions.add(new Transition(State.Initial, State.Error, Event.EOS));
        transitions.add(new Transition(State.Initial, State.Error, Event.ANY));

        transitions.add(new Transition(State.Q1, State.Error, Event.PlusOrMinus));
        transitions.add(new Transition(State.Q1, State.Q2, Event.Digit));
        transitions.add(new Transition(State.Q1, State.Error, Event.UppercaseLetter));
        transitions.add(new Transition(State.Q1, State.Error, Event.EOS));
        transitions.add(new Transition(State.Q1, State.Error, Event.ANY));

        transitions.add(new Transition(State.Q2, State.Error, Event.PlusOrMinus));
        transitions.add(new Transition(State.Q2, State.Q2, Event.Digit));
        transitions.add(new Transition(State.Q2, State.Q3, Event.UppercaseLetter));
        transitions.add(new Transition(State.Q2, State.Error, Event.EOS));
        transitions.add(new Transition(State.Q2, State.Error, Event.ANY));

        transitions.add(new Transition(State.Q3, State.Error, Event.PlusOrMinus));
        transitions.add(new Transition(State.Q3, State.Error, Event.Digit));
        transitions.add(new Transition(State.Q3, State.Error, Event.UppercaseLetter));
        transitions.add(new Transition(State.Q3, State.Q3, Event.PlusOrMinus));
        transitions.add(new Transition(State.Q3, State.Success, Event.EOS));
        transitions.add(new Transition(State.Q3, State.Error, Event.ANY));
    }
}