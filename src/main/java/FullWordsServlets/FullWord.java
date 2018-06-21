package FullWordsServlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import allConstants.FullWordsConstants.FullConstants;

@WebServlet(name = "FullWord", urlPatterns = { "/FullWord" })

public class FullWord extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		PrintWriter pr = res.getWriter();
		
		try {
			HttpSession session = req.getSession(false);
			String accessToken = (String) session.getAttribute("AccessToken");

			String word = req.getParameter("word2");
			String desc = req.getParameter("meaning2");
			String src = req.getParameter("source2");

			Map<String, Object> reqData = new HashMap<>();
			reqData.put("word", word);
			reqData.put("src", src);
			reqData.put("desc", desc);
			
			System.out.println(reqData);

			String reqJsonStr = new ObjectMapper().writeValueAsString(reqData);

			URL url = new URL(FullConstants.FULL_WORDS_API.getValue());

			HttpURLConnection urlConn =(HttpURLConnection) url.openConnection();
			if(!word.equals("undefined") && !desc.equals("undefined") && !src.equals("undefined"))
			{
			urlConn.setRequestMethod("POST");
			urlConn.setDoOutput(true);
			urlConn.setRequestProperty("Content-Type", "application/json");
			urlConn.setRequestProperty("Authorization", "Bearer " + accessToken);

			OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());

			wr.write(reqJsonStr);
			wr.flush();
			wr.close();

			System.out.println("data sent");

			String outputString1 = "", line1 = "";

			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			System.out.println("Data received");

			while ((line1 = reader.readLine()) != null) {
				outputString1 += line1;
			}

			System.out.println(outputString1);
			}
			/*pr.println(result);*/
			if(urlConn.getResponseCode()==200)
			{   
				/*HttpSession session1=req.getSession(false);
		        session1.setAttribute("result",result);
				res.sendRedirect("Profile4.jsp");*/
				res.setContentType("text/plain");
				//res.setContentType("text/html;charset=UTF-8");
		        res.getWriter().write("Word submitted successfully");
		        
				
			}
			else
			{
				res.setContentType("text/plain");
				res.getWriter().write("Word submission failed");
			}
			
		}
		/*catch(IOException e)
		{
			pr.println("Duplicate Word!");
		}*/
		catch (Exception e) {
			System.out.println("Exception raised");
			e.printStackTrace();
			pr.println("Duplicate word!");
		} finally {
			System.out.println("Finally executed");
		}
	}
}
