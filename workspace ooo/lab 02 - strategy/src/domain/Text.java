package domain;

import domain.strategy.Cipher;

public class Text {

	private Cipher cipher;
	
	public Text(Cipher cipher) {
		this.cipher = cipher;
	}
	
	public String executeCipher(String text) {
		return cipher.coder(text);
	}
}
