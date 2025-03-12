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

import com.example.musiclibrary.model.Artist;
import com.example.musiclibrary.service.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Optional<Artist> artist = artistService.getArtistById(id);
        return artist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Artist addArtist(@RequestBody Artist artist) {
        return artistService.addArtist(artist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist artistDetails) {
        Optional<Artist> artistOptional = artistService.getArtistById(id);
        if (!artistOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Artist artistToUpdate = artistOptional.get();
        artistToUpdate.setName(artistDetails.getName());

        Artist updatedArtist = artistService.updateArtist(artistToUpdate);
        return ResponseEntity.ok(updatedArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        if (!artistService.getArtistById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
