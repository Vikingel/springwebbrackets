package Vikingel.springwebbrackets.services;

import Vikingel.springwebbrackets.entities.Cat;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

@Service    //аннотация, которая говорит Спрингу, что этот класс является "сервисом" - частным случаем "компонента"
            //благодаря этому аннотация @ComponentScan на уровне конфигурации приложения автоматически
            //создает бин, помещает его в контекст и выполняет инициализацию
public class GetCatsService {
    ArrayList<String> brs= new ArrayList<>();


    @PostConstruct  //аннотация, которая говорит Спрингу, что данный метод нужно вызвать для иницализации
    //после создания бина
    public void fill(){
        try {
            brs.add("{][");
            brs.add("{{[]}}");
            brs.add("{][");

        }catch (Exception e){
            System.out.println("что-то не так с созданием продуктов");
        }
    }

    public static boolean areBracketsBalanced(String expr) {
        Deque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);
            if (x == '(' || x == '[' || x == '{') {
                stack.push(x);
                continue;
            }
            if (stack.isEmpty())
                return false;
            char check;
            switch (x) {
                case ')':
                    check = stack.pop();
                    if (check == '{' || check == '[')
                        return false;
                    break;

                case '}':
                    check = stack.pop();
                    if (check == '(' || check == '[')
                        return false;
                    break;

                case ']':
                    check = stack.pop();
                    if (check == '(' || check == '{')
                        return false;
                    break;
            }
        }
        return (stack.isEmpty());
    }
}


    public ArrayList<String> getAllList() {
        return brs;
    }

    public static ArrayList<String> readCats(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        ArrayList<String> brs = new ArrayList<>();
        while (scanner.hasNext()) {
            String str= scanner.next();
            brs.add(str);
        }
        return brs;
    }


    @Override
    public String toString() {
        return "getCatsService{" +
                "str=" + brs +
                '}';
    }
}
