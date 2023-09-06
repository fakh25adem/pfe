package com.example.Client.Service;
import com.example.Client.Model.Alarme;
import com.example.Client.Repository.AlarmeRepository;
import com.example.Client.Repository.PersonneRepository;
import com.example.Client.Repository.WaveshareRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@AllArgsConstructor
public class ServiceServeurImpl implements ServiceServeur{

    private WaveshareRepository waveshareRepository;

    private ServiceAlarme serviceAlarme;

    private ServiceEvenement serviceEvenement;
    private PersonneRepository personneRepository;


    @Override
    public void getInformation() {


Socket client = null;
ServerSocket server;
        try {
             server = new ServerSocket(1300);
            //server.setReuseAddress(true);
            System.out.println("en attente");
            System.out.println(server);
            String a = "";
            List<Integer> integersOut= new ArrayList<>();
            List<Integer> integersIn = personneRepository.getCinByRole();
            System.out.println("hello");
            while (true) {

                  client = server.accept();
                 waveshareRepository.modify("Connecté", client.getInetAddress().getHostAddress());

                System.out.println("New client connected =  " + client.getInetAddress().getHostAddress());
                InputStream d = client.getInputStream();



                while (true) {
                    a = a + Integer.toHexString(d.read());
                    if (a.length() > 76) {


                        System.out.println(a);
                         if (a.contains("120085f")) {

                            System.out.println("Stayed On");
                            serviceAlarme.insertalarme(LocalDateTime.now(),"Alarme de blocage");
                             a=a.replace("789450", "120089");

                        }
                         else if (a.contains("1200862")) {

                             serviceAlarme.insertalarme(LocalDateTime.now(),"Alarme de queue");
                             System.out.println("Tailling Alarm");



                         }
                         else if (a.contains("1200860")) {

                             System.out.println("Intrusion Alarm");
                             serviceAlarme.insertalarme(LocalDateTime.now(),"Alarme d'intrusion");

                         }
                         else if (a.contains("1200861")) {

                             System.out.println("Reverse Alarm");
                             serviceAlarme.insertalarme(LocalDateTime.now(),"Alarme inversée");

                         }
                         else if (a.contains("31012")) {
                            System.out.println("Entry Open");
                             Random random = new Random();
                             int randomIndex = random.nextInt(integersIn.size());
                             int randomValue = integersIn.get(randomIndex);
                             integersOut.add(randomValue);
                           serviceEvenement.CreerEvenement(client.getInetAddress().getHostAddress(),
                                   randomValue,"Entrée Ouverte");
                             integersIn.remove(Integer.valueOf(randomValue));

                        } else if (a.contains("120089")) {
                            System.out.println("Entry close");
                            Integer i=integersOut.get(integersOut.size()-1);
                             serviceEvenement.CreerEvenement(client.getInetAddress().getHostAddress(),i,"Entrée Fermée");
                            System.out.println(integersIn);
                            System.out.println(integersOut);

                        } else if (a.contains("31011")) {
                            System.out.println("Sortie Ouverte");
                             Random random = new Random();
                             int randomIndex = random.nextInt(integersOut.size());
                             int value = integersOut.get(randomIndex);

                             serviceEvenement.CreerEvenement(client.getInetAddress().getHostAddress(),
                                     value,"Sortie Ouverte");
                             integersIn.add(value);

                             integersOut.remove(Integer.valueOf(value));

                        } else if (a.contains("120088")) {
                            System.out.println("Close Exit");
                             Integer i=integersIn.get(integersIn.size()-1);
                             serviceEvenement.CreerEvenement(client.getInetAddress().getHostAddress(),
                                     i,"Sortie Fermée");

                             System.out.println(integersOut);


                        }   else {
                            System.out.println("rien");
                        }
                        a = client.getInetAddress().getHostAddress();

                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            waveshareRepository.modify("Déconnecté", client.getInetAddress().getHostAddress());

        }

    }
}
