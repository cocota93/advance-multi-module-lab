package org.jedy.oembed.repository.query;

import org.jedy.notice.domain.Notice;
import org.jedy.oembed_core.domain.Oembed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OembedQueryRepository  extends JpaRepository<Oembed, Long> {
}
