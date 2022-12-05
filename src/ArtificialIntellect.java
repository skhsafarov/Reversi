class ArtificialIntellect extends Player {
    public ArtificialIntellect(Reversi game) {

        super(game);
    }

    public void makeMove() {
        game.setTurnAndColor();
        game.printer.printWhoIsTurn(game.isHuman);
        game.findChoices();
        game.playTurn();
    }
}