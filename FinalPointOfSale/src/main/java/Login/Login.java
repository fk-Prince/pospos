package Login;

import Exceptions.InvalidInputException;
import PointOfSale.PointofSale;
import UserEntity.User;
import UserEntity.UserRepository;


import java.util.Optional;
import java.util.Scanner;

public class Login {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("[1] LOGIN\n[2] EXIT");
                System.out.print("Choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Username: ");
                        String uname = sc.nextLine();
                        System.out.print("Enter Password: ");
                        String pass = sc.nextLine();

                        boolean userIsPresent = UserRepository.isUserExist(new User(uname, pass));
                        if (userIsPresent) {
                            PointofSale pos = new PointofSale();
                            System.out.println("Successfully Logged in.");
                            pos.run();
                        } else {
                            System.out.println("User doesn't exist.");
                        }

                    }
                    case 2 -> System.exit(0);
                    default -> throw new InvalidInputException("Invalid Choice");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Choice");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}