import java.util.List;

class Printer {
    private static int plateSize;

    public Printer(int plateSize) {
        Printer.plateSize = plateSize;
    }

    public void printInitMsg() {
        System.out.println();
        System.out.println("******** Reversi ********");
        System.out.println("-----Игра началась!-----");
    }

    public void printStartMsg() {
        System.out.println("-----Начните игру-----");
        System.out.println();
    }

    public void printFinishMsg(int currentCount, int countOfBlackStone, int countOfWhiteStone) {
        System.out.println("----------Конец игры!!!----------");
        System.out.println("Текущее количество : " + currentCount);
        System.out.println("Черный камень : " + countOfBlackStone);
        System.out.println("Белый камень : " + countOfWhiteStone);
        System.out.println("Уин : " + (countOfBlackStone > countOfWhiteStone ? "player" : "Противник"));
    }


    public void printGameStatus(Pos[][] plate) {
        System.out.println();
        StringBuffer gameDisplayer = new StringBuffer();
        for (int i = 0; i < plateSize; i++) {
            gameDisplayer.append("　　　　");
            for (int j = 0; j < plateSize; j++) {
                if (plate[i][j].getColor().equals("black")) {
                    gameDisplayer.append("●  ");
                } else if (plate[i][j].getColor().equals("white")) {
                    gameDisplayer.append("○  ");
                } else if (plate[i][j].getChoosable() == true) {
                    gameDisplayer.append("¤  ");
                } else {
                    gameDisplayer.append("□  ");
                }
            }
            gameDisplayer.append("\n");
        }
        System.out.println(gameDisplayer.toString());
    }

    public void printWhoIsTurn(Boolean myTurn) {
        if (myTurn) {
            System.out.println("---------Ваш ход---------");
            System.out.printf("Выбираемые координаты :  ");

        } else {
            System.out.println("---------Исходное состояние---------");
        }
    }

    public void printChoosablePoints(List<Pos> choices) {
        for (int i = 0, size = choices.size(); i < size; i++) {
            System.out.printf("[" + choices.get(i).getX() + "," + choices.get(i).getY() + "] ");
        }
    }

    public void printInputErrorMsg() {
        System.out.println();
        System.out.println("------Ошибка ввода!!!------");
        System.out.println("Пожалуйста, точно введите координаты из списка выбора, пр. \"3,1\"");
        System.out.println();
    }
}