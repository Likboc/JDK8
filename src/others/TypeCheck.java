package others;

import java.net.HttpURLConnection;
import java.net.URLConnection;

public class TypeCheck {
    public static void main(String[] args) {
        /**
         * instanceof only support object's parent check
         */
        boolean a1 = "1" instanceof String;
//        boolean a2 = HttpURLConnection instanceof URLConnection;
        System.out.println(String.valueOf(a1));
        /**
         * isAssignableFrom() support the abstractclass and the interface also
         */
        boolean b1 = HttpURLConnection.class.isAssignableFrom(URLConnection.class);


    }
}
