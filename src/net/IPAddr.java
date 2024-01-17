package net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddr {

    public static void main(String[] args) throws Exception {
        InetAddress local = InetAddress.getByName("www.google.com");
        System.out.println(local.getCanonicalHostName());
        System.out.println(local.getHostAddress());
    }
}
