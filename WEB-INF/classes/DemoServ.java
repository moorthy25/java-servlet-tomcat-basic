import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoServ extends HttpServlet {
    public static int count = 0;

    // public DemoServ(){
    // super();
    // this.count=0;
    // }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            res.setContentType("text/html");
            PrintWriter a = res.getWriter();
            String name = req.getParameter("name");

            a.println("Hello " + name + "data:\n" + getEmployee());
            count = count + 1;
            System.out.println("message sent s " + (count + 1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getEmployee() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo", "root", "");
            // here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from emp");
            String str = "";
            while (rs.next())
                str += ("\n" + rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
            return str;
        } catch (Exception e) {
            System.out.println(e);
            return "Something went wrong";
        }
    }
}
