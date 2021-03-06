import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static ArrayList<String> todoList = new ArrayList<>();
    static Command command;
    static String foundCommand;
    static String inText;
    public static void main(String[] args) {
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Введите одну из команд (HELP для справки)");
            inText = sc.nextLine().trim();
            Pattern pattern = Pattern.compile("^(ADD\\s\\d*)?(EDIT\\s\\d+)?(DELETE\\s\\d+)?(LIST)?(HELP)?(EXIT)?");
            Matcher matcher = pattern.matcher(inText);
            while (matcher.find()) {
                foundCommand = inText.substring(matcher.start(), matcher.end()).trim();
            }

            checkCommand();
            switch (getCommand()) {
                case EXIT:
                    System.out.println("\tПрограма завершена!");
                    check = false;
                    break;
                case LIST:
                    getList();
                    break;
                case HELP:
                    System.out.println(getHelpList());
                    break;
                case ADD:
                    String[] strAdd = inText.split("\\s", 2);
                    todoList.add(strAdd[1]);
                    break;
                case ADD_NUM:
                    String[] sAddNum = inText.split("\\s+", 3);
                    if (Integer.parseInt(sAddNum[1]) <= todoList.size() && sAddNum.length == 3) {
                        todoList.add(Integer.parseInt(sAddNum[1]), sAddNum[2]);
                    } else if (sAddNum.length == 3) {
                        todoList.add(sAddNum[2]);
                    } else System.out.println("\tВы не ввели задачу!");
                    break;
                case DELETE:
                    foundCommand = foundCommand.replaceAll("[^\\d+]", "");
                    if (Integer.parseInt(foundCommand) < todoList.size()) {
                        todoList.remove(Integer.parseInt(foundCommand));
                    }
                    break;
                case EDIT:
                    String[] sEdit = inText.split("\\s+", 3);
                    if (Integer.parseInt(sEdit[1]) < todoList.size() && sEdit.length == 3) {
                        todoList.set(Integer.parseInt(sEdit[1]), sEdit[2]);
                        break;
                    }
                default:
                    System.out.println("\tКоманда не распознана!");
                    break;
            }
        } while (check);

    }

    static void checkCommand() {
        if (inText.matches("^EXIT")) {
            command = Command.EXIT;
        } else if (inText.matches("^HELP")) {
            command = Command.HELP;
        } else if (inText.matches("^LIST")) {
            command = Command.LIST;
        } else if (foundCommand.matches("^ADD")) {
            command = Command.ADD;
        } else if (foundCommand.matches("^ADD\\s\\d+")) {
            command = Command.ADD_NUM;
        } else if (foundCommand.matches("^DELETE\\s\\d+")) {
            command = Command.DELETE;
        } else if (foundCommand.matches("^EDIT\\s\\d+")) {
            command = Command.EDIT;
        } else command = Command.NOT_RECOGNIZED;
    }

    static Command getCommand() {
        return command;
    }

    static void getList() {
        if (!todoList.isEmpty()) {
            for (int i = 0; i < todoList.size(); i++) {
                System.out.println(i + " - " + todoList.get(i));
            }
        } else System.out.println("\tСписок задач пуст");
    }

    static String getHelpList() {
        return ("" +
                "\t- Введите <<LIST>> чтобы получить список всех задач" +
                "\n\t- Введите <<ADD>> \"новая_задача\" чтобы добавить новую задачу или <<ADD>> \"порядковый_номер_задачи(от 0)\" \"текст_новой_задачи\"" +
                "\n\t- Введите <<EDIT>> \"порядковый_номер_задачи(от 0)\" \"новый_тест_задачи\" чтобы изменить текст задачи" +
                "\n\t- Введите <<DELETE>> \"порядковый_номер_задачи(от 0)\" чтобы удалить эту задачу" +
                "\n\t- Введите <<EXIT>> чтобы завершить программу");
    }
}
