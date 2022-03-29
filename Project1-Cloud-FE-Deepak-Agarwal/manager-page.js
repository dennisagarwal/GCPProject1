let logoutBtn = document.querySelector('#logout-btn');

logoutBtn.addEventListener('click',()=>{
  localStorage.removeItem('user_id')
  localStorage.removeItem('jwt');

  window.location = '/index.html';
});

window.addEventListener('load',(event)=>{
  populateReimbursementsTable();
});

async function populateReimbursementsTable(){
  const URL ='http://localhost:8081/reimbursements'

  let res = await fetch(URL,{
    method: 'GET',
    headers: {
      'Authorization':`Bearer ${localStorage.getItem('jwt')}` //include our jwt in request
    }
  })

  if(res.status === 200) {
    let reimbursements = await res.json();

    let tbody = document.querySelector('#reimbursements-tbl > tbody');
    tbody.innerHTML= '';

//iterate through the reimbursements for the GET /reimbursement request
//and populate table
    for (let reimbursement of reimbursements){

    let tr = document.createElement('tr');

    let td1 =document.createElement('td');
    td1.innerText = reimbursement.id;

    let td2 = document.createElement('td')
    td2.innerText = reimbursement.amount;


    let td3 = document.createElement('td')
    td3.innerText = reimbursement.submitDate;

    let td4 = document.createElement('td')
    td4.innerText = reimbursement.resolveDate;

    let td5 = document.createElement('td')
    td5.innerText = reimbursement.status;

    let td6 = document.createElement('td')
    td6.innerText = reimbursement.type;

    let td7 = document.createElement('td')
    td7.innerText = reimbursement.authorId;

    let td8 = document.createElement('td')
    td8.innerText = reimbursement.authorUserName;

    let td9= document.createElement('td')
    td9.innerText = reimbursement.authorFirst;

    let td10 = document.createElement('td')
    td10.innerText = reimbursement.authorLast;

    let td11 = document.createElement('td')
    td11.innerText = reimbursement.authorEmail;

    let td12 = document.createElement('td')
    td12.innerText = reimbursement.authorRole;

    let td13 = document.createElement('td')
    td13.innerText = (reimbursement.resolverId  ? reimbursement.resolverId : 'Not Res.');
    td13.style.color = (reimbursement.resolverId  ? reimbursement.resolverId.color : 'red');

    let td14 = document.createElement('td')
    td14.innerText = (reimbursement.resolverUserName? reimbursement.resolverUserName : 'Not Res.');
    td14.style.color = (reimbursement.resolverUserName? reimbursement.resolverUserName.color : 'red');

    let td15= document.createElement('td')
    td15.innerText = (reimbursement.resolverFirst? reimbursement.resolverFirst : 'Not Res.');
    td15.style.color = (reimbursement.resolverFirst? reimbursement.resolverFirst.color : 'red');

    let td16 = document.createElement('td')
    td16.innerText = (reimbursement.resolverLast? reimbursement.resolverLast : 'Not Res.');
    td16.style.color = (reimbursement.resolverLast? reimbursement.resolverLast.color : 'red');

    let td17 = document.createElement('td')
    td17.innerText = (reimbursement.resolverEmail? reimbursement.resolverEmail : 'Not Res.');
    td17.style.color = (reimbursement.resolverEmail? reimbursement.resolverEmail.color : 'red');

    let td18 = document.createElement('td')
    td18.innerText = reimbursement.resolverRole;

    let td19 = document.createElement('td')
    let imgElement = document.createElement('img')
    imgElement.setAttribute('src', `http://localhost:8081/reimbursement/${reimbursement.id}/image`);
    imgElement.style.height = '100px';
    td19.appendChild(imgElement);


    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);
    tr.appendChild(td6);
    tr.appendChild(td7);
    tr.appendChild(td8);
    tr.appendChild(td9);
    tr.appendChild(td10);
    tr.appendChild(td11);
    tr.appendChild(td12);
    tr.appendChild(td13);
        tr.appendChild(td14);
        tr.appendChild(td15);
        tr.appendChild(td16);
        tr.appendChild(td17);
        tr.appendChild(td18);
        tr.appendChild(td19);


//populate reimbursement that arent resolved with an inout and resove button
if(!reimbursement.resolverId){// if reimburesement is not being resolved
  let statusInput = document.createElement('input');
  statusInput.setAttribute('type', 'number'); //<input type = "number">
  statusInput.setAttribute('min', '1');
  statusInput.setAttribute('max', '3');


  //whenever the resolve button is clicked, send a patch request to change the status
  //of an reimbursement (providing a valid JSON web token from local storage we received
  //when logging in) and populate the reimbursement table again
  let statusButton = document.createElement('button');
  statusButton.innerText = 'Resolve Status';

  statusButton.addEventListener('click', async()=>{
    let status = statusInput.value;

    try{
    let res = await fetch(`http://localhost:8081/reimbursements/${reimbursement.id}?status=${status}`,{
    method: 'PATCH',
    headers: {
      'Authorization' : `Bearer ${localStorage.getItem('jwt')}`
    }
    });

    if (res.status === 200){
      populateReimbursementsTable();
    }
  }catch(e){
    console.log(e);
  }
  });

  tr.appendChild(statusInput);
  tr.appendChild(statusButton);

  // statusButton.addEventListener('click', async()=>{
  //   let status = statusInput.value
  // });

// tr.appendChild(statusInput);
// tr.appendChild(statusButton);

}

        // let tbody = document.querySelector('#reimbursements-tbl > tbody');
        tbody.appendChild(tr);
  }



  console.log(reimbursements);
}
}