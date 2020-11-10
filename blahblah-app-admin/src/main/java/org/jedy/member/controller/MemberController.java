package org.jedy.member.controller;

import lombok.RequiredArgsConstructor;
import org.jedy.member.dto.MemberResponse;
import org.jedy.member.dto.MemberSearchCondition;
import org.jedy.member.exception.MemberNotFoundException;
import org.jedy.member.exception.MemberSomethingException;
import org.jedy.member.repository.query.MemberQueryRepository;
import org.jedy.member_core.domain.Member;
import org.jedy.member_core.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberRepository memberRepository;
  private final MemberQueryRepository memberQueryRepository;


  @GetMapping
  public String getPage(Model model) {
    model.addAttribute("name", "jedy");
    return "hihi";
  }


  @GetMapping("/list")
  public String getList(Model model, MemberSearchCondition condition, Pageable pageable) {
    Page<MemberResponse> pageList = memberQueryRepository.searchPageComplex(condition, pageable);
    model.addAttribute("pageList", pageList);

    return "members/list";
  }


  @GetMapping("/notFound")
  public MemberResponse notFoundError(Model model) {

    try {
      Optional<Member> gflhkflkgh = memberRepository.findById(7575757L);
      System.out.println("name : " + gflhkflkgh.get().getName());
      return new MemberResponse(gflhkflkgh.get());
    } catch (Exception e) {
      e.printStackTrace();
      throw new MemberNotFoundException("gflhkflkgh");
    }
  }

  @GetMapping("/somethingError")
  public MemberResponse somethingError(Model model) {

    try {
      Optional<Member> gflhkflkgh = memberRepository.findById(7575757L);
      System.out.println("name : " + gflhkflkgh.get().getName());
      return new MemberResponse(gflhkflkgh.get());
    } catch (Exception e) {
      e.printStackTrace();
      throw new MemberSomethingException();
    }
  }


}
