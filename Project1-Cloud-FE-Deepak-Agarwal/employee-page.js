window.addEventListener('load',(event)=>{
  populateReimbursementsTable();
});


let logoutBtn = document.querySelector('#logout-btn');
logoutBtn.addEventListener('click',()=>{
  localStorage.removeItem('user_id');
  localStorage.removeItem('jwt');

  window.location = '/index.html';
});

let employeeSubmit = document.querySelector('#e-submit');
employeeSubmit.addEventListener('click', async()=>{
 let reimbursementAmountInput = document.querySelector('#e-amount-input');
 let reimbursementDateInput = document.querySelector('#e-date-input');
 let reimbursementDescriptionInput = document.querySelector('#e-description-input');
 let reimbursementFilesInput = document.querySelector('#e-file-input');
 let reimbursementStatusInput = document.querySelector('#e-status-input');
 let reimbursementTypeInput = document.querySelector('#e-type-input');

 let formData = new FormData();
 formData.append('amount', reimbursementAmountInput.value)
 formData.append('submitDate', reimbursementDateInput.value)
 formData.append('description', reimbursementDescriptionInput.value)
 formData.append('image', reimbursementFilesInput.files[0])
 formData.append('status', reimbursementStatusInput.value)
 formData.append('type', reimbursementTypeInput.value)


 try{
  let res = await fetch(`http://localhost:8081/users/${localStorage.getItem('user_id')}/reimbursements`,{
    method:'POST',
    body:formData ,
    headers: {
 'Authorization': `Bearer ${localStorage.getItem('jwt')}`
    }
  });
populateReimbursementsTable();
 }catch (e){
   console.log(e);
 }

})


async function populateReimbursementsTable(){
  const URL =`http://localhost:8081/user/${localStorage.getItem('user_id')}/reimbursements`;

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

    for (let reimbursement of reimbursements){

    let tr = document.createElement('tr');

    let td1 =document.createElement('td');
    td1.innerText = reimbursement.id;

    let td2 = document.createElement('td')
    td2.innerText = reimbursement.amount;


    let td3 = document.createElement('td')
    td3.innerText = reimbursement.submitDate;

    // let td4 = document.createElement('td')
    // td4.innerText = reimbursement.resolveDate;

    let td5 = document.createElement('td')
    td5.innerText = reimbursement.status;

    let td6 = document.createElement('td')
    td6.innerText = reimbursement.type;

    let td7 = document.createElement('td')
    td7.innerText = reimbursement.authorId;

    let td8 = document.createElement('td')
    td8.innerText = reimbursement.authorUserName;


       let td13 = document.createElement('td')
    td13.innerText = (reimbursement.resolverId  ? reimbursement.resolverId : 'Not Resolved');
    td13.style.color = (reimbursement.resolverId  ? reimbursement.resolverId.color : 'red');

    let td14 = document.createElement('td')
    td14.innerText = (reimbursement.resolverUserName? reimbursement.resolverUserName : 'Not Resolved');
    td14.style.color = (reimbursement.resolverUserName? reimbursement.resolverUserName.color : 'red');


    let td18 = document.createElement('td')
    let imgElement = document.createElement('img')
    imgElement.setAttribute('src', `http://localhost:8081/reimbursement/${reimbursement.id}/image`);
    imgElement.style.height = '100px';
    td18.appendChild(imgElement);


    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    // tr.appendChild(td4);
    tr.appendChild(td5);
    tr.appendChild(td6);
    tr.appendChild(td7);
    tr.appendChild(td8);
    tr.appendChild(td13);
        tr.appendChild(td14);
        tr.appendChild(td18);



        tbody.appendChild(tr);
  }



  console.log(reimbursements);
}
}