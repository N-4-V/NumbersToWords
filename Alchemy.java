package com.firstcode.code;

public class Alchemy {
	// NOTE- A whitespace is added before each tag
	static String units[] = {" One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine"};
	static String tens[] = {" Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"};
	static String elevens[] = {" Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"};
	static String larger[] = {" MahaShankh", " Shankh", " Padma", " Neel", " Kharab", " Arab", " Crore", " Lakh", " Thousand", " Hundred"};
	private String num;
	static int limitForLarger;
	
	Alchemy(String n) {
		this.num = n;
	}
	
	private String fetch(char position, int mode) {
		// fetches corresponding word from array as specified by mode
		if(mode == 1)
			return units[Character.getNumericValue(position) - mode]; 
		else
			return tens[Character.getNumericValue(position) - mode]; 
	}
	
	private String fetch(int position, int mode) {
		if(mode == 0)
			return elevens[position - 10];
		else
			return larger[position];
	}
	
	// Mode 1 = units[], 2 = tens[], 0 = elevens[], 3 = larger[] 
	
	private String[] tokenize(String stream) {
		// Converts 12345678 --> 01 23 45 678
		
		int i, spaceAt = 0, streamSize = stream.length();
		String tokens = "";
		
		// adds in the last token first
		for(i = streamSize - 3; i < streamSize; i ++) {
			tokens += stream.charAt(i);
		}
		tokens = " " + tokens;
		
		// The iteration is done in reverse as the first token could have a single digit, which
		// would throw off the iterator
		for(i = streamSize - 4; i >= 0; i --) {
			spaceAt ++;
			tokens = stream.charAt(i) + tokens;
			if(spaceAt % 2 == 0) {
				tokens = " " + tokens;
			}
		}
		if(streamSize % 2 == 0)	{ 
			tokens = "0" + tokens; // when first token only has a single digit
		}
	
		String tokenStream[] = tokens.split(" ");
		return tokenStream;
	}
	
	private String classify(String token) {
		// classifies given token according to constraints
		String result = "";
		int numToken = Integer.parseInt(token);
		
		if(numToken > 0) {
			
			if(numToken >= 10 && numToken <= 19) {
				result += fetch(numToken, 0); 
			}
			else if(token.charAt(0) == '0') { // example: 02
				result += fetch(token.charAt(1), 1);
			}
			else if(token.charAt(1) == '0') { // example: 20, 30, etc
				result += fetch(token.charAt(0), 2);
			}
			else { // example: 46, 73
				result += fetch(token.charAt(0), 2) + fetch(token.charAt(1), 1); 
			}
		}
		if (limitForLarger >= larger.length)
			return result;
		else {
			result += fetch(limitForLarger, 3);
			limitForLarger ++;
			return result;
		}
	}
	
	String toLetters() {
		
		int i = 0; // used as iterator variable
		String tokenStream[] = tokenize(num);
		limitForLarger = larger.length - tokenStream.length;
		String wordStream = "";
		
		for(i = 0;i < tokenStream.length - 1;i ++) { // traverses till second last token
			wordStream += classify(tokenStream[i]);
		}
		
		if(Integer.parseInt(tokenStream[i]) == 0) { // when last 3 digits are 0
			return wordStream;
		}
		else {
			if(tokenStream[i].charAt(0) != '0') { // when hundred's place is non-zero 
				wordStream += fetch(tokenStream[i].charAt(0), 1) + fetch(limitForLarger, 3);
				limitForLarger ++;
			}
			wordStream += classify(tokenStream[i].substring(1));
		}
		
		return wordStream;
	}
	
	public static void main(String[] args) {
		Alchemy a = new Alchemy("95425678");
		
		String words = a.toLetters();
		System.out.println(words);
	}

}
