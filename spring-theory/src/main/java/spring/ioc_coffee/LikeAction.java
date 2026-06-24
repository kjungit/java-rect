package spring.ioc_coffee;

public class LikeAction implements ClickListener{

    @Override
    public void onClick() {
        System.out.println("내 코드 실행");
    }
}
