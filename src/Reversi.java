import java.util.*;

class Reversi {
    private Scanner scanner;
    public Printer printer;
    private int
            plateSize,
            totalCount,
            currentCount,
            countOfWhiteStone,
            countOfBlackStone,
            blackOutCount,
            whiteOutCount;
    private Pos[][] plate;
    public boolean isHuman;
    private String myColor;
    private String enemyColor;
    private List<Pos> choices;
    private List<String> choicesList;
    private Stack<Pos> stack;

    public Reversi() {
        plateSize = 8;
        scanner = new Scanner(System.in);
        printer = new Printer(plateSize);
        plate = new Pos[plateSize][plateSize];
        totalCount = plateSize * plateSize;
        currentCount = 0;
        countOfWhiteStone = 0;
        countOfBlackStone = 0;
        blackOutCount = 0;
        whiteOutCount = 0;
        isHuman = false;
        myColor = "black";
        enemyColor = "white";

        initStone(plateSize);
        printer.printInitMsg();
        printer.printGameStatus(plate);
        printer.printStartMsg();
    }

    private void initStone(int plateSize) {
        for (int i = 0; i < plateSize; i++) {
            for (int j = 0; j < plateSize; j++) {
                if (isBlackStartingPlace(plateSize, i, j)) {
                    plate[i][j] = new Pos("black", j, i, plateSize);
                    countOfBlackStone++;
                    currentCount++;
                } else if (isWhiteStartingPlace(plateSize, i, j)) {
                    plate[i][j] = new Pos("white", j, i, plateSize);
                    countOfWhiteStone++;
                    currentCount++;
                } else {
                    plate[i][j] = new Pos("none", j, i, plateSize);
                }
            }
        }
    }

    private boolean isBlackStartingPlace(int plateSize, int i, int j) {
        return (i == (plateSize / 2) - 1 && j == (plateSize / 2) - 1) || (i == plateSize / 2 && j == plateSize / 2);
    }

    private boolean isWhiteStartingPlace(int plateSize, int i, int j) {
        return (i == (plateSize / 2) - 1 && j == plateSize / 2) || (i == plateSize / 2 && j == (plateSize / 2) - 1);
    }

    public boolean isFinish() {
        if (totalCount - currentCount == 0 || countOfWhiteStone == 0 || countOfBlackStone == 0 ||
                whiteOutCount == 7 || blackOutCount == 7) {
            System.out.println(totalCount);
            System.out.println(currentCount);
            System.out.println(countOfWhiteStone);
            System.out.println(countOfBlackStone);
            System.out.println(whiteOutCount);
            System.out.println(blackOutCount);
            return true;
        }
        return false;
    }

    public void setTurnAndColor() {
        isHuman = !isHuman;
        myColor = (isHuman ? "black" : "white");
        enemyColor = (isHuman ? "white" : "black");
    }

    public void findChoices() {
        validate();
        choices = new ArrayList<>();
        for (int i = 0; i < plateSize; i++) {
            for (int j = 0; j < plateSize; j++) {
                if (plate[i][j].getChoosable()) {
                    choices.add(plate[i][j]);
                }
            }
        }
    }

    private void validate() {
        clearPlate();
        searchDirectionForChoices();
    }

    private void clearPlate() {
        for (int i = 0; i < plateSize; i++) {
            for (int j = 0; j < plateSize; j++) {
                plate[i][j].setChoosable(false);
            }
        }
    }

