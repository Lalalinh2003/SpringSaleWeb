/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ptpl.hibernatedemo;

import com.ptpl.pojo.Category;
import com.ptpl.pojo.Product;
import com.ptpl.repository.impl.ProductRepositoryImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Linh
 */
public class HibernateDemo {

    public static void main(String[] args) {
        ProductRepositoryImpl s = new ProductRepositoryImpl();
        
        Map<String, String> params = new HashMap<>();
        params.put("kw", "Galaxy");
        params.put("fromPrice", "18000000");
        params.put("toPrice", "20000000");
        s.getProducts(null).forEach(p -> System.out.println(p.getId() + " - " + p.getName() + " - " + p.getPrice()));
        
        /*
        try (Session s = HibernateUtils.getFactory().openSession()) {
            //Query q = s.createNamedQuery("Product.findAll");
            //List<Product> products = q.getResultList();
            //products.forEach(p -> System.out.println(p.getName()));
            
            Query q = s.createQuery("FROM Category");
            List<Category> cates = q.getResultList();
            
            cates.forEach(c -> System.out.println(c.getName())); 
        
        }*/
        
    }
}
