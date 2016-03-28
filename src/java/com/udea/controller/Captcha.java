package com.udea.controller;

import javafx.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;

public class Captcha {
    
    public void check(ActionEvent e) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Su codigo es correcto", null));
    }

    public Captcha() {
    }
    
}
