package fullwordsservlets;

import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullauth.api.model.oauth.OauthAccessToken;
import com.fullauth.api.service.FullAuthOauthService;
import com.google.appengine.api.utils.SystemProperty;

import allconstants.FullWordsConstants.FullConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
@WebServlet(name = "AuthCallBack", urlPatterns = {"/authCallback"})

public class AuthCallBack extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pr = res.getWriter();
		String accessToken = "";

		try {
			HttpSession session = req.getSession(false);
			String redirectUrl = (String) session.getAttribute("Redirect");
			
			//String redirectUrl="http://localhost:8080/authCallback";
			String code = req.getParameter("code");
			System.out.println(code);

			FullAuthOauthService authService1 = FullAuthOauthService.builder().authDomain("fullcreative")
					.clientId(FullConstants.CLIENT_ID.getValue())
					.clientSecret(FullConstants.CLIENT_SECRET.getValue())
					.redirectUri(redirectUrl).build();

			OauthAccessToken token = authService1.requestAccessTokenForCode(code);

			accessToken = token.getAccessToken();

			System.out.println(accessToken);

			URL url = new URL(FullConstants.AW_API.getValue());
			URLConnection urlConn = url.openConnection();
			urlConn.setRequestProperty("Authorization", "Bearer " + accessToken);
			String outputString = "", line = "";

			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}

			System.out.println(outputString);

			// outputString=outputString.toString();
			JSONObject jsonObj = new JSONObject(outputString);

			JSONObject jsonObj1 = jsonObj.getJSONObject("data");
			JSONObject jsonObj2 = jsonObj1.getJSONObject("user");
			// System.out.println(jsonObj2);
			String mail = jsonObj2.getString("login");
			String firstName = jsonObj2.getString("firstName");
			String lastName = jsonObj2.getString("lastName");
			String photoId = jsonObj2.getString("photoId");
			String name = firstName + " " + lastName;

			String info = mail + "," + name + "," + photoId;
			System.out.println(info);

			
			session.setAttribute("userinfo", info);
			session.setAttribute("AccessToken", accessToken);
			res.sendRedirect("Profile3.jsp");

		}

		catch (Exception e) {
			pr.println("Invalid Input");
			e.printStackTrace();
		}
	}
}