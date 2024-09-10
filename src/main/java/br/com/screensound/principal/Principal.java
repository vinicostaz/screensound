package br.com.screensound.principal;

import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    public void showMenu() {
        var option = -1;

        while (option != 9) {
            var menu = """
                    *** Screen Sound Music ***
                                       \s
                    1- Register artists
                    2- Register songs
                    3- List songs
                    4- Search songs by artists
                    5- Search data about an artist
                                   \s
                    9 - Exit
                   \s""";

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registerArtist();
                    break;
                case 2:
                    registerSongs();
                    break;
                case 3:
                    listSongs();
                    break;
                case 4:
                    searchSongsByArtist();
                    break;
                case 5:
                    searchArtistData();
                    break;
                case 9:
                    System.out.println("Shutting down the application!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void registerArtist() {
    }

    private void registerSongs() {
    }

    private void listSongs() {
    }

    private void searchSongsByArtist() {
    }

    private void searchArtistData() {
    }
}
