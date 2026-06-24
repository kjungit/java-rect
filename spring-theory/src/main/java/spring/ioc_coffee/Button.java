package spring.ioc_coffee;

public class Button {
    private ClickListener listener;

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public void press() {
        System.out.println("버튼 클릭");
        listener.onClick();
    }
}
