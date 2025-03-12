package com.example.musiclibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musiclibrary.model.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
