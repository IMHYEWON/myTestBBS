
let idChk = false;
let user_id = $.cookie("user_id");

if(user_id != null){
	document.getElementById("id").value = user_id;
	document.getElementById("chk_save_id").checked=true;
}


/*if(user_id != null){
	$("#id").val(user_id);
	$("#chk_save_id").prop("checked", true);
}
*/




/**
 * Sign Up 
 */

 window.addEventListener('DOMContentLoaded', function () {
    let signElement = document.getElementById('btnSignIn');

    signElement.addEventListener('click', (e) => {
        goSignUp();
    });
}); 
 
  /**
   * Redirect Sign Up Pacre
   */
 const goSignUp = () => {
    alert("등록된 정보가 없습니다\n회원가입 페이지로 이동합니다.");
    window.location.href = "regiCss.jsp";
 }
 
 const getCheckboxValue = (event) => {
    if(event.target.checked)  {
      confirm("id를 저장하시겠습니까?");
    }

  }




$("#chk_save_id").click(function() {
	
	if($("#chk_save_id").is(":checked")){
		
		if($("#id").val().trim() == ""){
			alert("id를 입력해 주십시오");
			$("#chk_save_id").prop("checked", false);
		}else{
			// cookie를 저장	
			$.cookie("user_id", $("#id").val().trim(), {expires:7, path:'./'});
		}	
		
	}else{
		$.removeCookie("user_id", { path:'./' });
	}	
});
