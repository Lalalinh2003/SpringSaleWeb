/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ptpl.repository.impl;

import com.ptpl.hibernatedemo.HibernateUtils;
import com.ptpl.pojo.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.function.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
//import org.hibernate.mapping.List;
import org.hibernate.query.Query;

/**
 *
 * @author Linh
 */
public class ProductRepositoryImpl {

    public List<Product> getProducts(Map<String, String> params) {

        try (Session session = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            Root root = q.from(Product.class);
            q.select(root);
            Query query = session.createQuery(q);
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add((Predicate) b.like(root.get("name"), String.format("%%%s%%", kw)));
            }
 
            String fromPrice = params.get("fromPrice");
            if (fromPrice != null & !fromPrice.isEmpty()) {
                predicates.add((Predicate) b.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice)));
            }
            
            String toPrice = params.get("toPrice");
            if (toPrice != null & !toPrice.isEmpty()) {
                predicates.add((Predicate) b.lessThanOrEqualTo(root.get("price"), Double.parseDouble(toPrice)));
            }
            
            String cateId = params.get("cateId");
            if (cateId != null & !cateId.isEmpty()) {
                predicates.add((Predicate) b.equal(root.get("category").as(Integer.class), Integer.parseInt(cateId)));
            }
            
            q.where(predicates.toArray(Predicate[]::new));
            
            q.orderBy(b.desc(root.get("id")));
            List<Product> products = query.getResultList();

            return products;
        }

    }
    
    public Product getProductById(int id) {
        try(Session session = HibernateUtils.getFactory().openSession()) {
            return session.get(Product.class, id);
        } 
    }
    
    public void addOrUpdateProduct(Product p) {
        try (Session session = HibernateUtils.getFactory().openSession()) {
            session.persist(p);
        }
    }

}
