import java.io.IOException;
import java.util.Scanner;
public class Main {
    static int z = 0;   //Переменная, созданная для проверки римских и арабских цифр
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   //Сканер для приема введенного текста
        System.out.println("Введите пример, который нужно решить в формате a x b - где a - число 1, x - " +
                "знак операции, b - число 2; " + "a и b не могу быть больше 10, a и b могут принимать римские числа, " +
                "но не могут считать одновременно с арабскими, римские числа должны быть написаны заглавными буквами"); //Вывод условий для пользователя

        String text = scanner.nextLine();   //Считывание введенного текста

        if (text.matches("[I,X,V]*+\\s[+,[-],*,/]\\s+[I,X,V]*"))    //Проверка введенного текста на наличие римских цифр
        {
            z = 1;
            System.out.println(calc(text));     //Выполнение калькулятора
        }
        else if(text.matches("\\d*+\\s[+,[-],*,/]\\s+\\d*")){   //Проверка введенного текста на наличие арабских цифр
            z = 2;
            System.out.println(calc(text));     //Выполнение калькулятора
        }
        else {  //Ошибка если пример введен неверно
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Введите пример корректно");
            }
        }

    }

    public static String calc(String input) {   //Калькулятор

        int p = 0;  //Переменная помогающая подставить нужное римское число
        int n1 = 0; //Переменная, которая будет содержать в себе число 1
        int n2 = 0; //Переменная, которая будет содержать в себе число 2
        int result = 0; //Переменная, которая будет содержать в себе ответ примеров
        String result2 = null; //Возвращаемая переменная, содержащая конечный ответ

        String[] numbers = input.split(" ");    //Выделение чисел и знака операции в массив numbers

        if(z == 1){ //Проверка на то, какие числа в примере были введены, в данном случае - римские
            for (int i = 0; i <numbers.length; i++) {   //Цикл, для подстановки в n1 и n2 нужных арабских чисел
                RimNumbers o1 = RimNumbers.valueOf(numbers[0]); //Присвоение первого римского числа
                RimNumbers o2 = RimNumbers.valueOf(numbers[2]); //Присвоение второго римского числа
                for (RimNumbers UUU : RimNumbers.values()) {    //Цикл для вывода всех данных enum и поочередное присвоение каждого числительно в переменную UUU
                    if (o1 == UUU && p == 0) { //Условие для проверки правильности введенного римского числа 1
                        n1 = Integer.parseInt(UUU.getArabic()); //Перевод римского числа 1 в арабское
                        p = 1;  //Переменная для работы дальнейшего кода и помощь в присвоении n2 римского числа 2
                    }
                    if (o2 == UUU && p == 1) {  //Условие для проверки правильности введенного римского числа 2
                        n2 = Integer.parseInt(UUU.getArabic()); //Перевод римского числа 2 в арабское
                        p = 2;  //Переменная для завершения проверки правильности римских чисел
                        if (n1 >= 11 || n2 >= 11) { //Условие для проверки размера числа, чтобы число не было больше 10
                            try {
                                throw new IOException();    //Выводит ошибку, если число больше 10
                            } catch (IOException e) {
                                result2 = "Введите число равное либо меньшее 10";   //Объясняет ошибку
                            }
                        }
                        else {  //Если числа не больше 10, то выполняется следующий код
                            switch (numbers[1]) {   //Проверка знака в примере
                                case "+":
                                    result = n1 + n2; //Высчитывается пример
                                    result2 = "Ваш ответ - " + RimNumbers.values()[result - 1]; //Переводится в String ответ, который находит нужную римскую цифру по индексу на одну позицию меньше
                                    break;
                                case "-":
                                    result = n1 - n2;
                                    try {
                                        result2 = "Ваш ответ - " + RimNumbers.values()[result - 1];
                                    }
                                    catch (ArrayIndexOutOfBoundsException e) { //Ошибка, которая выходит, если индекс римского числа становится меньше 0
                                        result2 = "Римские числа не могу быть отрицательными"; //Объяснение ошибки
                                    }
                                    break;
                                case "*":
                                    result = n1 * n2;
                                    result2 = "Ваш ответ - " + RimNumbers.values()[result - 1];
                                    break;
                                case "/":
                                    result = n1 / n2;
                                    result2 = "Ваш ответ - " + RimNumbers.values()[result - 1];
                                    break;
                            }
                        }
                    }
                }
            }
        }

        else  if (z == 2){  //Проверка на то, какие числа в примере были введены, в данном случае - арабские
            n1 = Integer.parseInt(numbers[0]); //Присвоение числа 1, через массив, который был создан раньше
            n2 = Integer.parseInt(numbers[2]);  //Присвоение числа 2, через массив, который был создан раньше
            if (n1 >= 11 || n2 >= 11) { //Условие для проверки размера числа, чтобы число не было больше 10
                try {
                    throw new IOException();    //Выводит ошибку, если число больше 10
                } catch (IOException e) {
                    result2 = "Введите число равное либо меньшее 10";   //Объясняет ошибку
                }
            }
            else {  //Если ошибки не было, проверяет знак в примере
                switch (numbers[1]) {   //Цикл для проверки знака в примере
                    case "+":
                        result = n1 + n2;
                        result2 = "Ваш ответ - " + result;
                        break;
                    case "-":
                        result = n1 - n2;
                        result2 = "Ваш ответ - " + result;
                        break;
                    case "*":
                        result = n1 * n2;
                        result2 = "Ваш ответ - " + result;
                        break;
                    case "/":
                        try {
                            result = n1 / n2;
                            result2 = "Ваш ответ - " + result;
                        }
                        catch (ArithmeticException e) { //Выдает ошибку при делении на 0
                            result2 = "На ноль делить нельзя";  //Объяснение ошибки
                        }
                        break;
                }
            }
        }
        return result2;

    }
}
enum RimNumbers { //Enum созданный для перечисления всех римских чисел
    I("1"), II("2"), III("3"), IV("4"), V("5"), VI("6"), VII("7"),
    VIII("8"), IX("9"), X("10"), XI("11"), XII("12"), XIII("13"), XIV("14"),
    XV("15"), XVI("16"), XVII("17"), XVIII("18"), XIX("19"), XX("20"),
    XXI("21"), XXII("22"), XXIII("23"), XXIV("24"), XXV("25"), XXVI("26"),
    XXVII("27"), XXVIII("28"), XXIX("29"), XXX("30"), XXXI("31"), XXXII("32"),
    XXXIII("33"), XXXIV("34"), XXXV("35"), XXXVI("36"), XXXVII("37"), XXXVIII("38"),
    XXXIX("39"), XL("40"), XLI("41"), XLII("42"), XLIII("43"), XLIV("44"),
    XLV("45"), XLVI("46"), XLVII("47"), XLVIII("48"), XLIX("49"), L("50"),
    LI("51"), LII("52"), LIII("53"), LIV("54"), LV("55"), LVI("56"), LVII("57"),
    LVIII("58"), LIX("59"), LX("60"), LXI("61"), LXII("62"), LXIII("63"),
    LXIV("64"), LXV("65"), LXVI("66"), LXVII("67"), LXVIII("68"), LXIX("69"),
    LXX("70"), LXXI("71"), LXXII("72"), LXXIII("73"), LXXIV("74"), LXXV("75"),
    LXXVI("76"), LXXVII("77"), LXXVIII("78"), LXXIX("79"), LXXX("80"), LXXXI("81"),
    LXXXII("82"), LXXXIII("83"), LXXXIV("84"), LXXXV("85"), LXXXVI("86"), LXXXVII("87"),
    LXXXVIII("88"), LXXXIX("89"), XC("90"), XCI("91"), XCII("92"), XCIII("93"),
    XCIV("94"), XCV("95"), XCVI("96"), XCVII("97"), XCVIII("98"), XCIX("99"),
    C("100");
    private String arabic;
    RimNumbers(String arabic) {
        this.arabic = arabic;
    }
    String getArabic() {    //Переводит римское число в арабское
        return arabic;
    }
}
