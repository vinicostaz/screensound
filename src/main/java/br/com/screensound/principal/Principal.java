package br.com.screensound.principal;

import br.com.screensound.model.Artist;
import br.com.screensound.model.ArtistType;
import br.com.screensound.model.Music;
import br.com.screensound.repository.ArtistRepository;
import br.com.screensound.service.ChatGPTQuery;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistRepository repository;
    private Scanner scanner = new Scanner(System.in);

    public Principal(ArtistRepository repository) {
        this.repository = repository;
    }

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
        var newRegister = "Y";

        while (newRegister.equalsIgnoreCase("Y")) {
            System.out.println("Enter the name of the artist: ");
            var name = scanner.nextLine();
            System.out.println("Enter the type of the artist: (Solo, duo, or band)");
            var type = scanner.nextLine();
            ArtistType artistType = ArtistType.valueOf(type.toUpperCase());
            Artist artist = new Artist(name, artistType);
            repository.save(artist);
            System.out.println("Register a new artist? (Y/N)");
            newRegister = scanner.nextLine();
        }

    }

    private void registerSongs() {
        System.out.println("Register a music from which artist? ");
        var name = scanner.nextLine();
        Optional<Artist> artist = repository.findByNameContainingIgnoreCase(name);
        if (artist.isPresent()) {
            System.out.println("Enter the title of the music: ");
            var musicTitle = scanner.nextLine();
            Music music = new Music(musicTitle);
            music.setArtist(artist.get());
            artist.get().getMusics().add(music);
            repository.save(artist.get());
        } else {
            System.out.println("Artist not found!");
        }
    }

    private void listSongs() {
        List<Artist> artists = repository.findAll();
        artists.forEach(a -> a.getMusics().forEach(System.out::println));
    }

    private void searchSongsByArtist() {
        System.out.println("Search song by which artist? ");
        var name = scanner.nextLine();
        List<Music> musics = repository.searchMusicByArtist(name);
        musics.forEach(System.out::println);
    }

    private void searchArtistData() {
        System.out.println("Search information about which artist? ");
        var name = scanner.nextLine();
        var response = ChatGPTQuery.getInfo(name);
        System.out.println(response.trim());

    }
}
