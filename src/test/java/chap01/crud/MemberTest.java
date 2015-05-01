package chap01.crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import util.HibernateUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by 2ssoon on 5/1/15.
 */
public class MemberTest {

    public static final String SSOON = "2SsooN";
    public static final String HELLO_WORLD = "Hello World";
    public static final String HELLO_HIBERNATE = "Hello Hibernate";
    SessionFactory factory = HibernateUtil.getSessionFactory();

    @Test
    public void crudTest() {
        // Insert
        Member member = new Member(SSOON, HELLO_WORLD);
        insert(member);

        // Select One
        Member selectedMember = selectById(1);
        assertEquals(HELLO_WORLD, selectedMember.getMessage());

        // Update
        selectedMember.setMessage(HELLO_HIBERNATE);
        update(selectedMember);
        Member updatedMember = selectById(1);
        assertEquals(HELLO_HIBERNATE, updatedMember.getMessage());

        // Delete
        delete(updatedMember);
        Member deletedMember = selectById(1);
        assertNull(deletedMember);

        /*assertEquals(1, 1);*/
        /*fail("Not yet implemented");*/
    }

    public void delete(Member updatedMember) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(updatedMember);
        session.getTransaction().commit();
    }

    public void update(Member selectedMember) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.update(selectedMember);
        session.getTransaction().commit();
    }

    private Member selectById(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Member selectedMember = (Member) session.get(Member.class, id);
        session.getTransaction().commit();
        return selectedMember;
    }

    public void insert(Member member) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(member);
        session.getTransaction().commit();
    }
}
