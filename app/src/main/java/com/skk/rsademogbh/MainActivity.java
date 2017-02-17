package com.skk.rsademogbh;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn1, btn2;// 加密，解密
	private EditText et1, et2, et3;// 需加密的内容，加密后的内容，解密后的内容
	private EditText et4;

	/* 密钥内容 base64 code */
	private static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHpicKssPjh3p1aHEtLQrGjvFqYQe9Qwj+P56dj8fnYa3xamxzwZrHzhZCgjjxKBOgTyhwUcCAnjMxp9laIf1KkIvE2RN5Nkaq6NvW5BZEvqUMW7BEh4yiZdAXK+MjLWm2Qhf8j0YEI5R4DEYCjshMJ0wYVpM05K8kNNFP7B+F9QIDAQAB";
	private static String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIemJwqyw+OHenVocS0tCsaO8WphB71DCP4/np2Px+dhrfFqbHPBmsfOFkKCOPEoE6BPKHBRwICeMzGn2Voh/UqQi8TZE3k2Rqro29bkFkS+pQxbsESHjKJl0Bcr4yMtabZCF/yPRgQjlHgMRgKOyEwnTBhWkzTkryQ00U/sH4X1AgMBAAECgYBj2pB01KFUdV9U3Bwr6DM9dO4Lo/+hd55AIq7tR3EdR49W3kOVdpgsqu1B6kBmbVz9LigTfmqZg1smG2vpaInd7OLLjgZzumOrArvLQdwScNM5Dn+kZIBJ7N5iVag5aP2KCX9AM/CIqyW6J0nfB9KUffU2YkmE+ZdZurVWm3Y0JQJBAM8pRUVUIu4jfKrntIb3X7Ffo4OoP/ODVAeDmQkJaaNqmDpycm+SqyKZDqZBC8PaFBRwW9UDVcuZvL7lNcmoS+cCQQCnoO6S0ZkQhgWpS0AmmwcCK/nsmc2XhOLf1rQ1dm4EKDSEvAkG67cOsR/2Usl6IYlGlQKJyZwabKbC3Z/WZgPDAkA+5n4U9d4BRp8k2WO0E0pn9e0VHbIFQ1vxSCDgYI5Fwyjjnjpm7DawM58CFf/3gLDWH+OSQwf64PwxTjFNwJ8DAkAw8gy3UfwflwKQLCjPHPUu7ShMrZwaYfLc6RQ1iB8Xl6W+HCmGm80XvSBYDFRIFQLAWUIkeXnbPV50B8JkF+WBAkA7oVRBgJcDcx7KBuRCwrd+goCslV6hz36Uc4CdJsZfyVDcLFthFgFGoePWSPh08POGJIHrwt+SrWlKLsoXWNbu"
			+ "\r";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		String source = "adadwadawfsjfijrrojgiojrgjeorgjejrg";
		PublicKey publicKey;

		try {
			publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
			byte[] encryptByte = RSAUtils.encryptData(source.getBytes(), publicKey);
			// 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
			String afterencrypt = Base64Utils.encode(encryptByte);
			Log.i("kk", afterencrypt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 加密
	}

	private void initView() {
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);

		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		et3 = (EditText) findViewById(R.id.et3);
		et4 = (EditText) findViewById(R.id.et4);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 加密
		case R.id.btn1:
			String source = et1.getText().toString().trim();
			try {
				// 从字符串中得到公钥
				// PublicKey publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
				// 从文件中得到公钥
			//	InputStream inPublic = getResources().getAssets().open("rsa_public_key.pem");
				//PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
				PublicKey publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
				// 加密
				byte[] encryptByte = RSAUtils.encryptData(source.getBytes(), publicKey);
				// 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
				String afterencrypt = Base64Utils.encode(encryptByte);
				et2.setText(afterencrypt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		// 解密
		case R.id.btn2:
			long now_before = new Date().getTime();
			String encryptContent = et2.getText().toString().trim();

			try {
				// 从字符串中得到私钥
				// PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
				// 从文件中得到私钥
			//	InputStream inPrivate = getResources().getAssets().open("pkcs8_rsa_private_key.pem");
				PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
				// 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
				byte[] decryptByte = RSAUtils.decryptData(Base64Utils.decode(encryptContent), privateKey);
				String decryptStr = new String(decryptByte);
				et3.setText(decryptStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			long now_after = new Date().getTime();
			long date = now_after - now_before;
			Toast.makeText(getApplicationContext(), date+"", Toast.LENGTH_LONG).show();
			et4.setText(""+date);
			break;
		default:
			break;
		}
	}

}