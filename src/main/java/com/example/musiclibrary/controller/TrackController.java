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

import com.example.musiclibrary.model.Track;
import com.example.musiclibrary.service.TrackService;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping
    public List<Track> getAllTracks() {
        return trackService.getAllTracks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getTrackById(@PathVariable Long id) {
        Optional<Track> track = trackService.getTrackById(id);
        return track.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Track addTrack(@RequestBody Track track) {
        return trackService.addTrack(track);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable Long id, @RequestBody Track trackDetails) {
        Optional<Track> trackOptional = trackService.getTrackById(id);
        if (!trackOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Track trackToUpdate = trackOptional.get();
        trackToUpdate.setName(trackDetails.getName());
        trackToUpdate.setAlbum(trackDetails.getAlbum());

        Track updatedTrack = trackService.updateTrack(trackToUpdate);
        return ResponseEntity.ok(updatedTrack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Long id) {
        if (!trackService.getTrackById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        trackService.deleteTrack(id);
        return ResponseEntity.noContent().build();
    }
}
