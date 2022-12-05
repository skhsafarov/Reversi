public class Main {
    public static void main(String[] args) {
        Reversi game = new Reversi();
        Human human = new Human(game);
        ArtificialIntellect ai = new ArtificialIntellect(game);
        Player curentGamer = human;
        while (!game.isFinish()) {
            curentGamer.makeMove();
            curentGamer = game.isHuman ? human : ai;
        }
        game.finish();
    }
}











