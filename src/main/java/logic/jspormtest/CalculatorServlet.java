package logic.jspormtest;

import java.io.*;

import entities.Calculatorhistory;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "calculator", urlPatterns = {"", "/"})
public class CalculatorServlet extends HttpServlet {

    public void init(){}
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String capital = request.getParameter("capital");
        String interes = request.getParameter("interes");
        String anios = request.getParameter("anios");
        String periodos = request.getParameter("periodos");
        String history = "";
        String error;

        if( capital.isBlank() || interes.isBlank() || anios.isBlank()|| periodos.isBlank() ){
            error = "Por favor llene todas las casillas";

            request.setAttribute("error", error);
        }else{
            double result = Utils.calculateCompoundInterest(
                    Double.parseDouble(capital),
                    Double.parseDouble(interes)/100,
                    Double.parseDouble(anios),
                    Double.parseDouble(periodos));
            request.setAttribute("result", result);
            try {
                DBConnection.transaction.begin();
                Calculatorhistory newHistoryRecord = new Calculatorhistory();
                newHistoryRecord.setResult(result);
                DBConnection.entityManager.persist(newHistoryRecord);
                TypedQuery<Calculatorhistory> allHistoryQuery =  DBConnection.entityManager.createNamedQuery("History.allResults", Calculatorhistory.class);
                for (Calculatorhistory record: allHistoryQuery.getResultList()) {
                    history += record.getResult() + "<br>";
                }
                System.out.println(history);
                DBConnection.transaction.commit();
            } finally {
                if (DBConnection.transaction.isActive()) {
                    DBConnection.transaction.rollback();
                }
            }
        }

        request.setAttribute("capital", capital);
        request.setAttribute("interes", interes);
        request.setAttribute("anios", anios);
        request.setAttribute("periodos", periodos);
        request.setAttribute("history", history);

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}