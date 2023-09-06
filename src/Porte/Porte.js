import'../Liste/liste_tab.css';
import Liste from '../Liste/Liste';
import React,{useEffect,useState}  from 'react';
import Navbar from "../Nav/Nav";
import jwtDecode from 'jwt-decode';

import axios from 'axios';
function Porte(){
  const token = localStorage.getItem('token');
  const decode  = jwtDecode(token);
  const [issuedAtDate, setIssuedAtDate] = useState('');
 


  // console.log(dec)
  useEffect(() => {
   
    const issuedAt = new Date(decode.iat * 1000);
    setIssuedAtDate(issuedAt.toLocaleString());
 
  }, []);
  // console.log(issuedAtDate)



    const [List, setList] = useState([]);
    
    function getStatusClass(status) {
        if (status === "Sortie Fermée"||status === "Entrée Fermée") {
          return "rouge";
        }
        else {
            return "vert"
        }
       
      }
    
//  console.log(formattedDate);
useEffect(() => {
   
  const data = {
    datenow: issuedAtDate
  };
  
    axios.get('http://localhost:4000/porte/get',{ headers: {
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
    <div id='my-page' className='table_body'>
    <table  >
    <thead>
          <tr>
              <th> <br></br> Id_Porte </th>
              <th> <br></br> Nom_Porte </th>
              <th> <br></br> Event_Porte </th>
              <th> <br></br> Date  </th>
              
              
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
        return( 
           <tbody>
         <tr>
              <td>{item.idporte} </td>
                <td>{item.nomporte} </td>
                <td > <span className={getStatusClass(item.statusporte)}>{item.statusporte === "Entrée Fermée" || item.statusporte === "Sortie Fermée" ? "Fermeé" : "Ouvert"}</span> </td>
                <td>{formattedDate} </td>
                    </tr> </tbody>
                    
)})}
       </table>
      </div> 
      </div>
    
   </div>
        </div>
    );
}

export default Porte;