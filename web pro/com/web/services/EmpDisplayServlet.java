package com.web.services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.dao.DisplyDao;
import com.web.exep.Myexception;

@WebServlet("/ViewEmp")
public class EmpDisplayServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		FileWriter fw = null;
		System.out.println("json obj");
		DisplyDao dis = new DisplyDao();

		List view;
		try {
			view = dis.display();
			Gson gson = new Gson();
			String toJson = gson.toJson(view);
			System.out.println("json obj" + toJson);
			try {
				fw = new FileWriter("C:\\Users\\M1064615\\OneDrive - Mindtree Limited\\Desktop\\sample.json");
				fw.write(toJson);

			} finally {
				fw.close();
			}
		} catch (Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("/display.html");
		rd.forward(req, res);

	}

}
