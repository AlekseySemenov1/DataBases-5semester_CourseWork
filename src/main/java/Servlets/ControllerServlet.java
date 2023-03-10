package Servlets;

import DataBase.DataBaseManager;
import Entities.Npc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet", urlPatterns = {"/*.html", "/*.xhtml"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int query_type = Integer.parseInt(request.getParameter("r"));
            System.out.println("Request number: " + query_type);
            switch (query_type){
                case 0: {
                    request.getServletContext().getRequestDispatcher("/inventoryManagerServlet").forward(request, response);
                    break;
                }
                case 1: {
                    request.getServletContext().getRequestDispatcher("/catalogManagerServlet").forward(request,response);
                    break;
                }
                case 2:
                case 6:
                case 8:{
                    request.getServletContext().getRequestDispatcher("/tradeListManagerServlet").forward(request,response);
                    break;
                }
                case 3:
                case 7:
                case 9: {
                    request.getServletContext().getRequestDispatcher("/basketManagerServlet").forward(request, response);
                    break;
                }
                case 4:
                case 18:{
                    request.getServletContext().getRequestDispatcher("/orderListManagerServlet").forward(request, response);
                    break;
                }
                case 5: {
                    request.getServletContext().getRequestDispatcher("/itemInfoServlet").forward(request,response);
                    break;
                }
                case 10:
                case 17:{
                    request.getServletContext().getRequestDispatcher("/enterManagerServlet").forward(request,response);
                    break;
                }
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16: {
                    request.getServletContext().getRequestDispatcher("/tradeManagerServlet").forward(request, response);
                    break;
                }
            }
        } catch (NullPointerException | NumberFormatException e){
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
