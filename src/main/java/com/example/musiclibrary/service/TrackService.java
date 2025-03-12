package com.example.musiclibrary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musiclibrary.model.Track;
import com.example.musiclibrary.repository.TrackRepository;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public Optional<Track> getTrackById(Long id) {
        return trackRepository.findById(id);
    }

    public Track addTrack(Track track) {
        return trackRepository.save(track);
    }

    public Track updateTrack(Track track) {
        return trackRepository.save(track);
    }

    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }
}
