package br.com.screensound.repository;

import br.com.screensound.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findByNameContainingIgnoreCase(String name);
}
