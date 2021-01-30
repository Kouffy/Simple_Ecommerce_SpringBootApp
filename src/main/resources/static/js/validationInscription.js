var phoneRGEX = /(\+212|0)([ \-_/]*)(\d[ \-_/]*){9}$/;
var emailRGEX = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
var passwRGEX = /^[A-Za-z]\w{7,14}$/;

function validateForm() {
  $("#error_fnmae").remove();
  $("#error_lname").remove();
  $("#error_email3").remove();
  $("#email_error").remove();
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
  

  if (document.forms['myForm'].firstname.value === "") {
    $("#firstname").after("<span id='error_fnmae' class='text-danger'> Enter your name </span>");
    return false;
  }

  if (document.forms['myForm'].lastname.value == "") {
    $("#lastname").after("<span id='error_lname' class='text-danger'> Enter your lastName </span>");
    return false;
  }

  if (document.forms['myForm'].email.value === "") {
    $("#email").after("<span id='error_email3' class='text-danger'> Enter your email </span>");
    return false;
  }
  else if (!emailRGEX.test(document.forms['myForm'].email.value)) {
    $("#email_error2").remove(); $("#email").after("<span id='email_error2' class='text-danger'> Enter valid email </span>");
    return false;
  }
  else if ( $.ajax({
    url: 'http://localhost:7777/profile/validemail',
    type: 'GET',
    data: {
      'email': document.forms['myForm'].email.value,
    },

    success: function (response1) {
      $("#email_error").remove();
      if (!response1) {
        $("#email").after("<span id='email_error' class='text-danger'>" + "Email indisponible" + "</span>");
      }
      else {
        $("#email").after("<span id='email_error' class='text-danger'>" + " Email disponible " + "</span>");
 
      }
    },

    error: function (e) {
      $("#result").html("Something went wrong");
    }

  }).responsetext == false) {
    return false;
  }

  if (document.forms['myForm'].login.value == "") {
    $("#login").after("<span id='error_login' class='text-danger'> Enter your login </span>");
    return false;
  }
  else if ( $.ajax({
    url: 'http://localhost:7777/profile/validlog',
    type: 'GET',
    data: {
      'login': document.forms['myForm'].login.value,
    },

    success: function (response1) {
      $("#error_login").remove();
      console.log(response1);
      if (!response1) {
        $("#login").after("<span id='error_login' class='text-danger'>" + "Username indisponible" + "</span>");
      }
      else {
        console.log(response1);
        $("#login").after("<span id='error_login' class='text-danger'>" + " Username disponible " + "</span>");
      }
    },

    error: function (e) {
      $("#result").html("Something went wrong");
    }

  }).responsetext == false) {
    return false;
  }

  if (document.forms['myForm'].tel.value == "") {
    $("#tel").after("<span id='error_tel1' class='text-danger'> Enter your tel </span>");
    return false;
  }
  else if (!phoneRGEX.test(document.forms['myForm'].tel.value)) {
    $("#error_tel").remove(); $("#tel").after("<span id='error_tel' class='text-danger'> Enter valid tel </span>");
    return false;

  }

  if (document.forms['myForm'].ville.value == "") {
    $("#ville").after("<span id='error_ville' class='text-danger'> Enter your ville </span>");
    return false;
  }




}



