package com.ducpham.dao.category;


import com.ducpham.dto.search.CategorySearch;
import com.ducpham.enums.EnumStatus;
import com.ducpham.exception.AppException;
import com.ducpham.exception.EnumException;
import com.ducpham.model.Category;
import com.ducpham.utils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CategoryDAO implements ICategoryDAO {

    @Override
    public Category getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Category.class, id);
        } catch (Exception e) {
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to get student by id");
        }
    }

    @Override
    public List<Category> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Category", Category.class).list();
        } catch (Exception e) {
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to get all students");
        }
    }

    @Override
    public List<Category> getAll(CategorySearch search) {
        return List.of();
    }

    @Transactional
    @Override
    public Category create(Category entity) {
        if (existsByCode(entity.getCode()))
            throw new AppException(EnumException.EXISTED, "Category already exists with code " + entity.getCode());

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to create a new category");
        }
    }

    @Override
    public Category update(Long id, Category entity) {
        if (existsByCodeAndNotId(id, entity.getCode()))
            throw new AppException(EnumException.EXISTED, "Category already exists with code " + entity.getCode());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, id);

            category.setCode(entity.getCode());
            category.setName(entity.getName());

            session.merge(category);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to update category");
        }
        return null;
    }

    @Override
    public String delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, id);
            category.setStatus(EnumStatus.INACTIVE);
            session.merge(category);
            session.getTransaction().commit();

        } catch (Exception e) {
            throw new AppException(EnumException.SQL_EXCEPTION, "Failed to connect to the database or execute query to delete category");
        }
        return "";
    }

    @Override
    public boolean existsByCode(String code) {
        String hql = "select 1 from Category where code = :code";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Integer> typedQuery = session.createQuery(hql, Integer.class);
            typedQuery.setParameter("code", code);

            return typedQuery.getSingleResult() == 1;
        }
    }

    @Override
    public boolean existsByCodeAndNotId(Long id, String code) {
        String hql = "select 1 from Category where code = :code and id <> :id";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Integer> typedQuery = session.createQuery(hql, Integer.class);
            typedQuery.setParameter("code", code);
            typedQuery.setParameter("id", id);

            return typedQuery.getSingleResult() == 1;
        }
    }
}
