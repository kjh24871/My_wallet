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

    @GetMapping("/login_fail")
    public String retryLogin(){
        return "login_fail";
    }

    @PostMapping("/login_fail")
    public String memberLoginfail(MemberForm form, Model model)
    {
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPassword()); //현재 화면에서 입력한 ID, PASSWORD

        Optional<Member> find_member1 = this.memberRepository.findById(member.getId());

        //String resultMessage="";


        if (find_member1.isPresent()) {  // 아이디 값이 DB에 있을 때
            if (find_member1.get().getPassword().equals(member.getPassword())) {
                System.out.println("login complete");
                model.addAttribute("name", find_member1.get().getName());
                return "login_complete";

            } else {
                System.out.println("not password");
                return "login_fail";
            }
        } else {  // 아이디 값이 DB에 없을 때 NULL 관련된 에러를 잡기 위함
            System.out.println(member.getId());
            System.out.println(find_member1.get().getId());
            System.out.println("no found");
            return "login_fail";
        }
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
            System.out.println(member.getId());

            System.out.println("no found");
            return "login";

//            resultMessage="<script>alert('올바르지 않은 아이디입니다. 다시 시도해주세요.');location.href='login'</script>";
//            return resultMessage;
        }

//        if(memberRepository.findByPassword(member.getPassword()).isPresent()){  // 비밀번호 값이 DB에 있을 때
//            find_member2 = memberRepository.findByPassword(member.getPassword()).get();
//        }
//        else{  // 비밀번호 값이 DB에 없을 때 NULL 관련된 에러를 잡기 위함
//            return "login_fail";
////            resultMessage="<script>alert('올바르지 않은 비밀번호입니다. 다시 시도해주세요.');location.href='login'</script>";
////            return resultMessage;
//        }
//
//        if(find_member1.getId().equals(member.getId())&&find_member2.getPassword().equals(member.getPassword())&&find_member1.getCount().equals(find_member2.getCount())){ // 아이디값과 비밀번호값과 카운트가 같을 때 로그인 완료
//            model.addAttribute("name", find_member1.getName());
//            return "login_complete";
////            resultMessage="<script>alert('로그인에 성공하셨습니다. 얼간증권을 이용해주셔서 감사합니다.');location.href='login_complete'</script>";
////            return resultMessage;
//        }
//        else{
//            return "login_fail";
////            resultMessage="<script>alert('로그인에 실패하셨습니다. 다시 시도해주세요.');location.href='login'</script>";
////            return resultMessage;
//        }
//
    }
//


}