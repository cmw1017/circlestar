package onebyn.common.filter;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptorWrapper extends HttpServletRequestWrapper{

	public EncryptorWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getParameter(String name) {
		String returnValue = "";
		if (name.equals("password")) {
			String pw = super.getParameter(name);
			System.out.println("pw : " + pw);
			String encPw = getSha512(pw);
			System.out.println("encPw : " + encPw);
			returnValue = encPw;
		} else {
			returnValue = super.getParameter(name);
		}

		return returnValue;
	}

	private String getSha512(String value) {
		String encPwd = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] bytes = value.getBytes(Charset.forName("UTF-8"));
		md.update(bytes);
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		return encPwd;
	}
	
}
