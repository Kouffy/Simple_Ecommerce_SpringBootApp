
function validateForm() {
  var myForm = document.getElementById("myForm");
  if (isNaN(myForm.prix.value)) {
    alert("You have entered an invalid  price!");
    return false;
  }
  if (myForm.qte_stock.value <= 0 || isNaN(myForm.qte_stock.value)) {
  alert("You have entered an invalid qte!");
  return false;
  }
}



