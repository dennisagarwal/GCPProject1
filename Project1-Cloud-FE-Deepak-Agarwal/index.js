let loginBtn = document.querySelector('#login-btn');

loginBtn.addEventListener('click', async()=>{
  let usernameInput = document.querySelector('#username');
  let passwordInput = document.querySelector('#password');

  const URL = 'http://localhost:8081/login';

  const jsonString = JSON.stringify({
    "username": usernameInput.value,
    "password": passwordInput.value
  })

  let res = await fetch(URL,{
    method: 'POST',
    body: jsonString,
  });


  //Get the token and store the token in localStorage
  //localStorage is accessible from any point in the browser
  let token = res.headers.get('Token');
  localStorage.setItem('jwt',token);

  if(res.status === 200) {
    let user = await res.json();

    localStorage.setItem('user_id',user.id);//keep track of the userId in the local storage

    if(user.userRole === 'manager'){
      window.location ='./manager-page.html'
    }  else if(user.userRole === 'employee'){
      window.location ='./employee-page.html'
    }


  console.log(user);
  }else{
   let errorMsg = await res.text();
   console.log(errorMsg);

    let errorElement = document.querySelector('#error-msg');
    errorElement.innerText= errorMsg;
    errorElement.style.color = "red";
  }
});