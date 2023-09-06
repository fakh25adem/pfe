// import logo from './logo.svg';
import './App.css';
import { Routes, Route} from "react-router-dom";
import Login from './Login/Login';
import Alarme from './Alarme/Alarme';
import Wave from './Wavesahre/Wave';
import Porte from './Porte/Porte';
import jwtDecode from 'jwt-decode';
import Tabalarme from './telecharger/Tabalarme';
import Tabevent from './telecharger/Tabevent';
import Event from './Evenement/Vent';


function App() {
  const token = localStorage.getItem('token');
  var ok=false
  try {
    const decodedToken = jwtDecode(token);
    
    // Vérifier si la date d'expiration est passée ou non
    const isExpired = Date.now() >= decodedToken.exp * 1000;
    
    if (isExpired) {
     ok=false
    } else {
      ok=true
    }
    
  } catch (err) {
    ok=false
  }
  return (
    <div className="App">
 
      
    <Routes>
         <Route path="/" element={<Login/>} />
        {ok&&<Route path="/alarme" element={<Alarme/>}  />}
      {ok &&  <Route path="/wave" element={<Wave/>} />}
       {ok&& <Route path="/porte" element={<Porte/>} />}
       {ok&& <Route path="/event" element={<Event/>} />}
        {ok&&<Route path="/tabalarme" element={<Tabalarme/>} />}
        {ok&& <Route path="/tabevent" element={<Tabevent/>} />}
        
      </Routes>
    </div>
  );
}

export default App;
