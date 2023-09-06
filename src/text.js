
import React from 'react';
import { useState} from 'react';
import axios from 'axios';
function Text(){
    const [List,setList]=useState([]);
   



      axios.get("http://localhost:8000/product/lire").then((response) => {
       
        setList(response.data)
        console.log(response.data)
   
      })
      
      return(
      <div>
        <h1>hi</h1>
             {List.map((val,key)=>{
            return <tr>
                 <td>{val.id}</td>
        <td>{val.name}</td>
        <td>{val.price}</td>
        <td>{val.quantity}</td>
     
            </tr>
         })}  
        </div>
        )
      
    
    

    }
    export default Text;