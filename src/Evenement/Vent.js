import'../Liste/liste_tab.css';
import Liste from '../Liste/Liste';
import React,{useEffect,useState}  from 'react';

// import jsPDF from 'jspdf';
// import html2canvas from 'html2canvas';
import jwtDecode from 'jwt-decode';
import AddAlertRoundedIcon from '@mui/icons-material/AddAlertRounded';
import CalendarMonthRoundedIcon from '@mui/icons-material/CalendarMonthRounded';
import AccountCircleRoundedIcon from '@mui/icons-material/AccountCircleRounded';
import axios from 'axios';
function Vent(){
  const [List, setList] = useState([]);
  const token = localStorage.getItem('token');
  const decodedToken = jwtDecode(token);
 
  const [issuedAtDate, setIssuedAtDate] = useState('');
 


  // console.log(dec)
  useEffect(() => {
   
    const issuedAt = new Date(decodedToken.iat * 1000);
    setIssuedAtDate(issuedAt.toLocaleString());
    
  }, []);
  // const [visible, setVisible] = useState(false);
    function getRole(role) {
     
     if(role==='user'){
       return "hidd";
     }
    }
  

    function getStatusClass(status) {
        if (status === "Entrée Ouverte"|| status === "Entrée Fermée") {
          return "vert";
        }
        else{
          return "rouge"
        }
      }
       
    useEffect(() => {
   
      const data = {
        date: issuedAtDate
      };
      
        axios.get('http://localhost:4000/event/get',{ headers: {
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
        
          axios.post("http://localhost:4000/event/recherche", userData,{   
               headers: {
                 Authorization: `Bearer ${token}`,
              'Content-Type': 'application/json'
            }}).then((res) => {
     
              sessionStorage.setItem('myData', JSON.stringify(res.data));
           window.location.href = '/tabevent';
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
    return (
<div>
<div className='display'>
      <div>
      <Liste/>
      </div>
<div>
<div>
  <div className='header'>
      <input type="datetime-local" id={getRole(decodedToken.role[0].authority)} 
       onChange={(event) => setdatedebut(event.target.value)} /> 
           <input type="datetime-local" id={getRole(decodedToken.role[0].authority)} 
             onChange={(event) => setdatefin(event.target.value)} /> 
           

             <button   onClick={handle} className="filtre" id={getRole(decodedToken.role[0].authority)} >Filtre</button> 
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
            <h3 className='np'> {decodedToken.nom } {decodedToken.prenom}</h3> 
            </div>
      </div>
      </div>
    
  <hr></hr>
    <div id='my-page' className='table_body'>
    <table  >
    <thead>
          <tr>
        
              <th> <br></br> Nom </th>
              <th> <br></br> Prénom </th>
              <th> <br></br>Porte </th>
              <th> <br></br> Evenement_Personne</th>
              <th><br></br> Date </th>
              <th> <br></br>Détails</th>
              
              
          </tr>
      </thead>
      
     {List.map(item => {
        const dateObj = new Date(item.date);
        const formattedDate = dateObj.toLocaleDateString('fr-FR', { 
          year: 'numeric', 
          month: 'long', 
          day: 'numeric',
          hour: 'numeric',
          minute: 'numeric',
          second: 'numeric'
        });
        if (item.status !== "Entrée Ouverte" && item.status !== "Sortie Ouverte") {
        return( 
           <tbody >
         <tr>
        
              <td>{item.nom} </td>
                <td>{item.prenom} </td>
                {/* <td > {item.email}</td>
                <td>{item.tel} </td> */}
                <td>{item.nomporte} </td>
                <td > <span className={getStatusClass(item.status)}> {item.status === "Entrée Fermée" ? "Entrée" : "Sorti"}</span> </td>
                <td > {formattedDate}
               
                 </td>
                 <td>  <button title={
                                `Email: ${item.email}
Téléphone: ${item.tel}`}>Plus</button>
     
                 </td>
                    </tr> </tbody>
                    
)} else {
  return null; // si la ligne est exclue, renvoie null pour ne rien afficher
}})}  



       </table>
     

      
      </div> 
      </div>
      </div>
      </div>
  
        
    );
}


export default Vent;