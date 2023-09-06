package com.example.Client.Service;


import com.example.Client.Model.Waveshare;
import com.example.Client.Repository.WaveshareRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ServiceWaveshareImpl implements ServiceWaveshare {
    private final WaveshareRepository waveshareRepository;



    @Override
    public Waveshare findByAddressIp(String addressIp) {
        return this.waveshareRepository.findByAddressIp(addressIp);
    }

    @Override
    public List<Waveshare> find() {
        return waveshareRepository.findAll();
    }

    @Override
    public void ajouter(Waveshare waveshare) {
        waveshareRepository.save(waveshare);
    }

    @Override
    public void supprimer(String adressip) {
     Waveshare waveshare  = waveshareRepository.findByAddressIp(adressip);
     if(waveshare.getPorte()!=null) {
         waveshare.getPorte().setWave(null);
     }
        waveshareRepository.deleteByAddressIp(adressip);
    }

    @Override
    public List<Waveshare> deconnecter(String s) {
        return waveshareRepository.deconnecter(s) ;
    }

   /* @Override
    public void insert(LocalDateTime date, String sat, Integer idp, Integer idd) {
        waveshareRepository.insertPersonnel(date,sat,idp,idd);
    }
  /*  @Override
    public List<Waveshare> findid(String adrr) {
        return waveshareRepository.find(adrr);
    }
    */

}
