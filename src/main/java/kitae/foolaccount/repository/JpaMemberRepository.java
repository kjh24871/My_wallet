package kitae.foolaccount.repository;

import kitae.foolaccount.domain.Member;
import kitae.foolaccount.domain.Stock_add_member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.*;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Stock_add_member save_stock(Stock_add_member stock_add_member) {
        long index = em.createQuery("select m from Stock_add_member m",Stock_add_member.class)
                .getResultList().size()+1;
        stock_add_member.setSequence(index);
        em.persist(stock_add_member);
        return stock_add_member;
    }

    @Override
    public Optional<Member> findById(String id) {
        Member find_member = em.find(Member.class, id);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByPassword(String password) {
        Member find_member = em.find(Member.class, password);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        Member find_member = em.find(Member.class, name);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByPhone(String phone) {
        Member find_member = em.find(Member.class, phone);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByPassword_confirm_question(String password_confirm_question) {
        Member find_member = em.find(Member.class, password_confirm_question);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByPassword_confirm_question_answer(String password_confirm_question_answer) {
        Member find_member = em.find(Member.class, password_confirm_question_answer);
        return Optional.ofNullable(find_member);
    }
    @Override
    public List<Stock_add_member> findAllByStockId(String id){
        return em.createQuery("select m from Stock_add_member m where m.id=:id",Stock_add_member.class)
                .setParameter("id",id)
                .getResultList();
    }

}
