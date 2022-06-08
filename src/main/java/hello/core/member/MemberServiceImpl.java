package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // MemberRepository 인터페이스만 의존
    public MemberServiceImpl(MemberRepository memberRepository) { // 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중
        this.memberRepository = memberRepository; // MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다. 외부(AppConfig)에서 결정된다.
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
