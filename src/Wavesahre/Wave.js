
import'../Liste/liste_tab.css';
import Liste from '../Liste/Liste';
import React,{useEffect,useState}  from 'react';
import jwtDecode from 'jwt-decode';
// import jsPDF from 'jspdf';
// import html2canvas from 'html2canvas';
import Swal from 'sweetalert2';
import Navbar from "../Nav/Nav";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import axios from 'axios';
function Wave(){
  const [List, setList] = useState([]);
  const [adrr, setadrr] = useState("");
  const [nom, setnom] = useState("");
  const [visible, setVisible] = useState(false);


  const dec="Déconnecté"
  const token = localStorage.getItem('token');
  const decodedToken = jwtDecode(token);
if(decodedToken.exp * 1000>0)
{
console.log(decodedToken.exp * 1000)
}
// function removeTdBasedOnRole(role) {
//    // Remplacez "user" par votre condition de rôle

//   if (role === "user") {
//     const tdElement = document.getElementById("tdToRemove");
//     tdElement.parentNode.removeChild(tdElement);
//   }
// }

  function getRole(role) {
     
    if(role==='user'){
      return "hidd";
    }
   }
   function gettable(table){
    if(table==='user'){
      return "table_body";
    }else{
      return "table_wave";
    }
   }
    function getStatusClass(status) {
        if (status === "Déconnecté") {
          return "rouge";
        }
        else{
          return "vert"
        }
      }
      const handlewave = (e) => {
        e.preventDefault();
   const userData = {
    addressIp:adrr,nomwave:nom,statuswav:dec,  
     };
     
     axios.post("http://localhost:4000/wave/add", userData,{   
      headers: {
        Authorization: `Bearer ${token}`,
       }}).then(() => {
      
     
      }).catch((err)=>{
        const alertBox = document.createElement('div'); // Crée une nouvelle div
        alertBox.classList.add('alertlogin'); // Ajoute la classe CSS pour la boîte d'alerte
    
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
        }, 3500);
        
      });
     };
     const handlewaves = (adress) => {
      


Swal.fire({
  title: 'Êtes-vous sûr(e) ?',
  text: 'Voulez-vous vraiment supprimer cet élément ?',
  icon: 'warning',
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Oui, supprimer',
  cancelButtonText: 'Annuler'
}).then((result) => {
  if (result.isConfirmed) {
   axios.delete(`http://localhost:4000/wave/sup/${adress}`,{   
    headers: {
      Authorization: `Bearer ${token}`,
     }}).then(() => {
     
    
   
    }).catch(()=>{
     
  
    });
   }})};
    useEffect(() => {
      const token = localStorage.getItem('token');
        axios.get('http://localhost:4000/wave/get',{ headers: {
          Authorization: `Bearer ${token}`,
      }},)
          .then(response => {
            setList(response.data);
            console.log(response.data)
    
          })
          .catch(error => {
            console.error(error);
          });
      });
    return (
        <div>
             <div className='display'>
      <div>
      <Liste/>
      </div>
   <div>
   <div>
        <Navbar/>
      </div>
  <hr></hr>

    <div id='my-page' className={gettable(decodedToken.role[0].authority)}>
    <table  >
    <thead>
          <tr>
              <th> <br></br> Id_Module du communication </th>
              <th> <br></br> Nom_Module du communication </th>
              <th> <br></br> Adressip_Module du communication </th>
              <th> <br></br> Status_Module du communication </th>  
               <th id={getRole(decodedToken.role[0].authority)} > <br></br> Supprimer  </th>  
          </tr>
      </thead>

      
   {List.map(item => {
        return( 
           <tbody>
         <tr>
              <td>{item.id_wav} </td>
              <td>{item.nomwave}</td>
                <td>{item.addressIp} </td>
                <td > <span className={getStatusClass(item.statuswav)}>{item.statuswav}</span> </td>
                    {  <td> <DeleteForeverIcon id={getRole(decodedToken.role[0].authority)}    onClick={() => handlewaves(item.addressIp)}/></td> }
                    </tr> </tbody>
                    
)})}
       </table>
    
      </div> 
  <center>   <button  onClick={() => setVisible(!visible)} className='iadd' id={getRole(decodedToken.role[0].authority)} >Ajouter</button>
 
       {visible &&   
        <form className='form'   >
        
          <input type="text" className='adress' placeholder='AdressIp'  value={adrr} onChange={(event) => setadrr(event.target.value)}/>
       <br></br>
       <input type="text" className='adress' placeholder='NomWaveshare'  value={nom} onChange={(event) => setnom(event.target.value)}/>
       <br></br>
        <button className='add'  onClick={handlewave}>Confirmer l'ajout</button>
      
        </form>}
      
    </center> 
      </div>
   
    
   </div>
   
        </div>
        
    );
}

export default Wave;