package com.example.Client.Service;

import com.example.Client.Model.Evenement;
import com.example.Client.Model.Porte;
import com.example.Client.Repository.PorteRepository;
import com.example.Client.Repository.WaveshareRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicePorteImpl implements ServicePorte {
    private PorteRepository repositoryPorte;


    @Override
    public Porte findByid(Integer id) {
        return
                repositoryPorte.findByidporte(id);
    }


}
