

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet(description = "新規登録", urlPatterns = { "/SignUpServlet" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ローカル変数
		String uname;
		String mail1;
		String mail2;
		String pass;
		
		//Beansの生成
		SignUpBean suBean = new SignUpBean();
		
		//htmlから値の受け取り
		uname = redecode(request.getParameter("name"));
		mail1 = redecode(request.getParameter("mail1"));
		mail2 = redecode(request.getParameter("mail2"));
		pass = redecode(request.getParameter("pass"));
		
		if(mail1 != mail2) {
			getServletContext().getRequestDispatcher("/ErrorInput.html").forward(request,response);
			return;
		}
		
		suBean.set_mail(mail1);
		suBean.set_pass(passwd);
		ctmBean.setI_name(i_name);
		ctmBean.setI_address(i_address);
		ctmBean.setI_tel(i_tel);
		ctmBean.setI_date(i_date);
		ctmBean.setI_new(i_new);
		ctmBean.setI_cnt(i_cnt);
		ctmBean.setI_kingaku(i_kingaku);
	}
	
	String redecode(String str){
		String ret;
		if(str==null || str.equals(""))
			return null;
			try{
				ret = new String(str.getBytes("ISO-8859-1"), "JISAutoDetect");
			}catch(UnsupportedEncodingException ex){
				ret = str;
			}
			return ret;
		}

}
