package org.jedy.oembed.service;

import lombok.RequiredArgsConstructor;
import org.jedy.oembed.domain.Oembed;
import org.jedy.oembed.dto.OembedReceiver;
import org.jedy.oembed.repository.OembedRepository;
import org.jedy.oembed.util.OembedUrlUtil;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.jedy.system_core.global.error.exception.ErrorCode;
import org.jedy.system_core.util.ConverterUtil;
import org.jedy.system_core.util.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/*
* oembed때문에 외부api호출하는 클래스를 하나 더 만들어서 서버내에서 로직처리하는 클래스와 
* 분리하는게 맞는걸까? 일단 명확한 이유가 없거나 애매할때는 놔두는게 맞는것같음
* */

@Service
@Transactional
@RequiredArgsConstructor
public class OembedServiceImpl {

    private final OembedRepository oembedRepository;

    public Long create(String jsonString) throws IOException {
        Oembed oembed = ConverterUtil.convertJsonStringToObject(jsonString, Oembed.class);
        return oembedRepository.save(oembed).getId();
    }

    public Oembed findById(Long createId) {
        return oembedRepository.findById(createId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getMessage() + " createdId : " + createId));
    }

    public OembedReceiver searchFromExternal(String searchUrl) throws Exception {
        StringBuilder jsonString = HttpUtil.get(OembedUrlUtil.createApiUrl(searchUrl));
        OembedReceiver oembedReceiver = ConverterUtil.convertJsonStringToObject(jsonString.toString(), OembedReceiver.class);
        return oembedReceiver;
    }
}
