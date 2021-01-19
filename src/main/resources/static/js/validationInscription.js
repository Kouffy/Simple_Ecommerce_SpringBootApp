var phoneRGEX = /(\+212|0)([ \-_/]*)(\d[ \-_/]*){9}$/;
var emailRGEX = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
var passwRGEX=  /^[A-Za-z]\w{7,14}$/;


function validateForm() 
{
    var myForm = document.getElementById("myForm");
 if (emailRGEX.test(myForm.email.value))
  {
    if(phoneRGEX.test(myForm.tel.value))
    {
        if(passwRGEX.test(myForm.password.value))
        {
            return true;
        }
    
        alert("You have entered an invalid password");
        return (false);
    }
    alert("You have entered an invalid phone!");
    return (false);
  }
    alert("You have entered an invalid email address!");
    return (false);
}



