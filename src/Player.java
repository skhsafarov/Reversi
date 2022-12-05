abstract class Player {
    Reversi game;

    public Player(Reversi game) {
        this.game = game;
    }

    public abstract void makeMove();
}