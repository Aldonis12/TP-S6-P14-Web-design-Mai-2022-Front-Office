package miniprojet.miniprojet.Model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import miniprojet.miniprojet.DAO.HibernateDAO;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("Conf.xml");
        HibernateDAO db = context.getBean(HibernateDAO.class);
        Timestamp temps = Timestamp.valueOf("2023-05-08 00:02:14.384054");
        String dateStr = String.valueOf(temps);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS", Locale.FRENCH);
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy à HH:mm:ss", Locale.FRENCH));
        System.out.println(formattedDate);
    }
}
