package org.jedy.oembed_core.repository;

import org.jedy.oembed_core.domain.Oembed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OembedRepository extends JpaRepository<Oembed, Long> {
}
