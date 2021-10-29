package kitae.foolaccount.controller;

import kitae.foolaccount.domain.Member;
import kitae.foolaccount.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.util.Optional;

@Controller
public class LoginController {

    private final MemberRepository memberRepository;

    @Autowired
    public LoginController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/login")
    public String login(){              // DB에 있는 값을 토대로 로그인 할 예정
        return "login";
    }

    @PostMapping("/login")
    public String memberLogin(MemberForm form, Model model)
    {
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPassword()); //현재 화면에서 입력한 ID, PASSWORD

        Optional<Member> find_member1 = memberRepository.findById(member.getId());


        if(find_member1.isPresent()){  // 아이디 값이 DB에 있을 때
            if(find_member1.get().getPassword().equals(member.getPassword()))
            {
                System.out.println("login complete");
                model.addAttribute("name",find_member1.get().getName());
                return "login_complete";

            }
            else
            {
                model.addAttribute("flag","1");
                System.out.println("not password");
                return "login";
            }
        }
        else{  // 아이디 값이 DB에 없을 때 NULL 관련된 에러를 잡기 위함
            model.addAttribute("flag","1");
            System.out.println("no found");
            return "login";
        }
    }



}