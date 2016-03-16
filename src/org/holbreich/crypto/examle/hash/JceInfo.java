package org.holbreich.crypto.examle.hash;

import java.security.Provider;
import java.security.Security;

public class JceInfo {

	//http://docs.oracle.com/javase/1.5.0/docs/guide/security/CryptoSpec.html#AppA
    public static void main(String[] args) {
        System.out.println("Algorithms Supported in this JCE.");
        System.out.println("More detail can be found at http://docs.oracle.com/javase/1.5.0/docs/guide/security/CryptoSpec.html#AppA");
        System.out.println("====================");
        // heading
        System.out.println("Provider: type.algorithm -> className" + "\n  aliases:" + "\n  attributes:\n");
        // discover providers
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            System.out.println("====" + provider + "====\n");
            // discover services of each provider
            for (Provider.Service service : provider.getServices()) {
                System.out.println(service);
            }
            System.out.println();
        }
    }
}