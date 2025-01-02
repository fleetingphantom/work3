import java.util.Scanner;
import JDBCUtils.utils2;
public class DB {
    public static void main(String[] args) {
        while (true){
            String option;
            Scanner scanner=new Scanner(System.in);
            System.out.println("====================");
            System.out.println("A.向产品表中加入新产品  ");
            System.out.println("B.按ID在产品表中删除产品");
            System.out.println("C.按ID在产品表中查找产品");
            System.out.println("D.展示所有产品         ");
            System.out.println("E.按ID在产品表中改产品  ");

            System.out.println("F.向订单表中加入新订单  ");
            System.out.println("G.按ID在订单表中删除订单");
            System.out.println("H.按ID在订单表中查找订单");
            System.out.println("I.展示所有订单         ");
            System.out.println("J.按ID在订单表中改订单  ");

            System.out.println("其它:退出             ");
            System.out.println("====================");
            System.out.print("请输入:");
            option=scanner.nextLine();
            if(option.equals("A")){
                utils2.productCreate();
            }
            else if(option.equals("B")){
                utils2.productDelete();
            }
            else if(option.equals("C")){
                utils2.productSelectU();
            }
            else if(option.equals("D")){
                utils2.productSelectAll();
            }
            else if(option.equals("E")){
                utils2.UpdateProduct();
            }
            else if(option.equals("F")){
                utils2.orderCreate();
            }
            else if(option.equals("G")){
                utils2.orderDelete();
            }
            else if(option.equals("H")){
                utils2.orderSelectU();
            }
            else if(option.equals("I")){
                utils2.orderSelectAll();
            }
            else if(option.equals("J")){
                utils2.orderUpdate();
            }
            else {
                break;
            }

        }
    }
}
