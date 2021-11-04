package kitae.foolaccount.service;

import kitae.foolaccount.domain.Member;
import kitae.foolaccount.domain.Stock_add_member;
import kitae.foolaccount.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        memberRepository.save(member);
        return member.getCount();
    }
    public Long join_stock(Stock_add_member stock_add_member){
        memberRepository.save_stock(stock_add_member);
        return stock_add_member.getSequence();
    }

    public List<Stock_add_member> findAllStockMemberById(String id){
        return memberRepository.findAllByStockId(id);

    }

} 