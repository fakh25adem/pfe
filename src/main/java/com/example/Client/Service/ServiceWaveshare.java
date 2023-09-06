package com.example.Client.Service;

import com.example.Client.Model.Waveshare;

import java.util.List;


public interface ServiceWaveshare {

   public Waveshare findByAddressIp(String addressIp);
   public List<Waveshare> find();
   public void ajouter (Waveshare waveshare);
   public void supprimer(String adressip);
   public List <Waveshare> deconnecter(String s);


}
