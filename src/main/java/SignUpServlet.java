

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> paramMap = request.getParameterMap();

		System.out.println("param count: " + Integer.toString(paramMap.size()));
		for (String key : paramMap.keySet()) {
		    String[] values = paramMap.get(key);
		    System.out.println(
		            String.format("%s ===> %s",
		                    key,
		                    (values != null ? String.join(", ", values): "")
		                    )
		            );
		}		
		//ローカル変数
		String uname;
		String mail;
		String pass;
		String mc;
		
		//Beansの生成
		SignUpBean suBean = new SignUpBean();
		
		//htmlから値の受け取り
		uname = request.getParameter("name");
		mail = request.getParameter("mail1");
		pass = request.getParameter("password");
		
		//Beansに値をセット
		suBean.setMail(mail);
		suBean.setPass(pass);
		suBean.setUname(uname);
		
		System.out.println(mail);
		
		mc = suBean.mailcheck();
		
		if(mc=="false") {
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

