package org.jedy.oembed.repository.query;

import org.jedy.oembed.domain.Oembed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OembedQueryRepository  extends JpaRepository<Oembed, Long> {
}
