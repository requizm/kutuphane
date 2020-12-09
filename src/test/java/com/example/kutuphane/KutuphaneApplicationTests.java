package com.example.kutuphane;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.example.kutuphane.model.HesapTurleri;
import com.example.kutuphane.model.Kitap;
import com.example.kutuphane.model.Kullanici;
import com.example.kutuphane.model.Roller;
import com.example.kutuphane.model.Yayinevi;
import com.example.kutuphane.model.Yazar;
import com.example.kutuphane.repository.KitapRepository;
import com.example.kutuphane.repository.KullaniciRepository;
import com.example.kutuphane.repository.YayineviRepository;
import com.example.kutuphane.repository.YazarRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class KutuphaneApplicationTests {

	@Autowired
	private YayineviRepository yayineviRepository;

	@Autowired
	private YazarRepository yazarRepository;

	@Autowired
	private KitapRepository kitapRepository;

	@Autowired
	private KullaniciRepository kullaniciRepository;

	@Test
	public void testyayinEviOlustur() {
		Yayinevi yayinevi = new Yayinevi("ad", "açıklama");
		yayineviRepository.save(yayinevi);
		Yayinevi yayinevi2 = yayineviRepository.findByAciklama("açıklama").get(0);
		assertNotNull(yayinevi2);
		assertEquals(yayinevi2.getAd(), yayinevi.getAd());
		assertEquals(yayinevi2.getAciklama(), yayinevi.getAciklama());
	}

	@Test
	public void testyazarOlustur() {
		Yazar yazar = new Yazar("ad", "açıklama");
		yazarRepository.save(yazar);
		Yazar yazar2 = yazarRepository.findByAd("ad").get(0);
		assertNotNull(yazar2);
		assertEquals(yazar2.getAd(), yazar.getAd());
		assertEquals(yazar2.getAciklama(), yazar.getAciklama());
	}

	@Test
	public void testkullaniciOlustur() {
		Kullanici kullanici = new Kullanici("ad@ad.com", "şifre", Roller.ROLE_ADMIN, HesapTurleri.LOCAL);
		kullaniciRepository.save(kullanici);
		Kullanici kullanici2 = kullaniciRepository.findByEmail("ad@ad.com").get(0);
		assertNotNull(kullanici2);
		assertEquals(kullanici2.getEmail(), kullanici.getEmail());
		assertEquals(kullanici2.getSifre(), kullanici.getSifre());
		assertEquals(kullanici2.getHesap(), kullanici.getHesap());
		assertEquals(kullanici2.getRol(), kullanici.getRol());
	}

	@Test
	public void testkitapOlustur() {
		Yazar yazar = new Yazar("Orhan Pamuk", "za");
		yazarRepository.save(yazar);
		Yayinevi yayinevi = new Yayinevi("Can Yayınları", "oke");
		yayineviRepository.save(yayinevi);
		Kitap kitap = new Kitap("Günlükler", "Altadı", "Seri", yazar, yayinevi, "abcdefglhgtyu", "uhuhu");
		kitapRepository.save(kitap);
		Kitap kitap2 = kitapRepository.findByIsbnNumarasi("abcdefglhgtyu").get(0);
		assertNotNull(kitap2);
		assertEquals(kitap2.getAd(), kitap.getAd());
		assertEquals(kitap2.getAltAdi(), kitap.getAltAdi());
		assertEquals(kitap2.getSeriAdi(), kitap.getSeriAdi());
		assertEquals(kitap2.getIsbnNumarasi(), kitap.getIsbnNumarasi());
		assertEquals(kitap2.getIsbnNumarasi(), kitap.getIsbnNumarasi());
		assertEquals(kitap2.getYayinevi(), kitap.getYayinevi());
		assertEquals(kitap2.getYazar(), kitap.getYazar());
		assertEquals(kitap2.getId(), kitap.getId());
	}

}
