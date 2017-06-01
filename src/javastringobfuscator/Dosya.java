/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastringobfuscator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Userxxx
 */
public class Dosya {

    private File Dosya;
    private String Fonk;
    private ArrayList<Stringx> Stringler = new ArrayList<Stringx>();

    public Dosya(File Dosya) {
        this.Dosya = Dosya;
    }

    public File getDosya() {
        return Dosya;
    }

    public void Cevir(String Fonk) {
        this.Fonk = Fonk;
        try {
            StringleriOku();
            Arayuz.logger(LogTur.BILGI, Dosya.getName() + " Adlı Dosyada " + Stringler.size() + " Tane String Bulundu.");
        } catch (Exception ex) {
            Arayuz.logger(LogTur.HATA, Dosya.getName() + " Adlı Dosyada String Bulurken Hata.");
            ex.printStackTrace();
            return;
        }
        try {
            YeniBelgeyiYaz();
            Arayuz.logger(LogTur.BILGI, Dosya.getName() + " Adlı Dosyada " + Stringler.size() + " Tane String Sifrelendi.");

        } catch (Exception ex) {
            Arayuz.logger(LogTur.HATA, Dosya.getName() + " Adlı Dosyada String Sifrelerken Hata.");
            for (Stringx stringx : Stringler) {
                Arayuz.logger(LogTur.BILGI, stringx.getOrjinal() + "=>" + stringx.getTemiz());
            }
            ex.printStackTrace();
            return;
        }
    }

    private void StringleriOku() throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(Dosya), "UTF8"))){
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.trim().startsWith("@")){
                    StringBul(line);
                }
                
            }
        }
    }

    private void StringBul(String Satir) {
        char[] harfler = Satir.toCharArray();
        String Orjinal = "";
        String Temiz = "";
        boolean StringVar = false;
        boolean KacisKarakteri = false;
        for (char harf : harfler) {
            if (!KacisKarakteri) {
                if (harf == '"') {
                    Orjinal += harf;
                    StringVar = !StringVar;
                    if (StringVar == false && Orjinal != "" && Temiz != "") {
                        Stringler.add(new Stringx(Orjinal, Temiz, Encryptor.Encryptor(Temiz)));
                        Orjinal = "";
                        Temiz = "";
                    }
                    continue;
                }
            }
            if (StringVar) {
                Orjinal += harf;
                if (harf == '\\' && !KacisKarakteri) {
                    KacisKarakteri = true;
                    continue;
                }
                Temiz += harf;
                if (KacisKarakteri) {
                    KacisKarakteri = false;
                }
            }
        }
    }

    private void YeniBelgeyiYaz() throws Exception {
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(Dosya.toPath()), charset);
        for (Stringx stringx : Stringler) {
            content = content.replace(stringx.getOrjinal(), Fonk + "(\"" + stringx.getEncrypt() + "\")");
        }
        Files.write(Dosya.toPath(), content.getBytes(charset));
    }
}
