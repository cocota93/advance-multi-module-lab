package org.jedy.oembed.dto.mapper;

import org.jedy.oembed.domain.Oembed;
import org.jedy.oembed.dto.OembedReceiver;
import org.jedy.oembed.dto.ResOembed;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OembedMapper  {
    OembedMapper INSTANCE = Mappers.getMapper(OembedMapper.class);

    Oembed dtoToEntity(OembedReceiver oembedReceiver);

    ResOembed entityToDto(Oembed oembed);
}
