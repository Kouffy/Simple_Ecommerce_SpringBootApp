function validateForm() {
    $("#error_qte").remove();
  
    if (document.forms['myForm'].qte.value == "") {
      $("#qte").after("<span id='error_qte' class='text-danger'> Enter your Qte </span>");
      return false;
    }
    else if(document.forms['myForm'].qte.value <= 0 )
    {
        $("#qte").after("<span id='error_qte' class='text-danger'> valeur de Qte invalide</span>");
    }
  
  
  }
  