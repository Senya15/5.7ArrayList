import lombok.Getter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

@Getter
public class Main {
    static ArrayList<String> todoList = new ArrayList<>();
    public static void main(String[] args) {
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Введите одну из команд:");
            Pattern pattern = Pattern.compile("\\s+");
            String[] inText = pattern.split(sc.nextLine().trim(), 3);
            for (String s :
                    inText) {
                System.out.println(s);
            }

            if (inText[0].equals("LIST")) {
                getList();
            }

            if (inText[0].equals("ADD")) {
                if (inText.length == 3) {
                    if (inText[1].matches("[+]?\\d+") && (Integer.parseInt(inText[1]) <= inText.length)) {
                        todoList.add(Integer.parseInt(inText[1]), inText[2]);
                    }
                } else todoList.add(inText[1]);
            }
        } while (check);


    }

    static void getList() {
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(i + " - " + todoList.get(i));
        }

    }
}
