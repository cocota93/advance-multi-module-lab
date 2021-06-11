package org.jedy.oembed.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.oembed.domain.Oembed;
import org.jedy.oembed.dto.OembedReceiver;
import org.jedy.oembed.dto.ResOembed;
import org.jedy.oembed.dto.mapper.OembedMapper;
import org.jedy.oembed.service.OembedServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/oembed")
@RequiredArgsConstructor
@Slf4j
public class OembedController {

    private final OembedServiceImpl oembedService;


    @GetMapping(value = "/search")
    public String searchPage(Model model, String searchUrl) throws Exception {
        if(!StringUtils.isEmpty(searchUrl)){
            OembedReceiver oembedReceiver = oembedService.searchFromExternal(searchUrl);
            Oembed oembed = OembedMapper.INSTANCE.dtoToEntity(oembedReceiver);
            ResOembed resOembed = OembedMapper.INSTANCE.entityToDto(oembed);
//
            model.addAttribute("searchResult", resOembed);
            model.addAttribute("searchUrl", searchUrl);
        }

        return "/oembed/search";
    }

    @PostMapping(value = "/search")
    public String search(RedirectAttributes redirectAttributes, String searchUrl) throws Exception {
        if(!StringUtils.isEmpty(searchUrl)){
            redirectAttributes.addAttribute("searchUrl", searchUrl);
        }
        return "redirect:/oembed/search";
    }
}
