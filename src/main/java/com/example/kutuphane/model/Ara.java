package com.example.kutuphane.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
public class Ara {
    private Integer i;

    @NotBlank(message = "Lütfen boş bırakmayınız")
    @Length(max = 45, message = "45 karakterden az olmalıdır")
    private String text;

    public Ara() {
    }

    public Ara(Integer i, String text) {
        this.i = i;
        this.text = text;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
