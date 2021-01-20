
function validateForm() {
    $("#error_qte").remove();
var F = document.getElementById("myForm");
    if (F.qte.value == "") {
      $("#qte").after("<span id='error_qte' class='text-danger'> Enter your Qte </span>");
      return false;
    }
    else if(F.qte.value <= 0 )
    {
        $("#qte").after("<span id='error_qte' class='text-danger'> valeur de Qte invalide</span>");
        return false;
    }
  
  
  }
  