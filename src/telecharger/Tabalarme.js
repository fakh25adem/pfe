import React,{useState,useEffect} from 'react';
import GetAppRoundedIcon from '@mui/icons-material/GetAppRounded';
import jsPDF from 'jspdf';
 import html2canvas from 'html2canvas';
function Tabalarme(props) {
    const [myData, setMyData] = useState([]);
    useEffect(() => {
    const donnees=sessionStorage.getItem('myData');
    
    if (donnees) {
        setMyData(JSON.parse(donnees));
      }
    }, []);
    
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
    return (
        <div>
        <br></br>
      <br></br>
        <div id='my-page'    className='table'>
    <table  >
    <thead>
          <tr>
              <th> Id_Alarme </th>
              <th> Date_Alarme </th>
              <th> Status_Alarme <GetAppRoundedIcon onClick={downloadPDF}/> </th>
              
          </tr>
      </thead>
      
   {myData.map(item => {
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
           <tbody  >
         <tr>
              <td>{item.id_alarme} </td>
                <td>{formattedDate} </td>
                <td > <span className={getStatusClass(item.status_alarm)}>{item.status_alarm}</span> </td>
                    </tr> </tbody>                 
)})}
       </table>
      </div> 
        </div>
    );
}

export default Tabalarme;