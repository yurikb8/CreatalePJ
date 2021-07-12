import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpBean {
	
	private int     countOfResults;
	
	private String mail;
	private String uname;
	private String pass;
	private int uid;
	
	//コンストラクタ
	public SignUpBean() {
	}
	
	//セッター
	public void setMail(String mail1){
		this.mail = mail1;
	}
	public void setPass(String pass){
		this.pass = pass;
	}
	public void setUname(String uname){
		this.uname = uname;
	}
	public void setUid(int uid){
		this.uid = uid;
	}
	
	//ゲッター
	public String getMail(){
		return mail;
	}
	
	public String getPass(){
		return pass;
	}
	
	public int getUid(){
		return uid;
	}
	
	public String getUname() {
		return uname;
	}
	
	//mailが存在するかのチェック
	public String mailcheck() {
		String mc = "false";
		
		
		Connection        con     = null;
		String            sql     = null;
		PreparedStatement preStmt = null;
		ResultSet         rs      = null;
		
		try {
	        // JDBC Driverの登録
	            Class.forName("com.mysql.jdbc.Driver");

	        // データベースへの接続
	            con = DriverManager.getConnection (
	                        "jdbc:mysql://localhost/CreataleDB?" +
	                        "useUnicode=true&characterEncoding=UTF-8",
	                        "root","kcsf");

	        // SQL文の作成
	            sql = "Select * from user "+
					 	"where mail = ?";
	            
	       //PreparedStatementの発行
	            preStmt = con.prepareStatement(sql);
	            
	            preStmt.setString(1, mail);
	            
	         
	         // SQL ステートメントの発行
	            rs = preStmt.executeQuery();
	            
	            for (countOfResults=0; rs.next(); countOfResults++);
	            
	            if(countOfResults == 0) {
	            	mc = "true";
	            }
	            
		}catch (Exception ex) {
            System.out.println("Exception: "+ex.getMessage());
        } finally {
        // データベースのクローズ
            if (preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException ex) { }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { }
            }
       }
		return mc;
	}
	
	//Insert作業
	public void insert() {
        Connection        con     = null;
		String            sql     = null;
		PreparedStatement preStmt = null;
	
		try {
        // JDBC Driverの登録
            Class.forName("com.mysql.jdbc.Driver");

        // データベースへの接続
            con = DriverManager.getConnection (
                        "jdbc:mysql://localhost/CreataleDB?" +
                        "useUnicode=true&characterEncoding=Shift_JIS",
                        "root","kcsf");

        // SQL文の作成
            sql = "INSERT INTO User "+
				 	"VALUES(0, ?, ?, ?)";

        // Preparedステートメントの作成
            preStmt = con.prepareStatement(sql);

        // パラメータの設定
            preStmt.setString(1, uname);
			preStmt.setString(2, mail);
			preStmt.setString(3, pass);

        // SQL ステートメントの発行
            preStmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Exception: "+ex.getMessage());
        } finally {
        // データベースのクローズ
            if (preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException ex) { }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { }
            }
        }
    }
}