/**
 * Sign Up 
 */
let idChk = false;

window.addEventListener('DOMContentLoaded', function () {
    domready();
}); 

const domready = () => {
    createImg();
    
    let idElement   = document.getElementById('btnChkId');
    let signElement = document.getElementById('btnSignUp');

    idElement.addEventListener('click', (e) => {
        idChk = IdCheck();
        if(idChk) {
            alert("ID 검사 완료!");
        }
    });

    signElement.addEventListener('click', (e) => {
        SignUp();
    });
};




 /**
  * Validation Check
  */
  const Validation = () => {
    // Check Id
    let id      = document.getElementById('UserId');
    let pwd     = document.getElementById('UserPwd');
    let email   = document.getElementById('UserMail');
    if (id.value.trim() == null || id.value.trim() == '') {
        alert("id를 입력해주세요.");
        id.focus();
        return;
    }

    // Check pwd
    if (pwd.value.trim().length < 9 ) {
        alert("비밀번호는 8글자 이상 입력해주세요");
        console.log(pwd.value.trim());
        pwd.focus();
        return;
    }

    // Check email
   if (!(ValidateEmail(email.value.trim()))) {
        alert("이메일 형식을 확인해주세요");
        email.focus();
        return;
   }
   return true;
    
 }

 /**
  * ID Check
  */
  const IdCheck = ()  => {
    let id      = document.getElementById('UserId').value;
    if (id == null || id == '') {
        alert("id를 입력해주세요.");
        return;
    }

    // if (id != document.getElementById('UserId').value) {
    //     return;
    // }
    return true;
}

const ValidateEmail =  (inputText) => 
{
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if(inputText=='' || inputText==null){
        return;
    }

    if(!inputText.match(mailformat)) {
        return;
    } 
   
    else return true;
}

 /**
  * Sign Up
  */
const SignUp = () => {
    if (!idChk) {
        alert("ID 검사 해주세요");
        return;
    }; 

    
    if(Validation()) {
        window.location.href = "result.html";
    }
}


const createImg = () => {
    const imgField = document.getElementById('bgImgField');
    const img =  document.createElement('img');
    img.classList.add('bgImg');

    let imgArr = new Array();
    imgArr.push("https://designbuffs.com/wp-content/uploads/2020/11/Animation-Girl-Listens-To-Music.gif" );
    imgArr.push("https://designbuffs.com/wp-content/uploads/2020/11/Boy-Working-From-Home.gif" );
    imgArr.push("https://designbuffs.com/wp-content/uploads/2020/11/People-loving-to-work-animation.gif");

    let num = getRandom();
    img.src = imgArr[num];

    imgField.prepend(img);

}

const getRandom = () => {
    const IMG_NUM = 3;
    let num = Math.floor(Math.random() * IMG_NUM);
    return num;    
}