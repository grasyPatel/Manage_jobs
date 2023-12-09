package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.JobDAO;
import com.db.DBConnect;
import com.entity.user;

@WebServlet("/register")
public class user_data extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		try {
			PrintWriter out=resp.getWriter();
			
			String name=req.getParameter("name");
			String Qualification =req.getParameter("qua");
			String email=req.getParameter("email");
			String password=req.getParameter("ps");
			HttpSession session =req.getSession();
			user u=new user();
			u.setName(name);
			u.setEmail(email);
			u.setPassword(password);
			u.setQualification(Qualification);
			JobDAO dao= new JobDAO(DBConnect.getConn());
			boolean f= dao.addUser(u);
			if(f) {
				out.print("<h1>Succesfull</h1>");
//				session.setAttribute("succMsg","Job Post Successfully.....");
//				resp.sendRedirect("add_job.jsp");
			}else {
				out.print("<h1>could not insert</h1>");
//				session.setAttribute("succMsg","Something wrong on server...!");
//				resp.sendRedirect("add_job.jsp");
				
				
				
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
