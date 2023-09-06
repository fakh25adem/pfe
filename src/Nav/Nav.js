
import React,{useEffect,useState}  from 'react';// import jsPDF from 'jspdf';
// import html2canvas from 'html2canvas';
//import axios from 'axios';
import jwtDecode from 'jwt-decode';
import AddAlertRoundedIcon from '@mui/icons-material/AddAlertRounded';
import CalendarMonthRoundedIcon from '@mui/icons-material/CalendarMonthRounded';
import AccountCircleRoundedIcon from '@mui/icons-material/AccountCircleRounded';
import axios from 'axios';
function Nav() {
    const token = localStorage.getItem('token');
    const decodedToken = jwtDecode(token);
    const [List, setList] = useState([]);
    useEffect(() => {
     
        axios.get('http://localhost:4000/wave/dec',{ headers: {
          Authorization: `Bearer ${token}`,
      }},)
          .then(response => {
            setList(response.data);
            console.log(response.data);
            console.log(List);
    
          })
          .catch(error => {
            console.error(error);
          });
      });
      const [showMessage, setShowMessage] = useState(false);
   
    return (
      <div>
        <div className='header'>
             
            {/* <input type="datetime-local"   onChange={(event) => setdatedebut(event.target.value)} /> 
           <input type="datetime-local"   onChange={(event) => setdatefin(event.target.value)} /> 
           

             <button   onClick={handle} >date</button> */}
           <div className='line' > 
           <div
      onMouseOver={() => setShowMessage(true)}
      onMouseLeave={() => setShowMessage(false)}
    >
      <AddAlertRoundedIcon className='icon1' />
     {showMessage && (

      
        <div className='message'>
            {List.map(item => {
        return(         
      
         <ul>
             <li>  Module de communication qui a comme adressIp : <b className='item'> {item.addressIp} </b> n'a acunne information sur le gate  </li>
          </ul>      
)})}
        </div>
      )} 

    </div>
    
          <a href='event'>  <CalendarMonthRoundedIcon className='icon1'/></a>
             <AccountCircleRoundedIcon className='icon1'/>
            <h3 className='np'> {decodedToken.nom } {decodedToken.prenom}</h3> 
          

         

{/*   
    {rech.map(item => {
        return( 
          <tbody>
          <tr>
               <td>{item.nom} </td>
                 <td>{item.prenom} </td>
                 <td > {item.email}</td>
                 <td>{item.tel} </td>
                 <td>{item.nomporte} </td>
                 <td > <span >{item.status}</span> </td>
                 <td > {item.date} </td>
                     </tr> </tbody>
                    
)})} */}
        </div>
                  
        </div>
        </div>
        
    );
}

export default Nav;