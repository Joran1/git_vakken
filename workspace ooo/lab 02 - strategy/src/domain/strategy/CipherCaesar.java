package domain.strategy;

public class CipherCaesar implements Cipher{
	
	private int shift;
	
	public CipherCaesar(int shift) {
		this.shift = shift;
	}
	
	@Override
	public String coder(String text) {
	    String s = "";
	    int len = text.length();
	    for(int x = 0; x < len; x++){
	        char c = (char)(text.charAt(x) + shift);
	        if (c > 'z')
	            s += (char)(text.charAt(x) - (26 - shift));
	        else
	            s += (char)(text.charAt(x) + shift);
	    }
	    return s;
	}	
}
