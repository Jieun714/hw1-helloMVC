package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

/**
 * Servlet implementation class DoRegister
 */
@WebServlet("/doRegister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		CustomerService service = (CustomerService) CustomerService.getInstance();
		
		//Customer customer =service.register(id,password, name, gender, email);
		
		//Customer 생성자
		Customer customer = new Customer(id, password, name, gender, email);
		//service 패키지 - CustomerService -> addCustomer 함수
		service.addCustomer(customer);
	
		String page = null;
		
		//아이디가 null이거나 비번이 null이면 error 페이지로 가도록
		if(id.equals("") || password.equals("")) {
			page ="/view/error.jsp";
		}
		else {
			page ="/view/registerSuccess.jsp";
			//customer으로부터 받아와서
			request.setAttribute("customer", customer);
		}
	
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
