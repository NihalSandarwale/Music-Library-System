package com.example.musiclibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musiclibrary.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
