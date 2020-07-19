package technology.touchmars.javaq.string;

public class StringEqual {
    public static void main(String[ ] args) {
        String s1 = "Hello";
        String s2 = new String(s1);
        String s3 = "Hello";

        System.out.println(s1 + " equals " + s2 + "--> " +  s1.equals(s2)); //true
        System.out.println(s1 + " == " + s2 + " --> " + (s1 == s2)); //false
        System.out.println(s1 + " == " + s3+ " --> " + (s1 == s3)); //true

        String s5 = new StringBuffer().append("Hello").toString();
        System.out.println(" s1 == s5 ---> " + (s1==s5)); //false
        String s6 = new StringBuilder().append("Hello").toString();
        System.out.println(" s1 == s6 ---> " + (s1==s6)); //false
    }
}