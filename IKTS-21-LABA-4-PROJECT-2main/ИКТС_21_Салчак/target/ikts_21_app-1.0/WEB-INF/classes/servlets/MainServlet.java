package servlets;
import services.MainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;



@WebServlet("/test")
public class MainServlet extends HttpServlet{

    private final MainService mainService;

    public MainServlet(){
        this.mainService = new MainService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF8"), true);
        out.println("<html><body>");
        out.print("<table border=\"1px\">");
        out.print("<tr>");
        out.print("<th>ProductID</th>");
        out.print("<th>ProductName</th>");
        out.print("<th>Quantity</th>");
        out.print("<th>Price</th>");
        out.print("<th>Supplier</th>");
        out.print("<th>DateReceived</th>");
        out.print("<th>Delete</th>");
        out.print("<th>Edit</th>");
        out.print("</tr>");

        try {
            for(Map<String, Object> row: mainService.getNames()){
                out.println("<style>");
                out.println("td {\n" +
                        "  padding: 2px;\n" +
                        "  background-color: lightgrey;\n" +
                        "}");
                out.println("tr {\n" +
                        "  padding: 2px;\n" +
                        "  background-color: lightgrey;\n" +
                        "}");
                out.println("table {\n" +
                        "  width: 100%;\n" +
                        "  border-collapse: collapse;\n" +
                        "  border: 3px solid black;\n" +
                        "}");
                out.println("form { background-color: lightgrey; padding: 20px; border-radius: 15px;width: 500px; margin: 40px auto; }");

                out.println("body { font-family: Arial; margin: 30px; background-color: #172547; }");

                out.println("input[type='submit'] { background-color: #007BFF; color: white; padding: 10px 20px; border: none; cursor: pointer; border-radius: 5px; }");
                out.println("input[type='submit']:hover { background-color: #0056b3; }");
                out.println("</style>");
                out.print("<tr>");
                out.print("<td>"+row.get("ProductID")+"</td>");
                out.print("<td>"+row.get("ProductName")+"</td>");
                out.print("<td>"+row.get("Quantity")+"</td>");
                out.print("<td>"+row.get("Price")+"</td>");
                out.print("<td>"+row.get("Supplier")+"</td>");
                out.print("<td>"+row.get("DateReceived")+"</td>");
                out.print("<td>");
                out.print("<a href='delete?id="+row.get("ProductID")+"'style='color: red;'>Удалить</a>");
                out.print("</td>");
                out.print("<td>");
                out.print("<a href='edit?ProductID="+row.get("ProductID")+"&ProductName="+row.get("ProductName")+"&Quantity="+row.get("Quantity")+"&Price="+row.get("Price")+"&Supplier="+row.get("Supplier")+"&DateReceived="+row.get("DateReceived")+"'style='color: purple;'>Изменить</a>");
                System.out.println(row.get("ProductID"));
                out.print("</td>");
                out.print("</tr>");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        out.print("</table>");
        out.print("<form method='post'>");
        out.print("<p>Введите ProductID111</p>");
        out.print("<input name='ProductID' type='text' />");
        out.print("<p>Введите ProductName</p>");
        out.print("<input name='ProductName' type='text' />");
        out.print("<p>Введите Quantity</p>");
        out.print("<input name='Quantity' type='text' />");
        out.print("<p>Введите Price</p>");
        out.print("<input name='Price' type='text' />");
        out.print("<p>Введите Supplier</p>");
        out.print("<input name='Supplier' type='text' />");
        out.print("<p>Введите DateReceived</p>");
        out.print("<input name='DateReceived' type='text' />");
        out.print("<p></p>");
        out.print("<input type='submit' value='Отправить'>");

        out.print("</form>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ProductID = Integer.parseInt(req.getParameter("ProductID"));
        String ProductName = req.getParameter("ProductName");
        int Quantity = Integer.parseInt(req.getParameter("Quantity"));
        int Price = Integer.parseInt(req.getParameter("Price"));
        String Supplier = req.getParameter("Supplier");
//        Timestamp DateReceived = Timestamp.valueOf(req.getParameter("DateReceived"));

        String DateReceived = req.getParameter("DateReceived");
        mainService.addWarehouse(ProductID, ProductName, Quantity, Price, Supplier, DateReceived);
        resp.sendRedirect("/ikts_21_app_war/test");
    }
}
