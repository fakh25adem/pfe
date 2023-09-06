import React,{useEffect,useState}  from 'react';
//import jsPDF from 'jspdf';
import axios from 'axios';

import jwtDecode from 'jwt-decode';
import AddAlertRoundedIcon from '@mui/icons-material/AddAlertRounded';
import CalendarMonthRoundedIcon from '@mui/icons-material/CalendarMonthRounded';
import AccountCircleRoundedIcon from '@mui/icons-material/AccountCircleRounded';

import'../Liste/liste_tab.css';
import Liste from '../Liste/Liste';

function Alarme() {

 const token = localStorage.getItem('token');
 const decodedToken = jwtDecode(token);

 const [issuedAtDate, setIssuedAtDate] = useState('');



 // console.log(dec)
 useEffect(() => {
  
   const issuedAt = new Date(decodedToken.iat * 1000);
   setIssuedAtDate(issuedAt.toLocaleString());

 }, []);


 function getRole(role) {
  
  if(role==='user'){
    return "hidd";
  }
}
    
          const [List, setList] = useState([]);
          function getStatusClass(status) {
          if (status === "Alarme d'intrusion") {
            return "jaune";
          } else if (status==="Alarme de blocage") {
            return "blue";
          }
          else if (status==="Alarme de queue"){
             return "orange"
          }
          else{
            return "vert"
          }
        }
     

     
          useEffect(() => {
            const data = {
              date: issuedAtDate
            };
  
            axios.get('http://localhost:4000/alarme/get', 
            { headers: {
              Authorization: `Bearer ${token}`,
          }, params: data
        })
              .then(response => {
                setList(response.data);
          
              })
              .catch(error => {
                console.error(error);
              });
          });
    const [datefin, setdatefin] = useState("");
    const [datedebut, setdatedebut] = useState("");
    
    
    const handle = (e) => {
        e.preventDefault();
      const userData = {
   
         datedebut: datedebut,
         datefin: datefin
             
      }
      
        axios.post("http://localhost:4000/alarme/recherche", userData,{   
             headers: {
               Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
          }}).then((res) => {
   
            sessionStorage.setItem('myData', JSON.stringify(res.data));
         window.location.href = '/tabalarme';
      console.log(res.data)
           
         }).catch((err)=>{
           
  const alertBox = document.createElement('div'); // Crée une nouvelle div
          alertBox.classList.add('alert'); // Ajoute la classe CSS pour la boîte d'alerte
      
          const message = document.createElement('p'); // Crée un élément p pour le message
          message.textContent = err.response.data; // Ajoute le texte dans l'élément p
      
          const closeButton = document.createElement('span'); // Crée un élément span pour le bouton de fermeture
          closeButton.classList.add('close'); // Ajoute la classe CSS pour le bouton de fermeture
          closeButton.textContent = '×'; // Ajoute le texte "×" dans le bouton de fermeture
          closeButton.addEventListener('click', () => {
            alertBox.remove(); // Supprime la boîte d'alerte lorsque l'utilisateur clique sur le bouton de fermeture
          });
      
          alertBox.appendChild(closeButton); // Ajoute le bouton de fermeture à la boîte d'alerte
          alertBox.appendChild(message); // Ajoute le message à la boîte d'alerte
      
          document.body.appendChild(alertBox);       
          setTimeout(() => {
            alertBox.style.display = 'none'; // Rend la boîte d'alerte invisible après 3 secondes
          }, 2000);  
         });
       }
       const [List1, setList1] = useState([]);
       useEffect(() => {
          
        axios.get('http://localhost:4000/wave/dec',{ headers: {
          Authorization: `Bearer ${token}`,
      }},)
          .then(response => {
            setList1(response.data);
          
           
          })
          .catch(error => {
            console.error(error);
          });
      });
       
        const [showMessage, setShowMessage] = useState(false);

    
    return(
    <div >
       
    
      <div className='display'>
        
      <div>
     
      <Liste/>
      
      </div>
 <div>
 <div>
  <div className='header'>
      <input type="datetime-local"id={getRole(decodedToken.role[0].authority)}   onChange={(event) => setdatedebut(event.target.value)} /> 
      <input type="datetime-local" id={getRole(decodedToken.role[0].authority)}  onChange={(event) => setdatefin(event.target.value)} /> 
           

             <button   onClick={handle} className="filtre " id={getRole(decodedToken.role[0].authority)}  >Filtre</button> 
      <div className='blockline' > 
      <div
      onMouseOver={() => setShowMessage(true)}
      onMouseLeave={() => setShowMessage(false)}
    >
      <AddAlertRoundedIcon className='icon1' />
     {showMessage && (

      
        <div className='message'>
            {List1.map(item => {
        return(         
      
         <ul>
             <li> Le Wavesahre qui a comme adressIp : <b className='item'> {item.addressIp} </b> n'a acunne information sur le gate  </li>
          </ul>      
)})}
        </div>
      )} 

    </div>
       
          <a href='event'>  <CalendarMonthRoundedIcon className='icon1'/></a>
                             <AccountCircleRoundedIcon className='icon1'/>
            <h3 className='np'>{decodedToken.nom } {decodedToken.prenom}</h3> 
            </div>
          
      </div>
      </div>
{/*  
     <button className='btn' onClick={downloadPDF}>Télécharger en PDF</button> */}
  <hr></hr>
    <div id='my-page'   className='table_body'>
    <table  >
    <thead>
          <tr>
              <th> <br></br> Id_Alarme </th>
              <th> <br></br> Date_Alarme </th>
              <th> <br></br> Status_Alarme  </th>
              
          </tr>
      </thead>
   
   {List.map(item => {
       const dateObj = new Date(item.datealarm);
       const formattedDate = dateObj.toLocaleDateString('fr-FR', { 
         year: 'numeric', 
         month: 'long', 
         day: 'numeric',
         hour: 'numeric',
         minute: 'numeric',
         second: 'numeric'
       });
        return( 
           <tbody >
         <tr>
              <td>{item.id_alarme} </td>
                <td>{formattedDate} </td>
                <td > <span className={getStatusClass(item.status_alarm)}>{item.status_alarm}</span> </td>
                    </tr> </tbody>                 
)})}
       </table>
      </div> 
      </div>
      
    
   </div>
      </div>
    
      )
       
    //  console.log(socket)
    // useEffect(() => {
    //   const socket = new SockJS('http://localhost:4000/websocket');
    //   console.log(socket)
    //   const stompClient = Stomp.over(socket);
    //   stompClient.connect({}, function(frame) {
    //     console.log('Connected: ' + frame);
    //     stompClient.subscribe('/topic/message', function(message) {
    //       console.log('Received: ' + message.body);
    //     });
    //   })});

    //  useEffect(() => {
        //   console.log(client)
        //      client.onopen = () => {
        //          console.log('WebSocket Client Connected');
        //      };
     
        //      client.onmessage = (msg) => {
        //          setMessage(msg.data);
        //          console.log(message)
        //      };
     
        //      client.onclose = () => {
        //          console.log('WebSocket Client Disconnected');
        //      };
        //  });
  
}

export default Alarme;