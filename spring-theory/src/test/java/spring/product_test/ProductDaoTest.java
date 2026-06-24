package spring.product_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
class ProductDaoTest {
    //    private ProductDao dao = new ProductDao();
    @Autowired
    private ProductDao dao;

    @BeforeEach
    void setUp() {
        dao.deleteAll();          // 매 테스트 직전 0건으로 리셋
    }


    @Test
    void add() {
        // given : 비어 있는 상태(0건)
        assertEquals(0, dao.getCount());
        Product product = newProduct("p1", "연필", 500);
        // when : 저장
        dao.add(product);
        // then : 1건이 되어야 한다  (기대값 먼저, 실제값 나중!)
        assertEquals(1, dao.getCount());
    }

    @Test
    void get() {
        // given : 조회 대상 미리 저장
        Product product = newProduct("p1", "연필", 500);
        dao.add(product);

        // when : id로 조회
        Product found = dao.get("p1");

        // then : 모든 필드가 같아야 한다
        assertEquals(product.getName(), found.getName());
        assertEquals(product.getPrice(), found.getPrice());
    }

//    @Disabled("일부러 틀린 기대값을 넣은 학습용 실패 예제")
//    @Test
//    void 일부러_실패하는_테스트() {
//        dao.add(newProduct("fail_demo", "공책", 1000));
//        assertEquals(2, dao.getCount());   // 실제는 1건인데 2 기대 → 실패
//    }

    private Product newProduct(String id, String name, int price) {
        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setPrice(price);
        return p;
    }

}