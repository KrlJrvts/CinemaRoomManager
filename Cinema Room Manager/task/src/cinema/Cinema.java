package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();

        // Initialize cinema seating arrangement
        char[][] cinema = new char[numRows][numSeats];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numSeats; j++) {
                cinema[i][j] = 'S';
            }
        }
        boolean status = true;

        while (status) {
            System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    seats(cinema); // show the seats
                    break;
                case 2:
                    tickets(cinema, scanner, numRows, numSeats); // buy ticket
                    break;
                case 3:
                    stats(cinema, numRows, numSeats); // statistics
                    break;
                case 0:
                    status = false; // exit program
                    break;
                default:
                    System.out.println("Invalid selection");
            }
        }
    }

    public static void seats(char[][] cinema) {

        // Print cinema seating arrangement with row and column numbers
        System.out.print("Cinema:\n  ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void tickets(char[][] cinema, Scanner scanner, int numRows, int numSeats) {

        while (true) {
            System.out.println("Enter a row number:");
            int chosenRow = scanner.nextInt() - 1; // Subtract 1 to convert to zero-based indexing
            System.out.println("Enter a seat number in that row:");
            int chosenSeat = scanner.nextInt() - 1; // Subtract 1 to convert to zero-based indexing

            if (chosenRow < 0 || chosenRow >= cinema.length || chosenSeat < 0 || chosenSeat >= cinema[0].length) {
                System.out.println("Wrong input!");
            } else if (cinema[chosenRow][chosenSeat] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                int ticketPrice;
                int totalSeats = numRows * numSeats;
                if (totalSeats <= 60) {
                    ticketPrice = 10;
                } else {
                    int frontHalf = numRows / 2;
                    if (chosenRow < frontHalf) {
                        ticketPrice = 10;
                    } else {
                        ticketPrice = 8;
                    }
                }
                cinema[chosenRow][chosenSeat] = 'B';
                System.out.println("Ticket price: $" + ticketPrice);
                break; // add break statement to exit the loop
            }
        }
    }

    public static void stats(char[][] cinema, int numRows, int numSeats){
        // sold tickets
        int totalSeats = numSeats * numRows;
        int frontHalf = numRows / 2;
        int counterHigherPrice = 0;
        int counterLowerPrice = 0;
        int totalSoldSeats = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        if (totalSeats <= 60) {
            for (int i = 0; i <= cinema.length; i++) {
                for (int j = 0; j < cinema[0].length; j++) {
                    if ('B' == cinema[i][j]){
                        counterHigherPrice++;
                    }
                }

            }
        } else {
            // int backHalf = numRows - frontHalf;
            for (int i = 0; i < frontHalf; i++) {
                for (int j = 0; j < cinema[0].length; j++) {
                    if ('B' == cinema[i][j]) {
                        counterHigherPrice++;
                    }
                }
            }
            for (int i = frontHalf; i < numRows; i++) {
                for (int j = 0; j < cinema[0].length; j++) {
                    if ('B' == cinema[i][j]) {
                        counterLowerPrice++;
                    }
                }
            }
        }
        totalSoldSeats = counterHigherPrice + counterLowerPrice;
        System.out.println("Number of purchased tickets: " + totalSoldSeats);


        // fill rate
        double fillPercent = 100.0 * (counterLowerPrice + counterHigherPrice) / totalSeats;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Percentage: " + df.format(fillPercent ) + "%");

        // Current income
        currentIncome = counterHigherPrice * 10 + counterLowerPrice * 8;
        System.out.println("Current income: $" + currentIncome);

        // total income
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        }else {
            totalIncome = frontHalf * numSeats * 10 + (numRows - frontHalf) * numSeats * 8;

        }
        System.out.println("Total income: $" + totalIncome);
    }
}