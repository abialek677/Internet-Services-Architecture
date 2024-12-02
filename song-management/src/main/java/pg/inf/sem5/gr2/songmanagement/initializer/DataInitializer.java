package pg.inf.sem5.gr2.songmanagement.initializer;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.inf.sem5.gr2.songmanagement.DTOs.SongCreateDTO;
import pg.inf.sem5.gr2.songmanagement.model.Song;
import pg.inf.sem5.gr2.songmanagement.repositories.SongRepository;
import pg.inf.sem5.gr2.songmanagement.services.SongService;

import java.util.UUID;

@Component
public class DataInitializer implements InitializingBean {
    private SongService songService;

    @Autowired
    public DataInitializer(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SongCreateDTO u2Song1 = SongCreateDTO.builder()
                .title("With or Without You")
                .duration(296)
                .album("The Joshua Tree")
                .build();

        SongCreateDTO u2Song2 = SongCreateDTO.builder()
                .title("I Still Haven't Found What I'm Looking For")
                .duration(277)
                .album("The Joshua Tree")
                .build();

        SongCreateDTO depecheModeSong1 = SongCreateDTO.builder()
                .title("Personal Jesus")
                .duration(285)
                .album("Violator")
                .build();

        SongCreateDTO depecheModeSong2 = SongCreateDTO.builder()
                .title("Enjoy the Silence")
                .duration(246)
                .album("Violator")
                .build();

        SongCreateDTO coldplaySong1 = SongCreateDTO.builder()
                .title("The Scientist")
                .duration(311)
                .album("A Rush of Blood to the Head")
                .build();

        SongCreateDTO coldplaySong2 = SongCreateDTO.builder()
                .title("Clocks")
                .duration(307)
                .album("A Rush of Blood to the Head")
                .build();

        songService.create(u2Song1);
        songService.create(u2Song2);
        songService.create(depecheModeSong1);
        songService.create(depecheModeSong2);
        songService.create(coldplaySong1);
        songService.create(coldplaySong2);
    }

}
