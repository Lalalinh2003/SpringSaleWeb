/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ptpl.hibernatedemo;

//import com.mysql.cj.xdevapi.SessionFactory;
import com.ptpl.pojo.Category;
import com.ptpl.pojo.Comment;
import com.ptpl.pojo.OrderDetail;
import com.ptpl.pojo.ProdTag;
import com.ptpl.pojo.Product;
import com.ptpl.pojo.SaleOrder;
import com.ptpl.pojo.Tag;
import com.ptpl.pojo.User;
import java.util.Properties;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Linh
 */
public class HibernateUtils {
    private static final SessionFactory factory;
    
    
    static {
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        /*
        Properties props = new Properties();
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost/saledb");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "Linh113@");
        props.put(Environment.SHOW_SQL, "true");
        conf.setProperties(props);
        */
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(Tag.class);
        conf.addAnnotatedClass(SaleOrder.class);
        conf.addAnnotatedClass(OrderDetail.class);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Comment.class);
        conf.addAnnotatedClass(ProdTag.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        factory = (SessionFactory) conf.buildSessionFactory(registry);
    }
    
    public static SessionFactory getFactory() {
        return factory;
    }
    
    /**
     *
     * @return
     */
    public static SessionFactory setFactory() {
        return factory;
    }
    

}
