
function validateForm() {
  var myForm = document.getElementById("myForm");
  if (!isNaN(myForm.prix.value)) {
    if (myForm.qte - stock.value < 1) {
      return true;
    }
    alert("You have entered an invalid qte!");
    return (false);
  }
  alert("You have entered an invalid price!");
  return (false);
}



