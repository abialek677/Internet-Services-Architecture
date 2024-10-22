package pg.inf.sem5.gr2.aui.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pg.inf.sem5.gr2.aui.services.AlbumService;
import pg.inf.sem5.gr2.aui.services.SongService;
import pg.inf.sem5.gr2.aui.utility.MenuHelper;

import java.util.Scanner;

@Component
public class CmdRunner implements CommandLineRunner {
    private final MenuHelper menuHelper;

    @Autowired
    public CmdRunner(MenuHelper menuHelper) {
        this.menuHelper = menuHelper;
    }

    @Override
    public void run(String... args) throws Exception {


        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running){
            System.out.println("\nOptions:");
            System.out.println("1. List all albums");
            System.out.println("2. List all songs");
            System.out.println("3. List all songs in album");
            System.out.println("4. Add new album");
            System.out.println("5. Add new song");
            System.out.println("6. Delete album");
            System.out.println("7. Delete song");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    menuHelper.printAllAlbums();
                    break;
                case 2:
                    menuHelper.printAllSongs();
                    break;
                case 3:
                    menuHelper.printAllSongsInAlbum();
                    break;
                case 4:
                    menuHelper.addNewAlbum();
                    break;
                case 5:
                    menuHelper.addNewSong();
                    break;
                case 6:
                    menuHelper.deleteAlbum();
                    break;
                case 7:
                    menuHelper.deleteSong();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

        System.exit(0);
    }
}