    private void searchDirectionForChoices() {
        for (int i = 0; i < plateSize; i++) {
            for (int j = 0; j < plateSize; j++) {
                if (plate[i][j].getColor() == enemyColor) {
                    if (isEmptyNorthWest(i, j)) {
                        for (int q = i + 1, r = j + 1; q < plateSize && r < plateSize; q++, r++) {
                            if (plate[q][r].getColor() == myColor) {
                                plate[i - 1][j - 1].setChoosable(true);
                                break;
                            } else if (plate[q][r].getColor() == "none") {
                                break;
                            }
                        }
                    }
                    if (isEmptyNorth(i, j)) {
                        for (int q = i + 1; q < plateSize; q++) {
                            if (plate[q][j].getColor() == myColor) {
                                plate[i - 1][j].setChoosable(true);
                                break;
                            } else if (plate[q][j].getColor() == "none") {
                                break;
                            }
                        }
                    }
                    if (isEmptyNorthEast(i, j)) {
                        for (int q = i + 1, r = j - 1; q < plateSize && r >= 0; q++, r--) {
                            if (plate[q][r].getColor() == myColor) {
                                plate[i - 1][j + 1].setChoosable(true);
                                break;
                            } else if (plate[q][r].getColor() == "none") {
                                break;
                            }
                        }
                    }
                    if (isEmptyWest(i, j)) {//Слева
                        for (int r = j + 1; r < plateSize; r++) {
                            if (plate[i][r].getColor() == myColor) {
                                plate[i][j - 1].setChoosable(true);
                                break;
                            } else if (plate[i][r].getColor() == "none") {
                                break;
                            }
                        }
                    }
                    if (isEmptyEast(i, j)) {//Правильно
                        for (int r = j - 1; r >= 0; r--) {
                            if (plate[i][r].getColor() == myColor) {
                                plate[i][j + 1].setChoosable(true);
                                break;
                            } else if (plate[i][r].getColor() == "none") {
                                break;
                            }
                        }
                    }
                    if (isEmptySouthWest(i, j)) {//Ниже левой диагонали
                        for (int q = i - 1, r = j + 1; q >= 0 && r < plateSize; q--, r++) {
                            if (plate[q][r].getColor() == myColor) {
                                plate[i + 1][j - 1].setChoosable(true);
                                break;
                            } else if (plate[q][r].getColor() == "none") {
                                break;
                            }
                        }
                    }
                    if (isEmptySouth(i, j)) {//Ниже
                        for (int q = i - 1; q >= 0; q--) {
                            if (plate[q][j].getColor() == myColor) {
                                plate[i + 1][j].setChoosable(true);
                                break;
                            } else if (plate[q][j].getColor() == "none") {
                                break;
                            }
                        }
                    }
                    if (isEmptySouthEast(i, j)) {//Под правой диагональю
                        for (int q = i - 1, r = j - 1; q >= 0 && r >= 0; q--, r--) {
                            if (plate[q][r].getColor() == myColor) {
                                plate[i + 1][j + 1].setChoosable(true);
                                break;
                            } else if (plate[q][r].getColor() == "none") {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void addChoosablePoints() {
        choicesList = new ArrayList<>();
        for (int i = 0, size = choices.size(); i < size; i++) {
            choicesList.add(choices.get(i).getX() + "," + choices.get(i).getY());
        }
    }


    private boolean hasChoices() {
        return choices.size() > 0;
    }

    private boolean enemyExistsInSouthEast(int x, int y) {
        return y < plateSize - 2 && x < plateSize - 2 && plate[y + 1][x + 1].getColor() == enemyColor;
    }

    private boolean enemyExistsInSouth(int x, int y) {
        return y < plateSize - 2 && plate[y + 1][x].getColor() == enemyColor;
    }

    private boolean enemyExistsInSouthWest(int x, int y) {
        return y < plateSize - 2 && x > 2 && plate[y + 1][x - 1].getColor() == enemyColor;
    }

    private boolean enemyExistsInEast(int x, int y) {
        return x < plateSize - 2 && plate[y][x + 1].getColor() == enemyColor;
    }

    private boolean enemyExistsInWest(int x, int y) {
        return x > 2 && plate[y][x - 1].getColor() == enemyColor;
    }

    private boolean enemyExistsInNorthEast(int x, int y) {
        return y > 2 && x < plateSize - 2 && plate[y - 1][x + 1].getColor() == enemyColor;
    }

    private boolean enemyExistsInNorth(int x, int y) {
        return y > 2 && plate[y - 1][x].getColor() == enemyColor;
    }

    private boolean enemyExistsInNorthWest(int x, int y) {
        return y > 2 && x > 2 && plate[y - 1][x - 1].getColor() == enemyColor;
    }

    private boolean isEmptySouthEast(int i, int j) {
        return i < plateSize - 1 && j < plateSize - 1 && plate[i + 1][j + 1].getColor() == "none";
    }

    private boolean isEmptySouth(int i, int j) {
        return i < plateSize - 1 && plate[i + 1][j].getColor() == "none";
    }

    private boolean isEmptySouthWest(int i, int j) {
        return i < plateSize - 1 && j > 0 && plate[i + 1][j - 1].getColor() == "none";
    }

    private boolean isEmptyEast(int i, int j) {
        return j < plateSize - 1 && plate[i][j + 1].getColor().equals("none");
    }

    private boolean isEmptyWest(int i, int j) {
        return j > 0 && plate[i][j - 1].getColor() == "none";
    }

    private boolean isEmptyNorthEast(int i, int j) {
        return i > 0 && j < plateSize - 1 && plate[i - 1][j + 1].getColor() == "none";
    }

    private boolean isEmptyNorth(int i, int j) {
        return i > 0 && plate[i - 1][j].getColor() == "none";
    }

    private boolean isEmptyNorthWest(int i, int j) {
        return i > 0 && j > 0 && plate[i - 1][j - 1].getColor() == "none";
    }

    public void playTurn() {
        if (hasChoices()) {
            printer.printChoosablePoints(choices);
            addChoosablePoints();
            printer.printGameStatus(plate);

            choosePoint();

            printer.printGameStatus(plate);
        } else {
            System.out.println("Ходов не осталось, ваш ход пропускается.");

            if (isHuman) blackOutCount++;
            else whiteOutCount++;
        }
    }

    private void choosePoint() {
        int SelectedY, SelectedX;
        if (isHuman) {
            System.out.printf("Выберите координаты, формат ввода \"x,y\": ");
            String[] userSelectedXandY = scanner.nextLine().split(",");
            if (isUserSelectedUnCorrectly(userSelectedXandY)) {//Когда я снова выбрал то же самое место validation Дополнительные требуемые
                printer.printInputErrorMsg();
                isHuman = !isHuman;//Чтобы вернуться в Натун.
                return;
            }
            SelectedY = Integer.parseInt(userSelectedXandY[1]);
            SelectedX = Integer.parseInt(userSelectedXandY[0]);
            plate[SelectedY][SelectedX].setColor(myColor);
            countOfBlackStone = countOfBlackStone + 1;
            System.out.println("Вы выбрали: " + SelectedX + "," + SelectedY);
            modifyStatus(SelectedX, SelectedY);
            blackOutCount = 0;
        } else {
            Random ran = new Random();
            int randomInt = ran.nextInt(choices.size());
            SelectedY = choices.get(randomInt).getY();
            SelectedX = choices.get(randomInt).getX();
            plate[SelectedY][SelectedX].setColor(myColor);
            countOfWhiteStone = countOfWhiteStone + 1;
            System.out.println("Противник выбрал: " + SelectedX + "," + SelectedY);
            modifyStatus(SelectedX, SelectedY);
            whiteOutCount = 0;
        }
        currentCount++;
    }

    private void modifyStatus(int x, int y) {

        stack = new Stack<>();

        if (enemyExistsInNorthWest(x, y)) {
            for (int q = y - 1, r = x - 1; q >= 0 && r >= 0; q--, r--) {
                checkAndFlip(plate[q][r]);
            }
            stack.clear();
        }
        if (enemyExistsInNorth(x, y)) {
            for (int q = y - 1; q >= 0; q--) {
                checkAndFlip(plate[q][x]);
            }
            stack.clear();
        }
        if (enemyExistsInNorthEast(x, y)) {
            for (int q = y - 1, r = x + 1; q >= 0 && r < plateSize; q--, r++) {
                checkAndFlip(plate[q][r]);
            }
            stack.clear();
        }
        if (enemyExistsInWest(x, y)) {
            for (int r = x - 1; r >= 0; r--) {
                checkAndFlip(plate[y][r]);
            }
            stack.clear();
        }
        if (enemyExistsInEast(x, y)) {
            for (int r = x + 1; r < plateSize; r++) {
                checkAndFlip(plate[y][r]);
            }
            stack.clear();
        }
        if (enemyExistsInSouthWest(x, y)) {
            for (int q = y + 1, r = x - 1; q < plateSize && r >= 0; q++, r--) {
                checkAndFlip(plate[q][r]);
            }
            stack.clear();
        }
        if (enemyExistsInSouth(x, y)) {
            for (int q = y + 1; q < plateSize; q++) {
                checkAndFlip(plate[q][x]);
            }
            stack.clear();
        }
        if (enemyExistsInSouthEast(x, y)) {
            for (int q = y + 1, r = x + 1; q < plateSize && r < plateSize; q++, r++) {
                checkAndFlip(plate[q][r]);
            }
            stack.clear();
        }
    }

    private void checkAndFlip(Pos discoveredStone) {
        if (enemyColor.equalsIgnoreCase(discoveredStone.getColor())) {
            stack.push(discoveredStone);
        } else if (myColor.equalsIgnoreCase(discoveredStone.getColor())) {
            flipStone();
            return;
        } else if ("none".equalsIgnoreCase(discoveredStone.getColor())) {
            return;
        }
    }

    private void flipStone() {
        while (!stack.isEmpty()) {
            stack.pop().setColor(myColor);
            if (myColor == "black") {
                countOfBlackStone = countOfBlackStone + 1;
                countOfWhiteStone = countOfWhiteStone - 1;
            } else {
                countOfWhiteStone = countOfWhiteStone + 1;
                countOfBlackStone = countOfBlackStone - 1;
            }
        }
    }

    private boolean isUserSelectedUnCorrectly(String[] userSelectedXandY) {
        boolean isValid = false;
        for (String choice : choicesList) {
            if (userSelectedXandY.length > 1 && choice.equals(userSelectedXandY[0] + "," + userSelectedXandY[1])) {
                isValid = true;
                break;
            }
        }

        return (userSelectedXandY.length != 2) || !Character.isDigit(userSelectedXandY[0].charAt(0))
                || !Character.isDigit(userSelectedXandY[1].charAt(0)) || Integer.parseInt(userSelectedXandY[0]) > 7
                || Integer.parseInt(userSelectedXandY[0]) < 0 || Integer.parseInt(userSelectedXandY[1]) > 7
                || Integer.parseInt(userSelectedXandY[1]) < 0 || !isValid;
    }

    public void finish() {
        printer.printFinishMsg(currentCount, countOfBlackStone, countOfWhiteStone);
    }
}