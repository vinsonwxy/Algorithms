import java.math.BigInteger;

public class PA1 {
	public static String multiplication(String l1, String l2) {
		System.out.println(l1);
		System.out.println(l2);
		BigInteger x = new BigInteger(l1);
		BigInteger y = new BigInteger(l2);
		if (l1.length() == 1 && l2.length() == 1) {
			return x.multiply(y).toString();
		}  
		if (l1.length() < l2.length()) {
			for (int i = 0; i < l2.length() - l1.length(); i++){
				l1 = "0" + l1;
			} 
		} else if (l1.length() > l2.length()) {
			for (int i = 0; i < l1.length() - l2.length(); i++){
				l2 = "0" + l2;
			} 
		}
		int length = l1.length();
		int mid = length / 2;
		String a = l1.substring(0, mid);
		String b = l1.substring(mid);
		String c = l2.substring(0, mid);
		String d = l2.substring(mid);
		String ac = multiplication(a, c);
		String bd = multiplication(b, d);
		String full = "1";
		for (int i = 0; i < length; i++) {
			full = full + "0";
		}
		String half = "1";
		for (int i = 0; i < mid; i++) {
			half = half + "0";
		}

		BigInteger aPlusB = new BigInteger(a).add(new BigInteger(b));
		BigInteger cPlusD = new BigInteger(c).add(new BigInteger(d));
		String cross = multiplication(aPlusB.toString(), cPlusD.toString());

		BigInteger gauss = new BigInteger(cross).subtract(new BigInteger(bd)).subtract(new BigInteger(ac));
		BigInteger acTerm = new BigInteger(ac).multiply(new BigInteger(full));
		BigInteger total = gauss.multiply(new BigInteger(half)).add(acTerm).add(new BigInteger(bd));
		return total.toString();
	}

	public static void main(String[] args) {
		System.out.println(multiplication(
			"3141592653589793238462643383279502884197169399375105820974944592", 
			"2718281828459045235360287471352662497757247093699959574966967627"));
	}
}