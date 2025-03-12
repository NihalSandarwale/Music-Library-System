package com.example.musiclibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musiclibrary.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
