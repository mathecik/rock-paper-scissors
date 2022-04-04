package types;

public enum Choice {

    ROCK {
        @Override
        public boolean beats(Choice ch) {
            return ch.equals(Choice.SCISSORS);
        }
    },
    PAPER {
        @Override
        public boolean beats(Choice ch) {
            return ch.equals(Choice.ROCK);
        }
    },
    SCISSORS {
        @Override
        public boolean beats(Choice ch) {
            return ch.equals(Choice.PAPER);
        }
    };

    public abstract boolean beats(Choice ch);

    public static Choice chooseRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
