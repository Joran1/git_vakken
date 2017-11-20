package domain.strategy;

public class CipherMirror implements Cipher {

	@Override
	public String coder(String text) {
		return new StringBuilder(text).reverse().toString();
	}
}
