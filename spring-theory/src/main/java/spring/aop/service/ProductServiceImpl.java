package spring.aop.service;

public class ProductServiceImpl implements ProductService {
    @Override
    public String productOrder( String member ) {
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
