package com.example.Client.Controller;
import com.example.Client.Dto.WaveshareDto;
import com.example.Client.Model.Alarme;
import com.example.Client.Model.Waveshare;
import com.example.Client.Service.ServiceWaveshare;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/wave")

public class WaveshareController {
    private ServiceWaveshare servicewWaveshare;
    @GetMapping("/get")
    public List<WaveshareDto> getwave(){
        List<Waveshare> waveshareList= servicewWaveshare.find();
         // supposons que vous avez déjà une liste d'objets Alarme

        List<WaveshareDto> listewave = new ArrayList<>();

        for (Waveshare wave : waveshareList) {
             // supposons que le nom de l'alarme se trouve dans une propriété "nom" de l'objet Alarme
            WaveshareDto waveshareDto =new WaveshareDto(wave.getId_wav(), wave.getNomwave(), wave.getAddressIp(),
                    wave.getStatuswav());
            listewave.add(waveshareDto);
        }

return listewave;
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody  Waveshare waveshare)  {

String str = waveshare.getAddressIp();
System.out.println(waveshare.getNomwave());
if(waveshare.getAddressIp().isEmpty()||waveshare.getNomwave().isEmpty()){
    return ResponseEntity.badRequest()
            .body("Ajouter une adressIp ou/et le nom du module du communication");
}
        try {
         InetAddress.getByName(waveshare.getAddressIp());
        } catch (UnknownHostException e) {
            return ResponseEntity.badRequest()
                    .body("Format addressIp invalid (Entre 0.0.0.0. et 255.255.255.255)");
        }

        if(servicewWaveshare.findByAddressIp(waveshare.getAddressIp())!=null){
            return   ResponseEntity.badRequest()
                    .body("Le module du communication exist déja  ");

        }


        if(!str.contains("."))
        {
            return   ResponseEntity.badRequest()
                    .body("Format addressIp invalid (Entre 0.0.0.0. et 255.255.255.254)");
        }
Alarme alarme = new Alarme();



        servicewWaveshare.ajouter(waveshare);
        return ResponseEntity.ok("Valid");
    }
    @DeleteMapping ("/sup/{address}")
    public ResponseEntity<?> sup(@PathVariable  String address) {
      Waveshare waveshare =  servicewWaveshare.findByAddressIp(address);

       servicewWaveshare.supprimer(address);
       return ResponseEntity.ok("supprimer");
    }
    @GetMapping("/dec")
    public List<WaveshareDto> getwavedec() {
        List<Waveshare> waveshareList = servicewWaveshare.deconnecter("Déconnecté");
        // supposons que vous avez déjà une liste d'objets Alarme

        List<WaveshareDto> listewave = new ArrayList<>();

        for (Waveshare wave : waveshareList) {
            // supposons que le nom de l'alarme se trouve dans une propriété "nom" de l'objet Alarme
            WaveshareDto waveshareDto = new WaveshareDto(wave.getId_wav(), wave.getNomwave(),wave.getAddressIp(),
                    wave.getStatuswav());
            listewave.add(waveshareDto);
        }
        return listewave;
    }
}
