import React, { useEffect, useState } from 'react'
import axios from "axios";
import { Link, useParams } from 'react-router-dom';
const Home = () => {

  const [users, setUsers] = useState([])

  const {id} = useParams()
  useEffect(() => {
    loadUsers()
  }, []);

  const loadUsers = async () => {
    const result = await axios.get("http://localhost:8080/users")
    setUsers(result.data)
  }

  const deletedata =async(id)=>{
   await axios.delete(`http://localhost:8080/user/${id}`)
   loadUsers()
  }
  return (
    <div className='container'>
      <div className='py-4'>
        <table className=" table border shadow">
          <thead>
            <tr>
              <th scope="col">No</th>
              <th scope="col">Name</th>
              <th scope="col">Username</th>
              <th scope="col">Email</th>
              <th scope="col"></th>
              <th scope="col">Action</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            {
              users.map((user, index) => (
                <tr>
                  <th scope="row" key={index}>{user.id}</th>
                  <td>{user.name}</td>
                  <td>{user.username}</td>
                  <td>{user.email}</td>
                  <td><Link to={`/viewuser/${user.id}`} className='btn btn-primary mx-2'>View</Link></td>
                  <td><Link to={`/edituser/${user.id}`} className='btn btn-outline-primary mx-2'>Edit</Link></td>
                  <td><button className='btn btn-danger mx-2' onClick={()=>deletedata(user.id)}>Delete</button></td>
                </tr>
                
              ))
            }


          </tbody>
        </table>
      </div>
    </div>
  )
}

export default Home