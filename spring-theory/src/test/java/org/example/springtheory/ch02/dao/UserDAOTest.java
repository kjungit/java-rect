package org.example.springtheory.ch02.dao;

import org.example.springtheory.ch02.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(DaoFactory.class)
class UserDAOTest {

    // @Autowired : 타입(UserDAO)이 일치하는 빈을 스프링이 찾아 이 필드에 주입해x준다.
    @Autowired // 더 이상 직접 new AnnotationConfigApplicationContext / getBean 을 호출할 필요가 없다.
    private UserDAO userDAO;

    // @BeforeEach : 각 @Test 메서드가 실행되기 '직전'마다 매번 호출된다(공통 준비 작업).
    //  - deleteAll()로 '항상 동일한 출발 상태(0건)'에서 테스트가 시작되게 한다.
    //    -> 테스트끼리 데이터가 섞이지 않고, 같은 테스트를 몇 번 돌려도 결과가 같다(반복 가능).
    //       (이전에는 고정 id 때문에 두 번 실행하면 중복키로 실패했는데, 그 문제가 사라진다.)
    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        userDAO.deleteAll();
    }

    private User newUser(String id, String name, String password) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        return user;
    }

    // @Test : add() 자체가 정상 동작하는지에 집중한 테스트.
    @Test
    void add_메서드_테스트() throws SQLException, ClassNotFoundException {
        // given
        User user = newUser("test123", "test234", "321");

        // when
        userDAO.add(user);

        // then
        assertEquals("test234", userDAO.get("test123").getName());
    }

    @Test
    void get_메서드_테스트() throws SQLException, ClassNotFoundException {
        // given
        User user = newUser("test123", "test234", "321");

        // when
        userDAO.add(user);

        // then
        assertEquals("test234", userDAO.get("test123").getName());
    }

    @Test
    void add_중복_id_예외() throws SQLException, ClassNotFoundException {

        final User user = newUser("dup_id", "사용자1", "3210");

        // 정상동작
        userDAO.add(user);

        // 익명클래스
        /*
        Executable action = new Executable() {
            @Override
            public void execute() throws Throwable {
                // 예외
                userDAO.add(user);
            }
        };

        assertThrows(SQLException.class, action);
        */

        assertThrows(SQLException.class, () -> userDAO.add(user));
    }

    @Test
    void get_없는_id_예외() {
        assertThrows(SQLException.class, () -> userDAO.get("존재하지_않는_id"));
    }

    @Disabled("일부러 틀린 기댓값을 넣은 학습용 실패 예제 - 실패 메시지를 보고 싶을 때만 활성화")
    @Test
    void 일부러_실패하는_테스트() throws SQLException, ClassNotFoundException {
        userDAO.add( newUser("fail_demo", "fail", "1234") );

        assertEquals(2, userDAO.getCount());
    }

}