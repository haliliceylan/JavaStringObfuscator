/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastringobfuscator;

/**
 *
 * @author Userxxx
 */
public class Stringx {
    private String Orjinal;
    private String Temiz;
    private String Encrypt;
    public Stringx(String Orjinal, String Temiz, String Encrypt) {
        this.Orjinal = Orjinal;
        this.Temiz = Temiz;
        this.Encrypt = Encrypt;
    }

    public String getEncrypt() {
        return Encrypt;
    }

    public String getOrjinal() {
        return Orjinal;
    }

    public String getTemiz() {
        return Temiz;
    }

    @Override
    public String toString() {
        return "Stringx{" + "Orjinal=" + Orjinal + ", Temiz=" + Temiz + ", Encrypt=" + Encrypt + '}';
    }
    
}
