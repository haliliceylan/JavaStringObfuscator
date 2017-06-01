/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastringobfuscator;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

/**
 *
 * @author Userxxx
 */
public class Encryptor {

    private static String random(int uzunluk) {
        String alfabe = "1234567890asdfghjklizxcvbnnmQWERTYUIOPASDFGHJKLZXCVBNM";
        char[] alfabecik = alfabe.toCharArray();
        StringBuffer kelime = new StringBuffer();
        Random rand = new Random();
        for (int x = 0; x < uzunluk; x++) {
            kelime.append(alfabecik[rand.nextInt(alfabecik.length - 1)]);
        }
        return kelime.toString();
    }

    private static String LocalDecrypt(String Key) {
        return new String(Base64.getDecoder()
                .decode(new StringBuilder(new String(Base64.getDecoder().decode(Key)))
                        .delete(new String(Base64.getDecoder().decode(Key)).toCharArray().length - 10,
                                new String(Base64.getDecoder().decode(Key)).toCharArray().length)
                        .delete(0, 10).toString()),StandardCharsets.UTF_8);
    }

    private static String LocalEncrypt(String Key) {
        String nKey = random(10) + Base64.getEncoder().encodeToString(Key.getBytes(StandardCharsets.UTF_8)) + random(10);
        nKey = Base64.getEncoder().encodeToString(nKey.getBytes(StandardCharsets.UTF_8));
        return nKey;
    }

    public static String Encryptor(String Metin) {
        String SifreliMetin = LocalEncrypt(Metin);
        return SifreliMetin;
    }
}
