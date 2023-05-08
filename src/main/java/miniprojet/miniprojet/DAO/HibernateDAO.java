package miniprojet.miniprojet.DAO;
import miniprojet.miniprojet.Model.Article;
import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class HibernateDAO {

    private SessionFactory sessionFactory;

    public void Inscription(String mail, String mdp){
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "insert into Utilisateur (mail, mdp) values (:mail, :mdp)";
            Query query = session.createNativeQuery(sql);
            query.setParameter("mail", mail);
            query.setParameter("mdp", mdp);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    public Object findForArticle(String sql){
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(sql);
        Object ans = (Object) query.uniqueResult();
        return ans;
    }
    public void LikeArticle(int idArticle, int idUser){
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "insert into LikeArticle (idArticle, idUser) values (:idArticle, :idUser)";
            Query query = session.createNativeQuery(sql);
            query.setParameter("idArticle", idArticle);
            query.setParameter("idUser", idUser);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    public void DisLike(int idArticle, int idUser){
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "DELETE FROM LikeArticle where idarticle ="+idArticle+" and iduser ="+idUser;
            Query query = session.createNativeQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    public int LoginUser(String email, String mdp){
        Session session = sessionFactory.openSession();
        String sql = "SELECT iduser FROM Utilisateur where mail ='"+email+"' and mdp = '"+mdp+"'";
        Query query = session.createSQLQuery(sql);
        int ans = (int) query.uniqueResult();
        return ans;
    }

    public <T> T create(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }
    public<T> List<T> findQuery(Class<T> tClass,String query){
        Session session = sessionFactory.openSession();
        List<T> results = session.createNativeQuery(query,tClass).list();
        session.close();
        return results;
    }

    public <T> T findById(Class<T> clazz, Serializable id) {
        Session session = sessionFactory.openSession();
        T entity = (T) session.get(clazz, id);
        session.close();
        return entity;
    }

    public <T> List<T> findAll(Class<T> tClass) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(tClass).list();
        session.close();
        return results;
    }

    public <T> List<T> findWhere(T entity) {
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass()).add(example).list();
        session.close();
        return results;
    }

    public <T> List<T> recherche(T entity,String mot){
        Session session= null;
        List<T> list = null;
        try{
            session = this.sessionFactory.openSession();
            list = session.createCriteria(entity.getClass())
                    .add(
                            Restrictions.or(
                                    Restrictions.ilike("titre",mot, MatchMode.ANYWHERE),
                                    Restrictions.ilike("contenu",mot,MatchMode.ANYWHERE)
                            )
                    ).list();
        }catch (Exception e){e.printStackTrace();}
        return list;
    }

    public <T> List<T> findField(Class<T> tClass, String field, String value) {
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(tClass);
        crit.add(Restrictions.eq(field, value));
        List<T> results = crit.list();
        return results;
    }

    public <T> List<T> findField(Class<T> tClass, String field, Integer value) {
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(tClass);
        crit.add(Restrictions.eq(field, value));
        List<T> results = crit.list();
        return results;
    }

    public <T> List<T> paginateWhere(T entity, int offset, int size) {
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass())
            .add(example)
            .setFirstResult(offset)
            .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(clazz)
            .setFirstResult(offset)
            .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size, String orderBy, boolean orderAsc) {
        Session session = sessionFactory.openSession();
        Order order = (orderAsc) ? Order.asc(orderBy) : Order.desc(orderBy);
        List<T> results = session.createCriteria(clazz)
            .addOrder(order)
            .setFirstResult(offset)
            .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public void deleteById(Class tClass, Serializable id) {
        delete(findById(tClass, id));
    }

    public void delete(Object entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    public <T> T update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
