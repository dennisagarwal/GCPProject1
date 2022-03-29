let navElement = document.querySelector("#navbar");

if (
  localStorage.getItem("user_id") &&
  localStorage.getItem("jwt") &&
  localStorage.getItem("user_role")
) {
  if (localStorage.getItem("user_role") === "manager") {
    let managerLink = document.createElement("a");
    managerLink.setAttribute("href", "manager-page.html");
    managerLink.innerText = "Manager Page";

    navElement.appendChild(managerLink);
  } else if (localStorage.getItem("user_role") === "employee") {
    let employeeLink = document.createElement("a");
    employeeLink.setAttribute("href", "employee-page.html");
    employeeLink.innerText = "Employee Page";
  }
}
