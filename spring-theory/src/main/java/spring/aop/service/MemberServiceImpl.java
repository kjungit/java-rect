package spring.aop.service;

public class MemberServiceImpl implements MemberService {
    @Override
    public String register( String member ) {
        sleep(80);
        return member;
    }

    public void sleep( long millis ) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }
}
