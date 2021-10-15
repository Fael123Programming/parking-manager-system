package br.com.rafael.classes.read_input_data;

import java.util.Scanner;

public class Input { //Singleton design pattern.
    private static Scanner input;

    private Input(){}

    public static synchronized Scanner getInstance(){
        if (Input.input == null) input = new Scanner(System.in);
        return input;
    }
}
