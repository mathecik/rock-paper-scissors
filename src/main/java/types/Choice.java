package types;

public enum Choice {

    ROCK{
        @Override
        boolean beats(Choice ch) {
            return ch.equals(Choice.SCISSORS);
        }
    },
    PAPER {
        @Override
        boolean beats(Choice ch) {
            return ch.equals(Choice.ROCK);
        }
    },
    SCISSORS {
        @Override
        boolean beats(Choice ch) {
            return ch.equals(Choice.PAPER);
        }
    };

    abstract boolean beats(Choice ch);
}
