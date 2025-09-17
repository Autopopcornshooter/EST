package EST.SubjectDay9.controller;

import EST.SubjectDay9.DTO.AddAlbumRequest;
import EST.SubjectDay9.entity.Album;
import EST.SubjectDay9.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService){
        this.albumService=albumService;
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestParam AddAlbumRequest request){
        Album album=albumService.saveAlbum(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(album);
    }
    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id){
        albumService.deleteAlbum(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, AddAlbumRequest request){
        Album album=albumService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(album);
    }


}
