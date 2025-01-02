import DBClass.Order;
import DBClass.Product;
import JDBCUtils.utils2;
import org.junit.Test;

import java.time.LocalDate;



public class DBtest {
    //插入产品
    @Test
    public void test1(){
        Product product1=new Product(1,"测试1",114.1);
        utils2.productInsert(product1);
        Product product2=new Product(2,"测试2",135.9);
        utils2.productInsert(product2);
    }

    //插入订单
    @Test
    public void test2(){
        Order order1=new Order(1,1, LocalDate.of(2025,1,2),114.1);
        utils2.orderInsert(order1);
        Order order2=new Order(2,1, LocalDate.of(2024,1,2),114.1);
        utils2.orderInsert(order2);
    }

    //删除产品
    @Test
   public  void test3(){
       utils2.productDelete(1);
   }

    //删除订单
    @Test
    public void test4(){
        utils2.orderDelete(2);
    }


    //查找产品(id)
    @Test
    public void test5(){
        utils2.productSelect(1);
    }

    //查找产品(所有)
    @Test
    public void test6(){
        utils2.productSelect();
    }

    //查找订单(id)
    @Test
    public  void test7(){
        utils2.orderSelect(1);
    }

    //查找所有订单(默认id排序)
    @Test
    public  void test8(){
        utils2.orderSelect(" ");
    }

    //查找所有订单(按下单时间升序)
    @Test
    public  void test9(){
        utils2.orderSelect("按下单时间升序");
    }

    //查找所有订单(按下单时间降序)
    @Test
    public  void test10(){
        utils2.orderSelect("按下单时间降序");
    }

    //查找所有订单(按价格升序)
    @Test
    public  void test11(){
        utils2.orderSelect("按价格升序");
    }

    //查找所有订单(按价格降序)
    @Test
    public  void test12(){
        utils2.orderSelect("按价格降序");
    }

    //修改产品
    @Test
    public  void test13(){
        Product product1=new Product(1,"测试1-2",115.1);
        utils2.productUpdate(product1);
    }


    //修改订单
    @Test
    public  void test14(){
        Order order1=new Order(1,2, LocalDate.of(2025,1,2),100.1);
        utils2.orderUpdate(order1);
    }






}
