const figure = document.getElementById("figure");
const form = document.querySelector(".form");

const btnGoogle = document.getElementById("btn-google");
const btnRegister = document.getElementById("register");
const btnInfo = document.getElementById("btn-info");
const btnPlan = document.getElementById("btn-plan");
const btnCard = document.getElementById("btn-card-data");

let transleta = 0;
let operacion = 0;
const numberElements = 100 / 5;

const moveToBottom = () => {
  if (operacion <= 80) {
    operacion += numberElements;
    form.style.transform = `translateY(-${operacion}%)`;
    figure.style.transform = `translateY(-${operacion}%)`;
  }
};
