package org.jedy.member.controller;

import lombok.RequiredArgsConstructor;
import org.jedy.member.dto.MemberResponse;
import org.jedy.member.dto.MemberSearchCondition;
import org.jedy.member.service.MemberPagingServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberPagingServiceImpl memberPagingServiceImpl;


  @GetMapping
  public String getPage(Model model) {
    model.addAttribute("name", "jedy");
    return "hihi";
  }


  @GetMapping("/list")
  public String getList(Model model, MemberSearchCondition condition, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
    Page<MemberResponse> pageList = memberPagingServiceImpl.searchPaging(condition, page, size);
    model.addAttribute("pageList", pageList);
    return "members/list";
  }

  @GetMapping("/listResponseBoy")
  @ResponseBody
  public Page listResponseBoy(MemberSearchCondition condition, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
    Page<MemberResponse> pageList = memberPagingServiceImpl.searchPaging(condition, page, size);
    return pageList;
  }

}
