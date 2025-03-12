package com.example.musiclibrary.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.musiclibrary.model.Album;
import com.example.musiclibrary.service.AlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumService.getAlbumById(id);
        return album.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Album addAlbum(@RequestBody Album album) {
        return albumService.addAlbum(album);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album albumDetails) {
        Optional<Album> albumOptional = albumService.getAlbumById(id);
        if (!albumOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Album albumToUpdate = albumOptional.get();
        albumToUpdate.setTitle(albumDetails.getTitle());
        albumToUpdate.setArtist(albumDetails.getArtist());

        Album updatedAlbum = albumService.updateAlbum(albumToUpdate);
        return ResponseEntity.ok(updatedAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        if (!albumService.getAlbumById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
