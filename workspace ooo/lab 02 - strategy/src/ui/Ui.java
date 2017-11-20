package ui;

import domain.*;
import domain.strategy.*;

public class Ui {

	public static void main(String[] args) {
		Text text = new Text(new CipherCaesar(3));
		System.out.println(text.executeCipher("vguv"));
		
		Text text2 = new Text(new CipherMirror());
		System.out.println(text2.executeCipher("test"));

	}

}
