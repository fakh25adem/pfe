import React ,{useState} from 'react';
import './Login.css';
import Axios from "axios";
function Login(){

    const after = ()=>{

const container = document.getElementById('container');
container.classList.add("right-panel-active");
};

const after1 = ()=>{
    
const container = document.getElementById('container');

	container.classList.remove("right-panel-active");
}; 
 const [cin, setcin] = useState("");
 const [nom, setnom] = useState("");
 const [prenom, setprenom] = useState("");
 const [email, setemail] = useState("");
 const [mdp, setmdp] = useState("");
 const [email1, setemail1] = useState("");
 const [mdp1, setmdp1] = useState("");
 const [tel, settel] = useState("");
 const [role, setrole] = useState("");

 const[err,seterr]=useState([ 
 "La taille  8 et 13 caractères"]);

   const handleSubmit = (e) => {
           e.preventDefault();
      const userData = {
    cin:cin,nom:nom, prenom:prenom,email:email, mdp:mdp,role:role, tel:tel ,
        };
        Axios.post("http://localhost:4000/personne/creer", userData,{   
             headers: {
            'Content-Type': 'application/json'
          }}).then((response) => {
            const alertBox = document.createElement('div'); // Crée une nouvelle div
            alertBox.classList.add('alert1'); // Ajoute la classe CSS pour la boîte d'alerte
        
            const message = document.createElement('p'); // Crée un élément p pour le message
            message.textContent = response.data; // Ajoute le texte dans l'élément p
        
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
            seterr([])
         
        
         }).catch((error)=>{
          seterr(error.response.data)
          console.log(error.response.data)
        
    
         
        
         });
        };
       

        const handleSignin = (e) => {
            e.preventDefault();
            const Data = {
                email: email1,
                mdp: mdp1
                    };
            Axios.post('http://localhost:4000/personne/up',Data,{
            headers: {
                'Content-Type': 'application/json'
              }
            }
            ).then((res) => {
                localStorage.setItem('token', res.data.token);
                console.log(res.data.token)
                window.location.href = '/event'
                console.log(res.data)
            
          }).catch((e)=>{
            const alertBox = document.createElement('div'); // Crée une nouvelle div
            alertBox.classList.add('alertlogin'); // Ajoute la classe CSS pour la boîte d'alerte
        
            const message = document.createElement('p'); // Crée un élément p pour le message
            message.textContent = e.response.data; // Ajoute le texte dans l'élément p
        
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
            }, 3000);
          });
         };
         const containerStyle = {
          height: `${Math.max(err.length * 20 + 500, 500)}px`,
        };
      
      
      return(
        
        <div  className="container" id="container" style={containerStyle}  >
          
        <div className="form-container sign-up-container"   >
            <form  >
                <h1>Creer un compte</h1>
                <input type="text" placeholder=" Numéro du cin  " className="champs"
                value={cin} onChange={(event) => setcin(event.target.value)} />
                 {err.filter((error) => error.includes('cin'))
          .map((error) => (
            <div className='error-message' key={error}>{error}</div>
          ))}
                <input type="text" placeholder="Nom " className="champs"
                value={nom} onChange={(event) => setnom(event.target.value)} />
                       {err.filter((error) => error.includes('Nom'))
          .map((error) => (
            <div className='error-message' key={error}>{error}</div>
          ))} 
                 <input type="text" placeholder="prenom "  className="champs"value={prenom} onChange={(event) => setprenom(event.target.value)} />
                 {err
          .filter((error) => error.includes('prénom'))
          .map((error) => (
            <div className='error-message' key={error}>{error}</div>
          ))}
                 <input type="text" placeholder="Email "  className="champs"value={email} onChange={(event) => setemail(event.target.value)} />
                 {err
          .filter((error) => error.includes('email')|| error.includes('électronique'))
          .map((error) => (
            <div className='error-message' key={error}>{error}</div>
          ))}
                 <input type="password" placeholder="Mot du passe "  className="champs"value={mdp} onChange={(event) => setmdp(event.target.value)} />
                 {err
          .filter((error) => error.includes('passe'))
          .map((error) => (
            <div className='error-message' key={error}>{error}</div>
          ))}
                 <input type="text" placeholder="Numéro du télephone "  className="champs" value={tel} onChange={(event) => settel(event.target.value)} />
                 {err
          .filter((error) => error.includes('télépohone'))
          .map((error) => (
            <div className='error-message' key={error}>{error}</div>
          ))}
                 <select class="champs" value={role}  onChange={(event) => setrole(event.target.value)}     >
                    
                <option  value="">Seléctioner un role</option>
                <option>admin</option>
                <option>user</option>
                </select>
                {err
          .filter((error) => error.includes('role'))
          .map((error) => (
            <div className='error-message' key={error}>{error}</div>
          ))}
              
              
                <button className='sign'  onClick={handleSubmit} >Creer un compte</button>
            </form>
        </div>
        <div class="form-container sign-in-container">
            <form action="" onSubmit={handleSignin} >
                <h1>S'identifier</h1>
                 <input type="text" placeholder="email "  className="champs"value={email1} onChange={(event) => setemail1(event.target.value)} />
                 <input type="password" placeholder="mot de passe "  className="champs"value={mdp1} onChange={(event) => setmdp1(event.target.value)} /> 
        <button className='sign' >s'identifier</button> 
              
           
             
            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
           
                    <h1>EasyTek</h1>
                    <p>Entrez vos données personnelles et faire un nouveau compte </p>
                    <button class="ghost" id="signIn" onClick={after1}  >s'identifier</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h1>EasyTek</h1>
                    <p>Entrez vos données personnelles et commencez la journée avec nous</p>
                    <button class="ghost" id="signUp" onClick={after}>Creer un compte</button>
                </div>
            </div>
        </div>
      
    </div>
        )

      }
    export default Login;