package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.utilities.CLIPrinter;
import java.util.Scanner;

public class NavigatorCliController {

    protected int getMenuChoice(int min, int max){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true){
            CLIPrinter.printMessage("please enter your choice: ");
            choice = input.nextInt();
            if (choice >= min && choice <= max){
                break;
            }
            CLIPrinter.printMessage("invalid option");
        }
        return choice;
    }
}
