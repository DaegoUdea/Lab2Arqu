/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.logic.PaymentFacadeLocal;
import com.udea.model.Payment;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author diego.alvarezg
 */
public class PaymentBean implements Serializable {

    @EJB
    private PaymentFacadeLocal paymentFacade;

    //defino el componente myButton para la validacion de la tarjeta de credito.
    private UIComponent mybutton;
    //Defino los atributos de mi vista.
    private int id;
    private double amount;
    private long card;
    private Date date = new Date();
    private int cvvnNo;
    private String contraseña;
    private String repContraseña;
    //valor para desactivar  o activar el boton procesar
    private boolean disable = true;

    //Cadenas para manejar los mensajes de la tarjeta 
    String sSubcadena;
    String mensajeCard;
    String m;

    public PaymentFacadeLocal getPaymentFacade() {
        return paymentFacade;
    }

    public void setPaymentFacade(PaymentFacadeLocal paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    public UIComponent getMybutton() {
        return mybutton;
    }

    public void setMybutton(UIComponent mybutton) {
        this.mybutton = mybutton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCard() {
        return card;
    }

    public void setCard(long card) {
        this.card = card;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCvvnNo() {
        return cvvnNo;
    }

    public void setCvvnNo(int cvvnNo) {
        this.cvvnNo = cvvnNo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRepContraseña() {
        return repContraseña;
    }

    public void setRepContraseña(String repContraseña) {
        this.repContraseña = repContraseña;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getsSubcadena() {
        return sSubcadena;
    }

    public void setsSubcadena(String sSubcadena) {
        this.sSubcadena = sSubcadena;
    }

    public String getMensajeCard() {
        return mensajeCard;
    }

    public void setMensajeCard(String mensajeCard) {
        this.mensajeCard = mensajeCard;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    //Logica metodo guardar.
    public String guardar() {
        //Instacion el objeto Payment 
        Payment p = new Payment();
        //llamo los setters y getters del POJO
        p.setId(id);
        p.setAmount(amount);
        p.setCard(card);
        p.setCvvno(cvvnNo);
        p.setDate(date);
        //llamo al metodo create del facade
        this.paymentFacade.create(p);
        //Cargo el tipo de tarjeta de credito en el String
        m = this.getMensajeCard();
        return "PaymentCreate";
    }

    //Logica metodo validar de la tarjeta de credito.
    //valida el rango de los primeros 4 digitos segun el tipo de tarjeta de credito
    //Visa o Mastercard, si esta en algun rango valido se activa del boton de procesar 
    public String validar() {
        String sCadena;
        //tomo el campo card
        sCadena = String.valueOf(card);
        //Tomo los 4 digitos
        sSubcadena = sCadena.substring(0, 4);
        //convierto el rango a  int
        int val = Integer.parseInt(sSubcadena);
        //valido rango para VISA
        if (val >= 000 && val <= 5555) {
            FacesMessage message = new FacesMessage("TARJETA VISA");
            FacesContext context = FacesContext.getCurrentInstance();
            //si el mensaje es valido lo cargo al contexto de mybutton
            context.addMessage(mybutton.getClientId(context), message);
            mensajeCard = "es Visa";
            //activo el boton de procesar.
            disable = false;
            this.setMensajeCard(mensajeCard);
            return this.getMensajeCard();
        } else if (val >= 8000 && val <= 9999) {
            FacesMessage message = new FacesMessage("TARJETA MASTERCARD");
            FacesContext context = FacesContext.getCurrentInstance();
            //si el mensaje es valido lo cargo al contexto de mybutton
            context.addMessage(mybutton.getClientId(context), message);
            mensajeCard = "Es mastercard";
            //activo el boton de procesar.
            disable = false;
            this.setMensajeCard(mensajeCard);
            return this.getMensajeCard();
        }else {
            FacesMessage message = new FacesMessage("TARJETA INVALIDA");
            FacesContext context = FacesContext.getCurrentInstance();
            //si el mensaje es valido lo cargo al contexto de mybutton
            context.addMessage(mybutton.getClientId(context), message);
        }
        return null;
    }
    
    //Para internacionalizacion
    private Locale locale = FacesContext
            .getCurrentInstance().getViewRoot().getLocale();
    
    public Locale getLocale(){
        return locale;
    }
    
    public String getLanguage() {
        return locale.getLanguage();
    }
    
    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }

    public PaymentBean() {
    }

}
