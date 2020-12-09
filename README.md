Basit bir kütüphane otomasyon web uygulaması. Yazar ve yayınevi ekleyip, ardından kitap ekleyebilirsiniz. Bazı işlemleri yapmak için admin rolünde olmanız gerekiyor. 

# **Kullanılan Teknolojiler**
- Spring Boot
- Jpa
- OAuth2
- Maven
- Thymeleaf
- Bootstrap
- MySQL
------------
# **Kurulum**
    git clone https://github.com/requizm/kutuphane.git
    cd kutuphane
    mvn spring-boot:run
------------
# **Kabuller**
- Standart ve tek admin hesap bilgileri; email: `root@root.com`   şifre: `root` 
- Kayıt olduğunuz veya github ile giriş yaptığınız her hesap, otomatik USER rolü olarak kabul edilir.
- ADMIN rolü her işlemi yapabiliyorken, USER rolü sadece yayınevi ve yazar işlemlerini yapabilir.
- Application.properties dosyasından veritabanı ayarları ve github oauth2 ayarları yapmayı unutmayın.
------------
# **Notlar**
- Exception class'larının işe yaramadığının farkındayım, prototip olarak kalsın diye bıraktım. 
- Birim testlerin çok verimli olduğunu söyleyemem, o da prototip gibi oldu. 
------------
