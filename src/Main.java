import lombok.Getter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Main {
    static ArrayList<String> todoList = new ArrayList<>();
    public static void main(String[] args) {
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Введите одну из команд:");
            String inText = sc.nextLine().trim();
            String findCommand = null;
            Pattern pattern = Pattern.compile("^(ADD\\s\\d*)?(EDIT\\s\\d+)?(DELETE\\s\\d+)?");
            Matcher matcher = pattern.matcher(inText);
            while (matcher.find()) {
                findCommand = inText.substring(matcher.start(), matcher.end()).trim();
            }
            if (inText.matches("EXIT")) {
                System.out.println("\tПрограма завершена!");
                check = false;
                continue;
            }

            if (inText.matches("LIST")) {
                getList();
                continue;
            }

            if (inText.matches("HELP")) {
                System.out.println(getHelpList());
                continue;
            }

            if (findCommand.matches("^ADD")) {
                String[] s = inText.split("\\s", 2);
                todoList.add(s[1]);
                continue;
            }

            if (findCommand.matches("^ADD\\s\\d+")) {
                String[] s = inText.split("\\s+", 3);
                if (Integer.parseInt(s[1]) <= todoList.size() && s.length == 3) {
                    todoList.add(Integer.parseInt(s[1]), s[2]);
                } else if (s.length == 3){
                    todoList.add(s[2]);
                }
                continue;
            }

            if (findCommand.matches("^DELETE\\s\\d+")) {
                findCommand = findCommand.replaceAll("[^\\d+]", "");
                if (Integer.parseInt(findCommand) < todoList.size()) {
                    todoList.remove(Integer.parseInt(findCommand));
                }
                continue;
            }

            if (findCommand.matches("^EDIT\\s\\d+")) {
                String[] s = inText.split("\\s+", 3);
                if (Integer.parseInt(s[1]) < todoList.size() && s.length == 3) {
                    todoList.set(Integer.parseInt(s[1]), s[2]);
                }
            }
        } while (check);

    }

    static void getList() {
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(i + " - " + todoList.get(i));
        }
    }

    static String getHelpList() {
        return ("\t- Введите <<LIST>> чтобы получить список всех задач" +
                "\n\t- Введите <<ADD \"новая_задача\">> чтобы добавить новую задачу " +
                "или <<ADD \"порядковый_номер_задачи(от 0)\" \"текст_новой_задачи\">>" +
                "\n\t- Введите <<EDIT \"порядковый_номер_задачи(от 0)\" \"новый_тест_задачи\">> чтобы изменить текст задачи" +
                "\n\t- Введите <<DELETE \"порядковый_номер_задачи(от 0)\">> чтобы удалить эту задачу" +
                "\n\t- Введите <<EXIT>> чтобы завершить программу");
    }
}
