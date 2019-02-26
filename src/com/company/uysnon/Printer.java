package com.company.uysnon;

import java.util.concurrent.TimeUnit;

public class Printer {
    private final static int TIME_FOR_ONE_LIST = 1;
    public final static String  BASIC_NAME = "NONAME";
    public final static int BASIC_CAPACITY = -1;
    public final static int BASIC_LISTS = 0;
    private final static String MESSAGE_ERROR_MASK  = "ОШИБКА: ";
    private final static String MESSAGE_SUCCESSFUL_PRINT  = "ПЕЧАТЬ УСПЕШНО ЗАВЕРШЕНА";
    private final static String MESSAGE_SUCCESSFUL_PUT  = "ЛИСТЫ УСПЕШНО ЗАГРУЖЕНЫ";
    private final static String MESSAGE_PAPER_DEFICIT = "Не хватает бумаги для печати, вставь листики" ;
    private final static String MESSAGE_PAPER_WRONG = "Указано неправильное значение, повтори ввод";
    private final static String MESSAGE_ERROR_FULL_PRINTER = "Не хватает места в лотке";
    private int paperLists;
    private boolean isPrinting;
    private String name;
    private int capacity;


    public void putLists(int numLists){
        if (numLists+this.paperLists > capacity){
            System.out.println(MESSAGE_ERROR_MASK + "\n" + MESSAGE_ERROR_FULL_PRINTER);
            return;
        }
        this.paperLists +=  numLists;
        System.out.println(MESSAGE_SUCCESSFUL_PUT+ "\n Текущее кол-во листов в лотке: "+this.paperLists);
    }

    public void print(int numLists){
        int numLists0 = numLists;
        if (isPrinting) {System.out.println(MESSAGE_ERROR_MASK+"\nВ настоящее время уже идет печать");
        return;}
        isPrinting = true;
        if (numLists < 0){
            System.out.println(MESSAGE_ERROR_MASK+"\n"+MESSAGE_PAPER_WRONG);
            return;
        }
        if (numLists > paperLists) {
            System.out.println(MESSAGE_PAPER_DEFICIT + "\nВ принтере в настоящий момент " + this.paperLists + " листов" );
            return;
        }
        System.out.println("Начинаем печать:\n"+"Примерное время печати: "+numLists*TIME_FOR_ONE_LIST +" минут\n"+ "Количество листов в лотке: "+this.paperLists+ "; Осталось напечатать:"+numLists);
        for (int i = 1; i <= numLists0 ; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                numLists--;
                this.paperLists--;
                System.out.println(i+". "+"Количество листов в лотке: "+this.paperLists+ "; Осталось напечатать: "+numLists);
            } catch (InterruptedException e) {
                isPrinting  = false;
                e.printStackTrace();
            }
        }
        isPrinting = false;
        System.out.println(MESSAGE_SUCCESSFUL_PRINT);
    }

    public Printer(int paperLists, String name, int capacity) {
        this.paperLists = paperLists;
        this.name = name;
        this.capacity = capacity;
    }

    public Printer(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Printer(String name) {
        this.name = name;
        this.capacity = BASIC_CAPACITY;
    }

    public Printer() {
        this.name = BASIC_NAME;
        this.capacity = BASIC_CAPACITY;
        this.paperLists = BASIC_LISTS;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPaperLists() {
        return paperLists;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}
