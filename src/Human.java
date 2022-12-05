class Human extends Player {

    public Human(Reversi game) {
        super(game);
    }

    public void makeMove() {
        game.setTurnAndColor();
        game.printer.printWhoIsTurn(game.isHuman);
        game.findChoices();
        game.playTurn();
    }
}