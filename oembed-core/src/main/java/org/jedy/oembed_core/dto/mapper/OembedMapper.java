package org.jedy.oembed_core.dto.mapper;

import org.jedy.oembed_core.domain.Oembed;
import org.jedy.oembed_core.dto.OembedReceiver;
import org.jedy.oembed_core.dto.ResOembed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OembedMapper  {
    OembedMapper INSTANCE = Mappers.getMapper(OembedMapper.class);

    Oembed dtoToEntity(OembedReceiver oembedReceiver);

    ResOembed entityToDto(Oembed oembed);
}
