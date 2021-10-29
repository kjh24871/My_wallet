package kitae.foolaccount.controller;

import kitae.foolaccount.domain.Member;
import kitae.foolaccount.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class FIndInformationController {

    private final MemberRepository memberRepository;

    @Autowired
    public FIndInformationController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    @GetMapping("/find_id")
    public String find_id(){
        return "find_id";
    }

    @GetMapping("/login_with_id")
    public String login_with_id(){
        return "login_with_id";
    }

    @PostMapping("/find_id")
    public String findIdByInformation(MemberForm form, Model model) {
        Member lostMember = new Member();
        lostMember.setName(form.getName());
        lostMember.setPhone(form.getPhone());

        Optional<Member> findMember1 = memberRepository.findByName(form.getName());
        if (findMember1.isPresent())
        { // 사용자가 입력한 이름이 DB에 있을 때
            if (findMember1.get().getPhone().equals(lostMember.getPhone()))
            {
                model.addAttribute("name", findMember1.get().getName());
                model.addAttribute("id", findMember1.get().getId());
                return "login_with_id";
            }
            else
                model.addAttribute("flag","1");
                return "find_id";
        } else // 사용자가 입력한 이름이 DB에 없을 때
            model.addAttribute("flag","1");
            return "find_id";

    }

    @GetMapping("/find_password")
    public String find_password(){
        return "find_password";
    }

    @PostMapping("/find_password")
    public String findPasswordByInformation(Model model, MemberForm form){
        Member lostMember = new Member();
        lostMember.setPassword_confirm_question(form.getPassword_confirm_question());
        lostMember.setPassword_confirm_question_answer(form.getPassword_confirm_question_answer());

        Optional<Member> findMember1 = memberRepository.findById(form.getId());


        if(findMember1.isPresent())
        { // 사용자가 입력한 아이디가 DB에 있을 때
            if (lostMember.getPassword_confirm_question().equals(findMember1.get().getPassword_confirm_question())) {
                if (lostMember.getPassword_confirm_question_answer().equals(findMember1.get().getPassword_confirm_question_answer())) {
                    model.addAttribute("name", findMember1.get().getName());
                    model.addAttribute("password", findMember1.get().getPassword());
                    return "login_with_password";
                } else
                    model.addAttribute("flag","1");
                    return "find_password";
            } else // 사용자가 입력한 아이디이 DB에 없을 때
                model.addAttribute("flag","1");
                return "find_password";
        }
        else
            model.addAttribute("flag","1");
            return "find_password";

        }
}



