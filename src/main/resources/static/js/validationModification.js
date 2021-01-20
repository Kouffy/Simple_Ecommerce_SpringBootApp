var phoneRGEX = /(\+212|0)([ \-_/]*)(\d[ \-_/]*){9}$/;
var emailRGEX = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
var passwRGEX=  /^[A-Za-z]\w{7,14}$/;
var frm = document.getElementById("myForm");

function validateForm() {
  $("#error_fnmae").remove();
  $("#error_lname").remove();
  $("#error_email3").remove();
  $("#email_error2").remove();
  $("#error_login").remove();
  $("#error_fnmae").remove();
  $("#email_error2").remove();
  $("#error_login").remove();
  $("#error_ville").remove();
  $("#email_error2").remove();
  $("#error_login").remove();
  $("#error_tel").remove();
  $("#error_tel1").remove();
  

  if (frm.firstName.value == "") {
    $("#firstame").after("<span id='error_fnmae' class='text-danger'> Enter your name </span>");
    return false;
  }

  if (frm.lastName.value == "") {
    $("#lastName").after("<span id='error_lname' class='text-danger'> Enter your lastName </span>");
    return false;
  }

  if (frm.email.value == "") {
    $("#email").after("<span id='error_email3' class='text-danger'> Enter your email </span>");
    return false;
  }
  else if (!emailRGEX.test(frm.email.value)) {
    $("#email_error2").remove(); $("#email").after("<span id='email_error2' class='text-danger'> Enter valid email </span>");
    return false;
  }
  
  if (frm.tel.value == "") {
    $("#tel").after("<span id='error_tel1' class='text-danger'> Enter your tel </span>");
    return false;
  }
  else if (!phoneRGEX.test(frm.tel.value)) {
    $("#error_tel").remove(); $("#tel").after("<span id='error_tel' class='text-danger'> Enter valid tel </span>");
    return false;

  }

  if (frm.ville.value == "") {
    $("#ville").after("<span id='error_ville' class='text-danger'> Enter your ville </span>");
    return false;
  }

  if (frm.login.value == "") {
    $("#login").after("<span id='error_login' class='text-danger'> Enter your login </span>");
    return false;
  }
 
 
  if ( $("#passworda").val() == "") {
    $("#passworda").after("<span id='error_login' class='text-danger'> Enter your old password </span>");
    return false;
  }
  if (frm.password2.value == "") {
    $("#password2").after("<span id='error_login' class='text-danger'> Enter your new password </span>");
    return false;
  }
  if (frm.password3.value == "") {
    $("#password3").after("<span id='error_login' class='text-danger'> Re-Enter your new password </span>");
    return false;
  }
  else if(frm.password3.value !=frm.password2.value){
    $("#password3").after("<span id='error_login' class='text-danger'> les deux mots de passe doivent étre the same </span>");
    return false;
  }

return true;

}



