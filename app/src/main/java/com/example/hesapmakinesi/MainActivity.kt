package com.example.hesapmakinesi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var ilkSayi = 0.0
        var islem = ""
        var yeniGiris = false

        var sonuc = findViewById<TextView>(R.id.textSonuc)

        var buton0 = findViewById<Button>(R.id.button0)
        var buton1 = findViewById<Button>(R.id.button1)
        var buton2 = findViewById<Button>(R.id.button2)
        var buton3 = findViewById<Button>(R.id.button3)
        var buton4 = findViewById<Button>(R.id.button4)
        var buton5 = findViewById<Button>(R.id.button5)
        var buton6 = findViewById<Button>(R.id.button6)
        var buton7 = findViewById<Button>(R.id.button7)
        var buton8 = findViewById<Button>(R.id.button8)
        var buton9 = findViewById<Button>(R.id.button9)
        var butonAc = findViewById<Button>(R.id.buttonAc)
        var butonSil = findViewById<Button>(R.id.buttonSil)
        var butonEksi = findViewById<Button>(R.id.buttonEksi)
        var butonEsit = findViewById<Button>(R.id.buttonEsit)
        var butonArtı = findViewById<Button>(R.id.buttonArtı)
        var butonCarp = findViewById<Button>(R.id.buttonCarp)
        var butonYuzde = findViewById<Button>(R.id.buttonYuzde)
        var butonBol = findViewById<Button>(R.id.buttonBol)
        var butonVirgul = findViewById<Button>(R.id.buttonVirgul)
        var butonTers = findViewById<Button>(R.id.buttonTers)


        val sayiButonlari = listOf(buton0, buton1, buton2, buton3, buton4, buton5, buton6, buton7, buton8, buton9)
        for (buton in sayiButonlari) {
            buton.setOnClickListener {
                val sayi = (buton.text).toString()
                if (yeniGiris) {
                    sonuc.text = sayi
                    yeniGiris = false
                } else {
                    if (sonuc.text=="0"){
                        sonuc.text=""
                        sonuc.text = sonuc.text.toString() + sayi
                    }else{
                        sonuc.text = sonuc.text.toString() + sayi
                    }
                }
            }
        }

        butonSil.setOnClickListener {
            val mevcutMetin = sonuc.text.toString()
            if (mevcutMetin.isNotEmpty()) {
                sonuc.text = mevcutMetin.substring(0, mevcutMetin.length - 1)
                yeniGiris = false
            }
        }

        butonAc.setOnClickListener {
            sonuc.text = "0"
            ilkSayi = 0.0
            islem = ""
            yeniGiris = false
        }

        butonArtı.setOnClickListener {
            val sayi = sonuc.text.toString()
            if (sayi.isNotEmpty()) {
                ilkSayi = sayi.toDouble()
                islem = "+"
                yeniGiris = true
            }
        }

        butonEksi.setOnClickListener {
            val sayi = sonuc.text.toString()
            if (sayi.isEmpty()) {
                sonuc.text = "-"
            } else {
                ilkSayi = sayi.toDouble()
                islem = "-"
                yeniGiris = true
            }
        }

        butonCarp.setOnClickListener {
            val sayi = sonuc.text.toString()
            if (sayi.isNotEmpty()) {
                ilkSayi = sayi.toDouble()
                islem = "*"
                yeniGiris = true
            }
        }

        butonBol.setOnClickListener {
            val sayi = sonuc.text.toString()
            if (sayi.isNotEmpty()) {
                ilkSayi = sayi.toDouble()
                islem = "÷"
                yeniGiris = true
            }
        }

        butonYuzde.setOnClickListener {
            val sayi = sonuc.text.toString()
            if (sayi.isNotEmpty()) {
                ilkSayi = sayi.toDouble()
                islem = "%"
                yeniGiris = true
            }
        }

        butonVirgul.setOnClickListener {
            val metin = sonuc.text.toString()
            if (!metin.contains(".")) {
                sonuc.text = if (metin.isEmpty()) "0." else metin + "."
            }
        }

        butonTers.setOnClickListener {
            val sayi = sonuc.text.toString()
            if (sayi.isNotEmpty()) {
                if (sayi=="0"){
                    sonuc.text="-"
                }else{
                    sonuc.text = (sayi.toDouble() * -1).toString()
                }
            }
        }

        butonEsit.setOnClickListener {
            val ikinciSayiStr = sonuc.text.toString()
            if (ikinciSayiStr.isNotEmpty() && islem.isNotEmpty()) {
                val ikinciSayi = ikinciSayiStr.toDouble()
                var sonucDegeri = 0.0

                when (islem) {
                    "+" -> sonucDegeri = ilkSayi + ikinciSayi
                    "-" -> sonucDegeri = ilkSayi - ikinciSayi
                    "*" -> sonucDegeri = ilkSayi * ikinciSayi
                    "÷" -> sonucDegeri = if (ikinciSayi != 0.0) ilkSayi / ikinciSayi else Double.NaN
                    "%" -> sonucDegeri = (ilkSayi * ikinciSayi) / 100
                }

                sonuc.text = sonucDegeri.toString()
                islem = ""
                yeniGiris = true
            }
        }
    }
}
