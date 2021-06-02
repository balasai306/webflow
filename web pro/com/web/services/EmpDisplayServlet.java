package com.web.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.dao.DisplyDao;
import com.web.exep.Myexception;
import com.web.model.employeeModel;

@WebServlet("/ViewEmp")
public class EmpDisplayServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		FileWriter fw = null;
//		System.out.println("json obj");

		DisplyDao dis = new DisplyDao();
		PrintWriter out = null;
		List<employeeModel> view = null;
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");

		try {
		
			out = res.getWriter();
			view = dis.display();

			Gson gson = new Gson();
			String toJson = gson.toJson(view);
			System.out.println("json obj" + toJson);
			res.setStatus(200);
			

			out.println(toJson);
			out.flush();
//			req.setAttribute("view", toJson);
//			RequestDispatcher rd = req.getRequestDispatcher("/display.html");
//			rd.forward(req, res);
		} catch (Myexception e) {

			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

}
//response.setContentType("application/json");
//response.setCharacterEncoding("UTF-8");
//try {
//	out = response.getWriter();
//	persons = new EntryDaoImpl().getValues();
//	Gson gson = new Gson();
//	personsToJson = gson.toJson(persons);
//	response.setStatus(200);
//	out.println(personsToJson);
//	out.flush();

//try {
//fw = new FileWriter("C:\\Users\\M1064615\\OneDrive - Mindtree Limited\\Desktop\\sample.json");
//fw.write(toJson);
//
//} finally {
//fw.close();
//}
