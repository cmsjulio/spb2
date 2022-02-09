package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.service.AnimeService;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//anotação que diz que essa classe é um controler, e que todos os métodos vão adiconar
//o response body: o retorno dos métodos dessa classe são apenas STRINGS.

@RequestMapping("animes")
@Log4j2
@AllArgsConstructor

public class AnimeController {
//    private DateUtil dateUtil = new DateUtil();
//    em vez do código acima, utilizamos injeção de dependências:

    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    // localhost:8080/anime/list
    public ResponseEntity<List<Anime>>  list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping(path = "/{id}")
    // localhost:8080/anime/list
    public ResponseEntity<Anime>  findById(@PathVariable long id){
        return ResponseEntity.ok(animeService.findById(id));
    }

    @PostMapping(path="/salvar")
    public ResponseEntity<Anime> save (@RequestBody Anime anime){
        return new ResponseEntity<>(animeService.save (anime), HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> delete (@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace (@RequestBody Anime anime){
        animeService.replace(anime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
