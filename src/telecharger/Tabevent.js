import React,{useState,useEffect} from 'react';
import GetAppRoundedIcon from '@mui/icons-material/GetAppRounded';
import jsPDF from 'jspdf';
 import html2canvas from 'html2canvas';
function Tabevent() {
    const [myData, setMyData] = useState([]);
    useEffect(() => {
    const donnees=sessionStorage.getItem('myData');
    
    if (donnees) {
        setMyData(JSON.parse(donnees));
      }
    },[]);
    
        function downloadPDF() {
       const page = document.getElementById('my-page');
       console.log(page.innerHTML);
       html2canvas(page).then( canvas => {
         const imgData = canvas.toDataURL('image/png');
         console.log(imgData)
        const pdf = new jsPDF();
    
      pdf.addImage(imgData, 'PNG', 5, 5, 200, 120);
// Télécharger le fichier PDF
pdf.save('tableau.pdf');
       })}
  
       function getStatusClass(status) {
        if (status === "Entrée Ouverte"|| status === "Entrée Fermée") {
          return "vert";
        }
        else{
          return "rouge"
        }
      }
    return (
        <div>
         <br></br>
      <br></br>
        <div id='my-page' className='table'>
     
    <table  >
    <thead>
          <tr>
              <th> Nom </th>
              <th> Prénom </th>
              <th> Email </th>
              <th> Numéro du télèphone </th>
              <th>Porte </th>
              <th> Event </th>
              <th> Date <GetAppRoundedIcon onClick={downloadPDF}/>   </th>
             
          </tr>
          
      </thead>
     
     {myData.map(item => {
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
                <td >{item.email}</td>
                <td>{item.tel} </td>
                <td>{item.nomporte} </td>
                <td > <span className={getStatusClass(item.status)}> {item.status === "Entrée Fermée" ? "Entrée" : "Sorti"}</span> </td>
                <td > {formattedDate}</td>
                    </tr> </tbody>
                    
                    
)}else {
  return null; // si la ligne est exclue, renvoie null pour ne rien afficher
}})}  
       </table>
    
      </div> 
        </div>
    );
}

export default Tabevent;