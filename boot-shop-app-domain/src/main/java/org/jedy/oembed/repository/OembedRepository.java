package org.jedy.oembed.repository;

import org.jedy.oembed.domain.Oembed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OembedRepository extends JpaRepository<Oembed, Long> {
}
