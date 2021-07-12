

import java.io.IOException;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ローカル変数
		String uname;
		String mail;
		String pass;
		boolean mc;
		
		//Beansの生成
		SignUpBean suBean = new SignUpBean();
		
		//htmlから値の受け取り
		uname = request.getParameter("name");
		mail = request.getParameter("mail1");
		pass = request.getParameter("pass");
		
		//Beansに値をセット
		suBean.setMail(mail);
		suBean.setPass(pass);
		suBean.setUname(uname);
		
		mc = suBean.mailcheck();
		
		if(mc==false) {
			getServletContext().getRequestDispatcher("/ErrorInput.html").forward(request,response);
			return;
		}
		
		//Insertを実行
		suBean.insert();
	}
	
	/*String redecode(String str){
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
	*/

}
