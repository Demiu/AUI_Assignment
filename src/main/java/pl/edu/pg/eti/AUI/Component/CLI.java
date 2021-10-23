package pl.edu.pg.eti.AUI.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.AUI.Company;
import pl.edu.pg.eti.AUI.Component.Service.CompanyService;
import pl.edu.pg.eti.AUI.Component.Service.PlayerService;
import pl.edu.pg.eti.AUI.Player;

import java.util.Optional;
import java.util.Scanner;

@Component
public class CLI implements CommandLineRunner {
    private CompanyService companyService;
    private PlayerService playerService;
    private Scanner scanner;

    @Autowired
    public CLI(CompanyService companyService, PlayerService playerService) {
        this.companyService = companyService;
        this.playerService = playerService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        boolean exit = false;

        while (!exit) {
            System.out.println("1) List categories");
            System.out.println("2) List all elements");
            System.out.println("3) Add a new element");
            System.out.println("4) Delete an element");
            System.out.println("0) Stop");
            System.out.println("Choose option:");

            int option = scanner.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("Company, Player");
                    break;
                }
                case 2: {
                    System.out.println("Companies:");
                    companyService.findAll().stream().forEach(System.out::println);
                    System.out.println("Players:");
                    playerService.findAll().forEach(System.out::println);
                    break;
                }
                case 3: {
                    addElement();
                    break;
                }
                case 4: {
                    remElement();
                    break;
                }
                case 0: {
                    exit = true;
                    break;
                }
                default:
                    System.out.println("Invalid option");
            }
        }

        System.out.println("Proper exit");
    }

    private void addElement() {
        System.out.println("1) Add a company");
        System.out.println("2) Add a player");

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1: {
                System.out.println("Specify the name:");
                String name = scanner.nextLine();
                System.out.println("Specify the name of the owner:");
                String owner_name = scanner.nextLine();
                Optional<Player> owner = playerService.find(owner_name);
                if (owner.isEmpty()) {
                    System.out.println("Couldn't find the player");
                    return;
                }
                System.out.println("Specify the share price:");
                int price = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Specify the share count");
                int shares = scanner.nextInt();
                scanner.nextLine();
                companyService.save(new Company(null, name, owner.get(), price, shares));
                System.out.println("Company added");
                break;
            }
            case 2: {
                System.out.println("Specify the name:");
                String name = scanner.nextLine();
                System.out.println("Specify their money:");
                int money = scanner.nextInt();
                scanner.nextLine();
                playerService.save(Player.builder().name(name).money(money).build());
                System.out.println("Player added");
                break;
            }
            default:
                System.out.println("Invalid option");
        }
    }

    private void remElement() {
        System.out.println("1) Remove a company");
        System.out.println("2) Remove a player");

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1: {
                System.out.println("Specify the name:");
                String name = scanner.nextLine();
                Optional<Company> company = companyService.find(name);
                if (company.isPresent()) {
                    companyService.delete(company.get());
                    System.out.println("Company deleted");
                } else {
                    System.out.println("Company not found");
                }
                break;
            }
            case 2: {
                System.out.println("Specify the name:");
                String name = scanner.nextLine();
                Optional<Player> player = playerService.find(name);
                if (player.isPresent()) {
                    playerService.delete(player.get());
                    System.out.println("Player deleted");
                } else {
                    System.out.println("Player not found");
                }
                break;
            }
            default:
                System.out.println("Invalid option");
        }
    }
}
