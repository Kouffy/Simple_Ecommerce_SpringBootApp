var phoneRGEX = /(\+212|0)([ \-_/]*)(\d[ \-_/]*){9}$/;
var emailRGEX = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
var passwRGEX=  /^[A-Za-z]\w{7,14}$/;
 var myForm = document.getElementById("myForm");

function validateFields()
{
  if(myForm.firstName.val != "")
  {
    if(myForm.lastName.val != "")
    {
      if(myForm.email.val != "")
      {
      if(myForm.login.val != "")
      {
        if(myForm.tel.val != "")
        {
          if(myForm.ville.val != "")
          {
            $("#ville").after("<span id='error' class='text-danger'> Enter your email </span>");
            return true;
          }
          $("#tel").after("<span id='error' class='text-danger'> Enter your email </span>");
          return false;
        }
        $("#login").after("<span id='error' class='text-danger'> Enter your email </span>");
        return false;
      }
        $("#email").after("<span id='error' class='text-danger'> Enter your email </span>");
        return false;
      }
      $("#lastName").after("<span id='error' class='text-danger'> Enter your email </span>");
      return false;
    }
    $("#firstName").after("<span id='error' class='text-danger'> Enter your email </span>");
    return false;
  }
  return false;
}
function validateForm() 
{
  var vf = validateFields();
  if(vf)
  {
    if(emailRGEX.test(myForm.email.value))
    {
      if(phoneRGEX.test(myForm.tel.value))
      {
        return true;
      }
      $("#tel").after("<span id='error' class='text-danger'> tel </span>");
      return false;
    }
    $("#tel").after("<span id='error' class='text-danger'>  email </span>");
    return false;
  }
}



