package FullWordsServlets;
import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullauth.api.enums.OauthApprovalPrompt;
import com.fullauth.api.enums.OauthResponseType;
import com.fullauth.api.service.FullAuthOauthService;
import com.google.appengine.api.utils.SystemProperty;

import allConstants.FullWordsConstants.FullConstants;
@WebServlet(name = "RequestAuthCode", urlPatterns = { "/RequestAuthCode" })

public class RequestAuthCode extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pr = res.getWriter();
		try {
			HttpSession session = req.getSession();
			String redirectUrl="";
			SystemProperty.environment.set( SystemProperty.Environment.Value.Development );
			if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)
			{
				redirectUrl=FullConstants.REDIRECT_PRODUCTION.getValue();
			}
			else
			{
				redirectUrl=FullConstants.REDIRECT_DEVELOPMENT.getValue();
			} 
			session.setAttribute("Redirect",redirectUrl); 

			FullAuthOauthService authService = FullAuthOauthService.builder().authDomain("fullcreative")
					.clientId(FullConstants.CLIENT_ID.getValue()).responseType(OauthResponseType.CODE)
					.scope("awapis.identity").redirectUri(redirectUrl)
					.approvalPrompt(OauthApprovalPrompt.FORCE)
					.devServer(true)
					.build();

			String authUrl = authService.generateAuthorizationUrl();
			res.sendRedirect(authUrl);

		}

		catch (Exception e) {
			pr.println("Invalid Input");
			e.printStackTrace();
			
		}
	}
}