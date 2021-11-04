package kitae.foolaccount.repository;

import kitae.foolaccount.domain.Member;
import kitae.foolaccount.domain.Stock_add_member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository{
    Member save(Member member);
    Stock_add_member save_stock(Stock_add_member stock_add_member);
    Optional<Member> findById(String id);
    Optional<Member> findByPassword(String password);
    Optional<Member> findByName(String name);
    Optional<Member> findByPhone(String phone);
    Optional<Member> findByPassword_confirm_question(String password_confirm_question);
    Optional<Member> findByPassword_confirm_question_answer(String password_confirm_question_answer);
    List<Stock_add_member> findAllByStockId(String id);
}