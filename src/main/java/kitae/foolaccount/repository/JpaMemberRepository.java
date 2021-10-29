package kitae.foolaccount.repository;

import kitae.foolaccount.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
    public Optional<Member> findById(String id) {
//        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
//                .setParameter("id", id)
//                .getResultList();
//
//        return result.stream().findAny();
        Member find_member = em.find(Member.class, id);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByPassword(String password) {
//        List<Member> result = em.createQuery("select m from Member m where m.password = :password", Member.class)
//                .setParameter("password", password)
//                .getResultList();
//
//        return result.stream().findAny();
        Member find_member = em.find(Member.class, password);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByName(String name) {
//        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//
//        return result.stream().findAny();
        Member find_member = em.find(Member.class, name);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByPhone(String phone) {
//        List<Member> result = em.createQuery("select m from Member m where m.phone = :phone", Member.class)
//                .setParameter("phone", phone)
//                .getResultList();
//
//        return result.stream().findAny();
        Member find_member = em.find(Member.class, phone);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByPassword_confirm_question(String password_confirm_question) {
//        List<Member> result = em.createQuery("select m from Member m where m.password_confirm_question = :password_confirm_question", Member.class)
//                .setParameter("password_confirm_question", password_confirm_question)
//                .getResultList();
//
//        return result.stream().findAny();
        Member find_member = em.find(Member.class, password_confirm_question);
        return Optional.ofNullable(find_member);
    }

    @Override
    public Optional<Member> findByPassword_confirm_question_answer(String password_confirm_question_answer) {
//        List<Member> result = em.createQuery("select m from Member m where m.password_confirm_question_answer = :password_confirm_question_answer", Member.class)
//                .setParameter("password_confirm_question_answer", password_confirm_question_answer)
//                .getResultList();
//
//        return result.stream().findAny();
        Member find_member = em.find(Member.class, password_confirm_question_answer);
        return Optional.ofNullable(find_member);
    }
}
