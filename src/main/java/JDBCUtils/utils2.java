package JDBCUtils;

import DBClass.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;


public class utils2 {
    //---------------------产品表操作---------------------------
    //增加
    public static void productInsert(Product product) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into `products`(`product_id`,`product_name`,`product_price`) values(?,?,?)";

            st = conn.prepareStatement(sql);

            st.setInt(1, product.getProductId());//id
            st.setString(2, product.getProductName());//名字
            st.setDouble(3, product.getProductPrice());//价格

            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("插入成功");
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, null);
        }
    }

    //删除
    public static void productDelete(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);
            //要把订单里面的一起删掉
            String sql = "delete from products where product_id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

            sql = "delete from orders where product_id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

            conn.commit();
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, null);
        }
    }

    //查询产品(用id)
    public static void productSelect(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);

            String sql = "select `product_id`,`product_name`,`product_price` from products where product_id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            System.out.println("====================");
            while (rs.next()) {
                System.out.println("产品编号:" + rs.getInt("product_id"));
                System.out.println("产品名字:" + rs.getString("product_name"));
                System.out.println("产品价格:" + rs.getDouble("product_price"));
                System.out.println("====================");
            }

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, null);
        }
    }

    //查询产品(所有)
    public static void productSelect() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);

            String sql = "select `product_id`,`product_name`,`product_price` from products";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            System.out.println("====================");
            while (rs.next()) {
                System.out.println("产品编号:" + rs.getInt("product_id"));
                System.out.println("产品名字:" + rs.getString("product_name"));
                System.out.println("产品价格:" + rs.getDouble("product_price"));
                System.out.println("====================");
            }

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, rs);
        }
    }

    //查询产品(用于判断主键是否唯一)
    public static boolean productSelectDistinct(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean flag=true;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);

            String sql = "select `product_id` from `products`";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()){
                int tmp=rs.getInt("product_id");
                if(tmp==id){
                    flag=false;
                    break;
                }
            }

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, rs);
        }
        return flag;
    }


    //更改产品
    public static void productUpdate(Product product) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);

            String sql = "update `products` set " +
                    "`product_name`=?,`product_price`=?" +
                    "where `product_id`=?";
            st = conn.prepareStatement(sql);
            st.setString(1, product.getProductName());
            st.setDouble(2, product.getProductPrice());
            st.setInt(3, product.getProductId());
            st.executeUpdate();
            conn.commit();
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, null);
        }
    }


    //---------------------订单表操作---------------------------
    //增加
    public static void orderInsert(Order order) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into `orders`(`order_id`,`product_id`,`order_time`,`order_price`) values(?,?,?,?)";

            st = conn.prepareStatement(sql);

            st.setInt(1, order.getOrderId());//id
            st.setInt(2, order.getProductId());//对应产品id
            st.setDate(3, Date.valueOf(order.getOrderTime()));//对应产品id
            st.setDouble(4, order.getOrderPrice());//价格

            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("插入成功");
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, null);
        }
    }

    //删除
    public static void orderDelete(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from orders where order_id=?";

            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功");
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, null);
        }
    }

    //查询订单(用id)
    public static void orderSelect(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);

            String sql = "select `order_id`,o.`product_id`,`product_name`,`product_price`,`order_time`,`order_price`" +
                    "from orders as o " +
                    "left join products as p " +
                    "on o.`product_id`=p.`product_id`" +
                    "where order_id =?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            System.out.println("====================");
            while (rs.next()) {
                System.out.println("订单编号:" + rs.getInt("order_id"));
                System.out.println("产品编号:" + rs.getInt("product_id"));
                System.out.println("产品名字:" + rs.getString("product_name"));
                System.out.println("产品价格:" + rs.getDouble("product_price"));
                System.out.println("下单时间:" + rs.getDate("order_time"));
                System.out.println("订单价格:" + rs.getDouble("order_price"));
                System.out.println("====================");
            }

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, rs);
        }
    }

    //查询订单(所有)
    public static void orderSelect(String str) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);
            String sql;
            if (str.equals("按下单时间升序")) {
                sql = "select `order_id`,o.`product_id`,`product_name`,`product_price`,`order_time`,`order_price`" +
                        "from orders as o " +
                        "left join products as p " +
                        "on o.`product_id`=p.`product_id`" +
                        "order by order_time ASC";

            } else if (str.equals("按下单时间降序")) {
                sql = "select `order_id`,o.`product_id`,`product_name`,`product_price`,`order_time`,`order_price`" +
                        "from orders as o " +
                        "left join products as p " +
                        "on o.`product_id`=p.`product_id`" +
                        "order by order_time DESC";
            } else if (str.equals("按价格升序")) {
                sql = "select `order_id`,o.`product_id`,`product_name`,`product_price`,`order_time`,`order_price`" +
                        "from orders as o " +
                        "left join products as p " +
                        "on o.`product_id`=p.`product_id`" +
                        "order by order_price ASC";
            } else if (str.equals("按价格降序")) {
                sql = "select `order_id`,o.`product_id`,`product_name`,`product_price`,`order_time`,`order_price`" +
                        "from orders as o " +
                        "left join products as p " +
                        "on o.`product_id`=p.`product_id`" +
                        "order by order_price DESC";
            } else {
                sql = "select `order_id`,o.`product_id`,`product_name`,`product_price`,`order_time`,`order_price`" +
                        "from orders as o " +
                        "left join products as p " +
                        "on o.`product_id`=p.`product_id`" +
                        "order by order_id";
            }


            st = conn.prepareStatement(sql);

            rs = st.executeQuery();
            System.out.println("====================");
            while (rs.next()) {
                System.out.println("订单编号:" + rs.getInt("order_id"));
                System.out.println("产品编号:" + rs.getInt("product_id"));
                System.out.println("产品名字:" + rs.getString("product_name"));
                System.out.println("产品价格:" + rs.getDouble("product_price"));
                System.out.println("下单时间:" + rs.getDate("order_time"));
                System.out.println("订单价格:" + rs.getDouble("order_price"));
                System.out.println("====================");
            }

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, rs);
        }
    }

    //查询订单(用于判断主键是否唯一)
    public static boolean orderSelectDistinct(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean flag=true;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);

            String sql = "select `order_id`"+
                    "from orders";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()){
                if(id==rs.getInt("order_id")){
                    flag=false;
                }
            }

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, rs);
        }
        return flag;
    }

    //更改订单
    public static void orderUpdate(Order order) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = utils1.getConnection();
            conn.setAutoCommit(false);

            String sql = "update `orders` set " +
                    "`product_id`=?,`order_time`=?,`order_price`=?" +
                    "where `order_id`=?";

            st = conn.prepareStatement(sql);
            st.setInt(1,order.getProductId());
            st.setDate(2, Date.valueOf(order.getOrderTime()));
            st.setDouble(3, order.getOrderPrice());
            st.setInt(4, order.getOrderId());

            st.executeUpdate();
            conn.commit();
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            utils1.release(conn, st, null);
        }
    }

    //---------------------用户函数---------------------------
    //增加产品
    public static void productCreate(){
        int product_id;
        String product_name;
        Double product_price;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入要创建的产品编号:");
        product_id=scanner.nextInt();
        scanner.nextLine();
        if(!(productSelectDistinct(product_id))||product_id<=0){
            System.out.println("输入编号非法,创建产品失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }
        System.out.print("请输入产品名字:");
        product_name=scanner.nextLine();
        System.out.print("请输入产品价格:");
        product_price=scanner.nextDouble();
        scanner.nextLine();
        if(product_price<0){
            System.out.println("输入价格非法,创建产品失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        Product product=new Product(product_id,product_name,product_price);
        productInsert(product);
        System.out.print("输入任意键继续");
        scanner.nextLine();
    }

    //删除产品
    public static void productDelete(){
        int id;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入要删除的产品编号:");
        id=scanner.nextInt();
        productDelete(id);
        System.out.print("输入任意键继续");
        scanner.nextLine();
    }

    //查找产品(id)
    public static void productSelectU(){
        int id;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入要查找的产品编号:");
        id=scanner.nextInt();
        scanner.nextLine();
        productSelect(id);
        System.out.print("输入任意键继续");
        scanner.nextLine();
    }

    //查找产品(全部)
    public static void productSelectAll(){
        Scanner scanner=new Scanner(System.in);
        productSelect();
        System.out.print("输入任意键继续");
        scanner.nextLine();
    }

    //改产品
    public static void UpdateProduct(){
        int product_id;
        String product_name;
        Double product_price;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入要改的产品编号:");
        product_id=scanner.nextInt();
        scanner.nextLine();
        if(productSelectDistinct(product_id)){
            System.out.println("输入编号不存在,改产品失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }
        System.out.print("请输入产品名字:");
        product_name=scanner.nextLine();
        System.out.print("请输入产品价格:");
        product_price=scanner.nextDouble();
        scanner.nextLine();
        if(product_price<0){
            System.out.println("输入价格非法,改产品失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        Product product=new Product(product_id,product_name,product_price);
        productUpdate(product);
        System.out.print("输入任意键继续");
        scanner.nextLine();
    }

    //增加订单
    public static void orderCreate(){
        int product_id;
        int order_id;
        int year;
        int month;
        int day;
        int[] days1={31,29,31,30,31,30,31,31,30,31,30,31};
        int[] days2={31,28,31,30,31,30,31,31,30,31,30,31};
        LocalDate order_time;
        Double order_price;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入订单编号:");
        order_id=scanner.nextInt();
        scanner.nextLine();
        if(!(productSelectDistinct(order_id))||order_id<=0){
            System.out.println("输入订单编号非法,创建订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        System.out.print("请输入产品编号:");
        product_id=scanner.nextInt();
        scanner.nextLine();
        if(productSelectDistinct(product_id)){
            System.out.println("产品不存在,创建订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        System.out.print("请输入下单年:");
        year=scanner.nextInt();
        if(year<0){
            System.out.println("输入年非法,创建订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        System.out.print("请输入下单月:");
        month=scanner.nextInt();
        if(month<=0||month>=12){
            System.out.println("输入月非法,创建订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        System.out.print("请输入下单日:");
        day=scanner.nextInt();
        //闰年
        if(year%400==0||(year%4==0&&year%100!=0)){
            if(day<=0||day>=days1[month-1]){
                System.out.println("输入日非法,创建订单失败");
                System.out.print("输入任意键继续");
                scanner.nextLine();
                return;
            }
        }
        else{
            if(day<=0||day>=days2[month-1]){
                System.out.println("输入日非法,创建订单失败");
                System.out.print("输入任意键继续");
                scanner.nextLine();
                return;
            }
        }

        System.out.print("请输入订单价格:");
        order_price=scanner.nextDouble();
        scanner.nextLine();
        if(order_price<0){
            System.out.println("输入价格非法,创建订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        Order order=new Order(order_id,product_id, LocalDate.of(year,month,day),order_price);
        orderInsert(order);
        System.out.print("输入任意键继续");
        scanner.nextLine();
    }

    //删除订单
    public static void orderDelete(){
        int id;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入要删除的订单编号:");
        id=scanner.nextInt();
        orderDelete(id);
        System.out.print("输入任意键继续");
        scanner.nextLine();
    }

    //查找订单(id)
    public static void orderSelectU(){
        int id;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入要查找的订单编号:");
        id=scanner.nextInt();
        scanner.nextLine();
        orderSelect(id);
        System.out.print("输入任意键继续");
        scanner.nextLine();
    }

    //查找订单(全部)
    public static void orderSelectAll(){
        int id;
        Scanner scanner=new Scanner(System.in);
        String[] options={" ","按下单时间升序","按下单时间降序","按价格升序","按价格降序"};
        System.out.println("0.默认排序     ");
        System.out.println("1.按下单时间升序");
        System.out.println("2.按下单时间降序");
        System.out.println("3.按价格升序   ");
        System.out.println("4.按价格降序   ");
        System.out.print("输入排序方式");
        int option=scanner.nextInt();
        if(option<=4&&option>=0){
            orderSelect(options[option]);
        }
        else {
            orderSelect(options[0]);
        }
        System.out.print("输入任意键继续");
        scanner.nextLine();
        scanner.nextLine();
    }

    //改订单
    public static void orderUpdate(){
        int product_id;
        int order_id;
        int year;
        int month;
        int day;
        int[] days1={31,29,31,30,31,30,31,31,30,31,30,31};
        int[] days2={31,28,31,30,31,30,31,31,30,31,30,31};
        LocalDate order_time;
        Double order_price;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入订单编号:");
        order_id=scanner.nextInt();
        scanner.nextLine();
        if(productSelectDistinct(order_id)){
            System.out.println("输入订单编号非法,改订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        System.out.print("请输入产品编号:");
        product_id=scanner.nextInt();
        scanner.nextLine();
        if(productSelectDistinct(product_id)){
            System.out.println("产品不存在,改订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        System.out.print("请输入下单年:");
        year=scanner.nextInt();
        if(year<0){
            System.out.println("输入年非法,改订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        System.out.print("请输入下单月:");
        month=scanner.nextInt();
        if(month<=0||month>=12){
            System.out.println("输入月非法,改订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        System.out.print("请输入下单日:");
        day=scanner.nextInt();
        //闰年
        if(year%400==0||(year%4==0&&year%100!=0)){
            if(day<=0||day>=days1[month-1]){
                System.out.println("输入日非法,改订单失败");
                System.out.print("输入任意键继续");
                scanner.nextLine();
                return;
            }
        }
        else{
            if(day<=0||day>=days2[month-1]){
                System.out.println("输入日非法,改订单失败");
                System.out.print("输入任意键继续");
                scanner.nextLine();
                return;
            }
        }

        System.out.print("请输入订单价格:");
        order_price=scanner.nextDouble();
        scanner.nextLine();
        if(order_price<0){
            System.out.println("输入价格非法,改订单失败");
            System.out.print("输入任意键继续");
            scanner.nextLine();
            return;
        }

        Order order=new Order(order_id,product_id, LocalDate.of(year,month,day),order_price);
        orderUpdate(order);
        System.out.print("输入任意键继续");
        scanner.nextLine();
    }

}
