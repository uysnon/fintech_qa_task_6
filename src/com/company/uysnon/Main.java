package com.company.uysnon;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static private ArrayList<Printer> printers;
    static private String GAP_VERTICAL = "\n";
    static private final String[] printerOptions = {
            "1. Состояние текущего принтера",
            "2. Создать новый принтер",
            "3. Удалить принтер",
            "4. Положить в текущий принтер бумагу",
            "5. Печать",
            "6. Список всех принтеров",
            "7. Изменить название текущего принтера",
            "8. Изменить вместимость текущего принтера",
            "9. Изменить текущий принтер",
            "10. Выход"
    };
    static private int status;

    public static void main(String[] args) {
        Printer currentPrinter;
        printers = new ArrayList<>();
        printers.add(new Printer());
        currentPrinter = printers.get(0);

        status = -1;
        Scanner scanner = new Scanner (System.in);
        while (true){
            for (int i = 0; i<printerOptions.length; i++){
                System.out.println(printerOptions[i]);
            }
            status = scanner.nextInt();
            if (status == 1){
                printPrinterStatus(currentPrinter);
            }
            if (status == 2){
                printers.add(createNewPrinter(scanner));
            }
            if (status == 3){
                System.out.println("Введите номер принтера, который следует удалить");
                printAllPrinters();
                int num = scanner.nextInt();
                if (printers.get(num-1) == currentPrinter){
                    System.out.println("Извини, нельзя удалить текущий принтер, чтобы удалить выбранный треубемый принтер, снимите с него статус \"текущий\" ");
                    continue;
                }
                printers.remove(num-1);
            }
            if (status == 4) {
                putLists(currentPrinter,scanner);
            }
            if (status == 5){
                printLists(currentPrinter, scanner);
            }
            if (status == 6){
                printAllPrinters();
            }
            if (status == 7){
                rename(currentPrinter,scanner);
            }
            if (status == 8){
                changeCapacity(currentPrinter,scanner);
            }
            if (status == 9){
                currentPrinter = getNewCurrent(scanner);
            }

            if (status == printerOptions.length) break;
        }

    }

    static private Printer getNewCurrent(Scanner scanner){
        Printer printer;
        System.out.println("Выберете, какой из принтеров будет текущим: ");
        printAllPrinters();
        int num = -1;
        while ((num < 1)||(num > printers.size())){
            num = scanner.nextInt();
        }
        printer = printers.get(num-1);
        System.out.println("Успешно выбран новый принтер");
        printPrinterStatus(printer);
        return printer;
    }

    static private void printAllPrinters(){
        System.out.println("Список устройств: ");
        for (int i = 0; i < printers.size(); i++){
            System.out.println(i+1 + ". Название: " + printers.get(i).getName());
        }
        System.out.println(GAP_VERTICAL);
    }

    private static void putLists(Printer printer, Scanner scanner){
        System.out.println("Сколько листов треубется положить ?");
        System.out.println("Ожидается ввод (число >0)");
        int lists = -1;
        while (lists < 0){
            lists = scanner.nextInt();
        }
        printer.putLists(lists);
        System.out.println(GAP_VERTICAL);
    }

    private static void printLists(Printer printer, Scanner scanner){
        System.out.println("Сколько листов треубется напечатать ?");
        System.out.println("Ожидается ввод (число >0)");
        int lists = -1;
        while (lists < 0){
            lists = scanner.nextInt();
        }
        printer.print(lists);
        System.out.println(GAP_VERTICAL);
    }

    static private void printPrinterStatus(Printer printer){
        System.out.println("Принтер: " + printer.getName());
        System.out.println("Характеристики и свойства: ");
        System.out.println("  Вместимость: "+ printer.getCapacity());
        System.out.println("  Кол-во листов в настоящий момент: " + printer.getPaperLists() );
        System.out.println(GAP_VERTICAL);
    }

    static private void rename(Printer printer, Scanner scanner){
        System.out.println("Текущее имя: "+printer.getName());
        System.out.println("Сменить имя на: ");
        String inputString = scanner.next();
        if (!inputString.equals("")){
            printer.setName(inputString);
            System.out.println("Имя успешно изменено");
            System.out.println("Новое название принтера: " + printer.getName());
        } else {System.out.println("Вы решили не менять название принтера"); return;}
        System.out.println(GAP_VERTICAL);
    }

    static private void changeCapacity(Printer printer, Scanner scanner){
        System.out.println("Текущее вместимость: "+printer.getCapacity());
        System.out.println("Сменить вместимость на: ");
        int capacity = -1;
        while (capacity < 1) {
            capacity = scanner.nextInt();
        }
            printer.setCapacity(capacity);
            System.out.println("Вместимость успешно изменена");
            System.out.println("Новая вместимость принтера: " + printer.getCapacity());
        System.out.println(GAP_VERTICAL);
    }

    static Printer createNewPrinter(Scanner scanner){
        Printer printer = new Printer();
        int status = -1;
        System.out.println(" 1. Создать пустой принтер " +
                "\n 2. Создать именнованный принтер " +
                "\n 3. Создать именнованный принтер с определенной вместимостью"
             );
        while ((status < 1)||(status >3)) {
            status = scanner.nextInt();
        }
        try {
            if (status == 1) {
                printer = new Printer();
            }
            if (status == 2) {
                System.out.println("Ожидается ввод, формат: имя_принета ");
                String name = scanner.next();
                printer = new Printer(name);
            }
            if (status == 3) {
                System.out.println("Ожидается ввод, формат: имя_принета.вместимость_принтера ");
                String[] array = scanner.next().split("[.]");
                String name = array[0];
                System.out.println(array[0]);
                int capacity = Integer.valueOf(array[1]);
                printer = new Printer(name, capacity);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Ошибка ввода, повторите действия" +
                    GAP_VERTICAL);
            printer = createNewPrinter(scanner);
        }
        System.out.println("Созданный принтер:");
        printPrinterStatus(printer);
        System.out.println(GAP_VERTICAL);

        return printer;
    }

}
