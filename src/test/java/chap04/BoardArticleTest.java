package chap04;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import chap01.crud.Member;
import util.DaoCommon;

import static org.junit.Assert.assertEquals;

/**
 * Created by 2ssoon on 5/5/15.
 */
public class BoardArticleTest {

    DaoCommon<BoardArticle> daoBoard = new DaoCommon<BoardArticle>(BoardArticle.class);
    DaoCommon<Member> daoMember = new DaoCommon<Member>(Member.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test() {
        BoardArticle boardArticle = new BoardArticle("2SsooN", "hello", new Date());
        daoBoard.insert(boardArticle);

        BoardArticle getBoardArticle = daoBoard.selectById(1);
        assertEquals("2SsooN", getBoardArticle.getUserId());
    }
}