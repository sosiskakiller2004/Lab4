package servlets;

import services.MainService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private final MainService mainService;

    public EditServlet() {
        this.mainService = new MainService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("<html><head>");
        out.println("<style>");
        out.println("body {\n" +
                "    background: #172547;\n" +
                "    height: 100vh;\n" +
                "}");
        out.println("body { font-family: Arial; margin: 30px; background-color: #172547; }");
        out.println("h2 { color: white; text-align: center; }");
        out.println("form { background-color: lightgrey; padding: 20px; border-radius: 15px;width: 500px; margin: auto; }");
        out.println("label { display: block; margin-bottom: 10px; }"); // Изменил отступ снизу
        out.println("input[type='text'], input[type='number'] { width: 100%; padding: 15px; margin-bottom: 20px; border-radius: 10px; border: 1px solid #ddd; }"); // Изменил отступ, отступ снизу, радиус границы
        out.println("input[type='submit'] { background-color: #0B0C10; color: white; padding: 15px 25px; border: none; cursor: pointer; border-radius: 10px; }"); // Изменил цвет фона, отступ и радиус границы
        out.println("input[type='submit']:hover { background-color: #1F2833; }");
        out.println("</style>");
        out.println("</head><body>");
        out.print("<h2>Редактирование</h2>");
        out.print("<form method='post' action='/ikts_21_app_war/edit'>");
        out.print("<input type='hidden' name='ProductID' value='" + req.getParameter("ProductID") + "'>");
        out.print("<label for='ProductName'>Product Name:</label>");
        out.print("<input type='text' id='ProductName' name='ProductName' value='" + req.getParameter("ProductName") + "'>");
        out.print("<label for='Quantity'>Quantity:</label>");
        out.print("<input type='number' id='Quantity' name='Quantity' value='" + req.getParameter("Quantity") + "'>");
        out.print("<label for='Price'>Price:</label>");
        out.print("<input type='number' step='0.01' id='Price' name='Price' value='" + req.getParameter("Price") + "'>");
        out.print("<label for='Supplier'>Supplier:</label>");
        out.print("<input type='text' id='Supplier' name='Supplier' value='" + req.getParameter("Supplier") + "'>");
        out.print("<label for='DateReceived'>Date Received:</label>");
        out.print("<input type='text' id='DateReceived' name='DateReceived' value='" + req.getParameter("DateReceived") + "'>");
        out.print("<input type='submit' value='Сохранить изменения'>");

        out.print("</form>");

        out.println("</body></html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ProductID = Integer.parseInt(req.getParameter("ProductID"));
        String ProductName = req.getParameter("ProductName");
        int Quantity = Integer.parseInt(req.getParameter("Quantity"));
        BigDecimal Price = new BigDecimal(req.getParameter("Price"));
        String Supplier = req.getParameter("Supplier");
        Timestamp DateReceived = Timestamp.valueOf(req.getParameter("DateReceived"));
        mainService.editWarehouse(ProductID, ProductName, Quantity, Price, Supplier, DateReceived);

        resp.sendRedirect("/ikts_21_app_war/test");
    }
}
