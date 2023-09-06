import React from 'react';


import AddAlertRoundedIcon from '@mui/icons-material/AddAlertRounded';
import MeetingRoomRoundedIcon from '@mui/icons-material/MeetingRoomRounded';
import DeviceHubRoundedIcon from '@mui/icons-material/DeviceHubRounded';
import CalendarMonthRoundedIcon from '@mui/icons-material/CalendarMonthRounded';

import image from '../img/easy.png'
function Liste() {
 
 function deconnecter () {
    window.localStorage.clear();
    window.location.href="./"

  }
    return (
        <div>
    <div className='liste'>
  <ul className='container-liste'>
    <br></br>
    <br></br> 
    <img src={image} className="easy"></img>
    <br></br> 
    <br></br> 
   
    <li className='li'> <CalendarMonthRoundedIcon className='icon'/><a  href="event" class="professional-link journal">Journal d'événement</a></li>
    <li className='li'> <DeviceHubRoundedIcon className='icon'/> <a href="wave" class="professional-link wave">État  du la module  </a></li>
    <li className='li'> <MeetingRoomRoundedIcon className='icon'/><a href="porte" class="professional-link porte">État de la porte</a></li>
    <li className='li'> <AddAlertRoundedIcon className='icon'/><a href="alarme" class="professional-link alarme">Historique de l'alarme</a> </li>
  
  </ul>
  <br></br>
 
  <button onClick={deconnecter} className='decon'>Déconnecter</button>

   
</div>
        </div>
    );
}

export default Liste;