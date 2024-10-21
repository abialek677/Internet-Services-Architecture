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
    private final AlbumService albumService;
    private final SongService songService;

    @Autowired
    public CmdRunner(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
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
                    MenuHelper.printAllAlbums();
                    break;
                case 2:
                    MenuHelper.printAllSongs();
                    break;
                case 3:
                    MenuHelper.printAllSongsInAlbum();
                    break;
                case 4:
                    MenuHelper.addNewAlbum();
                    break;
                case 5:
                    MenuHelper.addNewSong();
                    break;
                case 6:
                    MenuHelper.deleteAlbum();
                    break;
                case 7:
                    MenuHelper.deleteSong();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
