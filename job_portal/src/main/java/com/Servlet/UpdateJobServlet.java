package com.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.JobDAO;
import com.db.DBConnect;
import com.entity.jobs;


@WebServlet("/Update")
public class UpdateJobServlet  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			 
			int id=Integer.parseInt(req.getParameter("id"));
			String title=req.getParameter("title");
			String location = req.getParameter("location");
			String category =req.getParameter("category");
			String status=req.getParameter("status");
			String desc=req.getParameter("desc");
			
			jobs j=new jobs();
			j.setId(id);
			j.setTitle(title);
			j.setDescription(desc);
			j.setLocation(location);
			j.setCategory(category);
			j.setStatus(status);
			
			HttpSession session =req.getSession();
			JobDAO dao= new JobDAO(DBConnect.getConn());
			boolean f= dao.updateJob(j);
			if(f) {
				session.setAttribute("succMsg","Job updated Successfully.....");
				resp.sendRedirect("view_jobs.jsp");
			}else {
				session.setAttribute("succMsg","Something wrong on server...!");
				resp.sendRedirect("view_jobs.jsp");
				
			}
				
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
