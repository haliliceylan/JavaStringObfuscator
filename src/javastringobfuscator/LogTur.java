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
public enum LogTur {
BILGI("[BILGI]"),HATA("[HATA]");
private String OnEk;
private LogTur(String Onek){
    this.OnEk = Onek;
}
    public String getOnEk() {
        return OnEk;
    }

}
