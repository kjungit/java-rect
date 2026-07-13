package org.example.basicboard.domian.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.basicboard.domian.entity.QBoard;
import org.example.basicboard.domian.entity.QComment;
import org.example.basicboard.domian.entity.QMember;
import org.example.basicboard.dto.BoardListItemResponseDto;
import org.example.basicboard.dto.BoardSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final QBoard board = QBoard.board;
    private static final QComment comment = QComment.comment;
    private static final QMember member = QMember.member;

    @Override
    public Page<BoardListItemResponseDto> searchBoards( BoardSearchRequestDto condition, Pageable pageable ) {
        List<BoardListItemResponseDto> content = queryFactory.select(
                        Projections.constructor(BoardListItemResponseDto.class,
                                                board.id,
                                                board.title,
                                                board.userId,
                                                member.username,
                                                commentCountOf(board),
                                                board.created
                                               )

                                                                    )
                .from(board)
                .leftJoin(member)
                .on(board.userId.eq(member.userId))
                .where(
                        titleContains(condition.getTitle()),
                        userIdEquals(condition.getUserId()),
                        createdGoe(condition.getFrom()),
                        createdLoe(condition.getTo())
                      )
                .orderBy(board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        JPAQuery<Long> countQuery = queryFactory
                .select(board.count())
                .from(board)
                .where(
                        titleContains(condition.getTitle()),
                        userIdEquals(condition.getUserId()),
                        createdGoe(condition.getFrom()),
                        createdLoe(condition.getTo())
                      );


        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    // 제목 부분 일치 (LIKE %title%) 빈 값이면 조건 없음(null)
    private BooleanExpression titleContains( String title ) {
        return (title == null || title.isBlank()) ? null : board.title.contains(title);
    }

    // 작성자 아이이디 정확히 일치. 빈 값이면 조건 없음(null)
    private BooleanExpression userIdEquals( String userId ) {
        return (userId == null || userId.isBlank()) ? null : board.userId.eq(userId);
    }

    /**
     * goe / loe 는 비교 연산자의 약이다
     * gt(Greater Than, >)
     * goe(Greater Than or Equeal. >=)
     * lt(Less Than)
     * loe(Less Than or Equal, <=)
     * -> 아래 goe + loe 한 쌍이 "from 이상 And to 이하" => Between 기간 검색이 된다.
     */

    private BooleanExpression createdGoe( LocalDate from ) {
        return from == null ? null : board.created.goe(from.atStartOfDay());
    }

    private BooleanExpression createdLoe( LocalDate to ) {
        return to == null ? null : board.created.loe(to.atTime(LocalTime.MAX));
    }

    // * JPAQueryFactory 와 JPAExpressions
    // - JPAQueryFactory : EntityManager를 품은 "본 쿼리" 생성기. fetch()/fetchOne()으로 SQL을 "실행"할 수 있다.
    // - JPAExpressions : static 유틸. "서브쿼리 표현식(조각)"만 만들고, 실행 능력이 없다.

    private Expression<Long> commentCountOf( QBoard board ) {
        return JPAExpressions
                .select(comment.count())
                .from(comment)
                .where(comment.board.id.eq(board.id));
    }
}
