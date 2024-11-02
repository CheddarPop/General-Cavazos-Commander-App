package com.cammander;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class App {
    
    private static final String[] COMMANDS = {
        "Advance", "Retreat", "Hold Position", "Deploy", "Reinforce",
        "Withdraw", "Attack", "Defend", "Scout", "Establish Base",
        "Supply", "Support", "Patrol", "Engage", "Disengage",
        "Ambush", "Secure", "Reposition", "Evacuate", "Recon",
        "Fortify", "Flank", "Breach", "Cover Fire", "Counterattack",
        "Survey"
    };
    
    private List<String> issuedCommands = new ArrayList<>();
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();
    private Random random = new Random();
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            displayMenu();
            String input = scanner.nextLine();
            
            switch (input.toLowerCase()) {
                case "i":
                    issueCommand();
                    break;
                case "l":
                    listCommands();
                    break;
                case "u":
                    undoCommand();
                    break;
                case "r":
                    redoCommand();
                    break;
                case "q":
                    running = false;
                    System.out.println("Quitting the application...");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("------------------------------");
        System.out.println("General Cavazos Commander App");
        System.out.println("------------------------------");
        System.out.println("i    Issue a command");
        System.out.println("l    List all of the commands");
        System.out.println("u    Undo the last command that was issued");
        System.out.println("r    Redo the last command that was issued");
        System.out.println("q    Quit");
        System.out.print("Enter a command: ");
    }
    
    private void issueCommand() {
        String command = COMMANDS[random.nextInt(COMMANDS.length)];
        issuedCommands.add(command);
        undoStack.push(command);
        redoStack.clear();  // Clear redo stack whenever a new command is issued
        System.out.println("Issued command: " + command);
    }
    
    private void listCommands() {
        System.out.println("List of issued commands:");
        for (int i = 0; i < issuedCommands.size(); i++) {
            System.out.println((i + 1) + ". " + issuedCommands.get(i));
        }
    }
    
    private void undoCommand() {
        if (!undoStack.isEmpty()) {
            String lastCommand = undoStack.pop();
            redoStack.push(lastCommand);
            System.out.println("Undid command: " + lastCommand);
        } else {
            System.out.println("No commands to undo.");
        }
    }
    
    private void redoCommand() {
        if (!redoStack.isEmpty()) {
            String lastUndoneCommand = redoStack.pop();
            undoStack.push(lastUndoneCommand);
            System.out.println("Redid command: " + lastUndoneCommand);
        } else {
            System.out.println("No commands to redo.");
        }
    }
    
    public static void main(String[] args) {
        new App().run();
    }
}
