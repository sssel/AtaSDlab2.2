public class Automat extends FSM {
    private Event event;

    public Automat(String text) {
        super(text);
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean scanner() {
        int count = 0;
        do {
            if (count < this.text.length())
                event = recognizeEvent(text.charAt(count));
            else
                event = Event.EOS;
            handleEvent(event);
            count++;
        }
        while (state != State.Success && state != State.Error);
        if (state == State.Success) {
            return true;
        } else
            return false;
    }

    @Override
    public Event recognizeEvent(char ch) {
        Event temp;
        switch (ch) {
            case '+':
            case '-':
                temp = Event.PlusOrMinus;
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
        switch (state) {
            case Initial:
                switch (event) {
                    case PlusOrMinus:
                        state = State.Q1;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;
            case Q1:
                switch (event) {
                    case PlusOrMinus:
                        state = State.Q2;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;
            case Q2:
                switch (event) {
                    case Digit:
                        state = State.Q3;
                        break;
                    case UppercaseLetter:
                        state = State.Q3;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;
            case Q3:
                switch (event) {
                    case PlusOrMinus:
                        state = State.Q3;
                        break;
                    case EOS:
                        state = State.Success;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
        }
    }
}